package org.eclipse.ease.ui.scripts.repository.impl;

import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.ease.Logger;
import org.eclipse.ease.service.IScriptService;
import org.eclipse.ease.service.ScriptType;
import org.eclipse.ease.ui.repository.IEntry;
import org.eclipse.ease.ui.repository.IRepositoryFactory;
import org.eclipse.ease.ui.repository.IScript;
import org.eclipse.ui.PlatformUI;

public class WorkspaceParser extends InputStreamParser {

	public WorkspaceParser(RepositoryService repositoryService) {
		super(repositoryService);
	}

	public void parse(final IResource resource, final IEntry entry) {
		if (resource instanceof IContainer) {
			// containment, parse children
			try {
				resource.accept(new IResourceVisitor() {

					@Override
					public boolean visit(final IResource resource) throws CoreException {

						if (resource instanceof IFile)
							parse(resource, entry);

						return entry.isRecursive();
					}

				}, entry.isRecursive() ? IResource.DEPTH_INFINITE : IResource.DEPTH_ONE, 0);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (resource instanceof IFile) {
			// try to locate registered script
			String location = "workspace:/" + resource.getFullPath();
			IScript script = getScriptByLocation(location);

			try {
				if (script == null) {
					// new script detected
					script = IRepositoryFactory.eINSTANCE.createScript();
					script.setEntry(entry);
					script.setLocation(location);

					ScriptType scriptType = getScriptType((IFile) resource);
					Map<String, String> parameters = extractParameters(scriptType, ((IFile) resource).getContents());
					script.getScriptParameters().clear();
					script.getScriptParameters().putAll(parameters);

					script.setTimestamp(resource.getModificationStamp());

					getRepositoryService().addScript(script);

				} else if (script.getTimestamp() != resource.getModificationStamp()) {
					// script needs updating
					ScriptType scriptType = getScriptType((IFile) resource);
					Map<String, String> parameters = extractParameters(scriptType, ((IFile) resource).getContents());

					script.setTimestamp(resource.getModificationStamp());

					getRepositoryService().updateScript(script, parameters);

				} else
					// script is up to date
					script.setUpdatePending(false);

			} catch (CoreException e) {
				// cannot read from script
				Logger.logError("Cannot read script: " + resource, e);
			}
		}
	}

	private ScriptType getScriptType(IFile file) throws CoreException {
		try {
			IContentDescription contentDescription = file.getContentDescription();
			if (contentDescription != null) {
				final IScriptService scriptService = (IScriptService) PlatformUI.getWorkbench().getService(IScriptService.class);
				ScriptType scriptType = scriptService.getScriptType(contentDescription.getContentType());
				if (scriptType != null)
					return scriptType;
			}
		} catch (CoreException e) {
			// cannot retrieve content description
		}

		// we could not get the script type from the content type, try parsing the file itself
		return getScriptType(file.getContents());
	}
}