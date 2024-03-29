package org.eclipse.ease.ui.propertytester;

import java.util.Collection;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.ease.service.EngineDescription;
import org.eclipse.ease.service.ScriptType;
import org.eclipse.ease.tools.ResourceTools;
import org.eclipse.ui.part.FileEditorInput;

public class EngineTester extends PropertyTester {

	private static final String HAS_ENGINE = "hasEngine";
	private static final String HAS_DEBUG_ENGINE = "hasDebugEngine";

	public EngineTester() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof Collection)
			receiver = ((Collection) receiver).iterator().next();

		if (receiver instanceof FileEditorInput)
			receiver = ((FileEditorInput) receiver).getFile();

		if (receiver instanceof IFile) {
			ScriptType scriptType = ResourceTools.getScriptType((IFile) receiver);
			Collection<EngineDescription> engines = scriptType.getEngines();

			if (HAS_ENGINE.equals(property))
				return !engines.isEmpty();

			if (HAS_DEBUG_ENGINE.equals(property)) {
				for (EngineDescription description : engines) {
					if (description.supportsDebugging())
						return true;
				}
			}
		}

		return false;
	}
}
