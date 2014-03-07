package org.eclipse.ease.module.platform;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.ease.common.RunnableWithResult;
import org.eclipse.ease.modules.AbstractScriptModule;
import org.eclipse.ease.modules.ScriptParameter;
import org.eclipse.ease.modules.WrapToScript;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public class ResourcesModule extends AbstractScriptModule {

	@WrapToScript
	public static final int READ = IFileHandle.READ;

	@WrapToScript
	public static final int WRITE = IFileHandle.WRITE;

	@WrapToScript
	public static final int APPEND = IFileHandle.APPEND;

	@WrapToScript
	public static final int RANDOM_ACCESS = IFileHandle.RANDOM_ACCESS;

	/**
	 * Get the workspace root.
	 * 
	 * @return workspace root
	 */
	@WrapToScript
	public IWorkspaceRoot getWorkspace() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}

	/**
	 * Get a project instance.
	 * 
	 * @param name
	 *            project name
	 * @return project or <code>null</code>
	 */
	@WrapToScript
	public IProject getProject(final String name) {
		return getWorkspace().getProject(name);
	}

	/**
	 * Opens a file dialog. Depending on the <i>rootFolder</i> a workspace dialog or a file system dialog will be used. If the folder cannot be located, the
	 * workspace root folder is used by default. When type is set to WRITE or APPEND a save dialog will be shown instead of the default open dialog.
	 * 
	 * @param rootFolder
	 *            root folder path to use
	 * @param type
	 *            dialog type to use (WRITE|APPEND for save dialog, other for open dialog)
	 * @return full path to selected file
	 */
	@WrapToScript
	public String showFileSelectionDialog(@ScriptParameter(optional = true, defaultValue = ScriptParameter.NULL) final Object rootFolder,
			@ScriptParameter(optional = true, defaultValue = "0") final int type) {

		Object root = (rootFolder instanceof String) ? getFile((String) rootFolder) : rootFolder;
		if (rootFolder == null)
			root = getWorkspace();

		if (root instanceof File) {
			// file system
			final int mode;
			switch (type) {
			case WRITE:
				// fall through
			case APPEND:
				mode = SWT.SAVE;
				break;
			default:
				mode = SWT.OPEN;
				break;
			}
			final File dialogRoot = (File) root;

			RunnableWithResult<String> runnable = new RunnableWithResult<String>() {

				private String fResult;

				@Override
				public void run() {
					FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), mode);
					dialog.setFilterPath(dialogRoot.getAbsolutePath());
					fResult = dialog.open();

				}

				@Override
				public String getResult() {
					return fResult;
				}
			};

			Display.getDefault().syncExec(runnable);
			return runnable.getResult();

		} else if (root instanceof IContainer) {
			// workspace
			final IContainer dialogRoot = (IContainer) root;

			RunnableWithResult<String> runnable = new RunnableWithResult<String>() {

				private String fResult = null;

				@Override
				public void run() {
					if ((type == WRITE) || (type == APPEND)) {
						// open a save as dialog
						SaveAsDialog dialog = new SaveAsDialog(Display.getDefault().getActiveShell());
						// set default filename if a subfolder is selected
						if (!dialogRoot.equals(getWorkspace()))
							dialog.setOriginalFile(dialogRoot.getFile(new Path("newFile")));

						if (dialog.open() == Window.OK)
							fResult = "workspace:/" + dialog.getResult().toString();

					} else {
						// open a select file dialog

						final ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(Display.getDefault().getActiveShell(),
								new WorkbenchLabelProvider(), new WorkbenchContentProvider());
						dialog.setTitle("Select file");
						dialog.setMessage("Select a file from the workspace:");
						dialog.setInput(dialogRoot);

						if (dialog.open() == Window.OK)
							fResult = "workspace:/" + ((IFile) dialog.getFirstResult()).getFullPath().toPortableString();
					}
				}

				@Override
				public String getResult() {
					return fResult;
				}
			};

			Display.getDefault().syncExec(runnable);
			return runnable.getResult();
		}

		return null;
	}

	/**
	 * Retrieve a file from the workspace or the file system.
	 * 
	 * @param location
	 *            file location to open from
	 * @return {@link File} or {@link IFile} object when file is found, <code>null</code> otherwise
	 */
	@WrapToScript
	public Object getFile(final String location) {
		return getEnvironment().resolveFile(location);
	}

	@WrapToScript
	public IFileHandle openFile(final Object location, @ScriptParameter(optional = true, defaultValue = "1") final int mode) {
		// resolve file
		Object file;
		if ((location instanceof IFile) || (location instanceof File))
			file = location;

		else
			file = getFile(location.toString());

		// create handle
		if (file instanceof IFile)
			return new ResourceHandle((IFile) file, mode);

		else if (file instanceof File)
			return new FilesystemHandle((File) file, mode);

		return null;
	}

	@WrapToScript
	public String readFile(final IFileHandle handle, @ScriptParameter(optional = true, defaultValue = "-1") final int characters) throws IOException {
		return handle.read(characters);
	}

	@WrapToScript
	public String readLine(final IFileHandle handle) throws IOException {
		return handle.readLine();
	}

	@WrapToScript
	public boolean writeFile(final IFileHandle handle, final String data, @ScriptParameter(optional = true, defaultValue = "-1") final int offset) {
		return handle.write(data, offset);
	}

	@WrapToScript
	public boolean existsFile(final IFileHandle handle) {
		return handle.exists();
	}

	@WrapToScript
	public boolean createFile(final IFileHandle handle, @ScriptParameter(optional = true, defaultValue = "true") final Object createHierarchy) throws Exception {
		return handle.createFile(Boolean.parseBoolean(createHierarchy.toString()));
	}
}
