/*******************************************************************************
 * Copyright (c) 2013 Christian Pontesegger and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Christian Pontesegger - initial API and implementation
 *******************************************************************************/
package org.eclipse.ease.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ease.AbstractScriptEngine;
import org.eclipse.ease.IScriptEngine;
import org.eclipse.ease.IScriptEngineLaunchExtension;
import org.eclipse.ease.Logger;
import org.eclipse.ease.modules.IModuleWrapper;
import org.eclipse.ease.modules.ModuleDefinition;

public class ScriptService implements IScriptService {

	private static final String ENGINE = "engine";

	private static final String ENGINE_ID = "engineID";

	private static final Object EXTENSION_MODULE = "module";

	private static final String EXTENSION_LANGUAGE_ID = "org.eclipse.ease.language";

	private static final String EXTENSION_MODULES_ID = "org.eclipse.ease.modules";

	private static final String EXTENSION_SCRIPTTYPE_ID = "org.eclipse.ease.scriptType";

	private static final String SCRIPTTYPE_NAME = "name";

	private static final String LAUNCH_EXTENSION = "launchExtension";

	private static final String MODULE_WRAPPER = "moduleWrapper";

	private static ScriptService fInstance = null;

	static ScriptService getInstance() {
		if (fInstance == null)
			fInstance = new ScriptService();

		return fInstance;
	}

	private Map<String, ModuleDefinition> fAvailableModules = null;

	private Map<String, EngineDescription> fEngineDescriptions = null;

	private Map<String, IModuleWrapper> fModuleWrappers = null;

	private Map<String, ScriptType> fScriptTypes = null;

	private ScriptService() {
	}

	private IScriptEngine createEngine(final EngineDescription description) {
		IScriptEngine engine = description.createEngine();

		if (engine != null) {
			// configure engine
			if (engine instanceof AbstractScriptEngine)
				((AbstractScriptEngine) engine).setEngineDescription(description);

			// engine loaded, now load launch extensions
			for (final IScriptEngineLaunchExtension extension : getLaunchExtensions(description.getID()))
				extension.createEngine(engine);

			return engine;
		}

		return null;
	}

	@Override
	public IScriptEngine createEngine(final String scriptType) {

		List<EngineDescription> engines = getEngines(scriptType);

		if (!engines.isEmpty())
			return createEngine(engines.get(0));

		return null;
	}

	@Override
	public IScriptEngine createEngineByID(final String engineID) {
		EngineDescription description = getEngineDescriptions().get(engineID);
		if (description != null)
			return createEngine(description);

		return null;
	}

	@Override
	public Map<String, ModuleDefinition> getAvailableModules() {
		if (fAvailableModules == null) {
			fAvailableModules = new HashMap<String, ModuleDefinition>();
			final IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_MODULES_ID);
			for (final IConfigurationElement e : config) {
				if (e.getName().equals(EXTENSION_MODULE)) {
					// module extension detected
					ModuleDefinition definition = new ModuleDefinition(e);
					fAvailableModules.put(definition.getName(), definition);
				}
			}
		}
		return fAvailableModules;
	}

	@Override
	public Collection<EngineDescription> getEngines() {
		return getEngineDescriptions().values();
	}

	@Override
	public List<EngineDescription> getEngines(String scriptType) {
		List<EngineDescription> result = new ArrayList<EngineDescription>();

		for (EngineDescription description : getEngines()) {
			if (description.supports(scriptType))
				result.add(description);
		}

		// sort by priority
		Collections.sort(result, new Comparator<EngineDescription>() {

			@Override
			public int compare(EngineDescription o1, EngineDescription o2) {
				return o2.getPriority() - o1.getPriority();
			}
		});

		return result;
	}

	private Map<String, EngineDescription> getEngineDescriptions() {
		if (fEngineDescriptions == null) {
			fEngineDescriptions = new HashMap<String, EngineDescription>();
			final IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_LANGUAGE_ID);

			for (final IConfigurationElement e : config) {
				if (ENGINE.equals(e.getName())) {
					EngineDescription engine = new EngineDescription(e);
					fEngineDescriptions.put(engine.getID(), engine);
				}
			}
		}
		return fEngineDescriptions;
	}

	// public Set<ScriptType> getHandleScriptType() {
	// Set<ScriptType> result = new HashSet<ScriptType>();
	// for (EngineDescription desc : getEngineDescriptions().values()) {
	// for (ScriptType scriptType : desc.getSupportedScriptTypes()) {
	// result.add(scriptType);
	// }
	// }
	// return result;
	// }
	//

	@Override
	public IModuleWrapper getModuleWrapper(final String engineID) {
		return getModuleWrappers().get(engineID);
	}

	private Map<String, IModuleWrapper> getModuleWrappers() {
		if (fModuleWrappers == null) {
			fModuleWrappers = new HashMap<String, IModuleWrapper>();
			final IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_LANGUAGE_ID);

			for (final IConfigurationElement e : config) {
				try {
					if (MODULE_WRAPPER.equals(e.getName())) {
						final Object extension = e.createExecutableExtension("class");
						String engineID = e.getAttribute(ENGINE_ID);
						if ((extension instanceof IModuleWrapper) && (engineID != null)) {
							if (fModuleWrappers.containsKey(engineID))
								Logger.logError("The engine id " + engineID + " is already used");
							else
								fModuleWrappers.put(engineID, (IModuleWrapper) extension);
						}
					}
				} catch (final InvalidRegistryObjectException e1) {
				} catch (final CoreException e1) {
				}
			}
		}
		return fModuleWrappers;
	}

	@Override
	public Collection<IScriptEngineLaunchExtension> getLaunchExtensions(final String engineID) {
		final Collection<IScriptEngineLaunchExtension> extensions = new HashSet<IScriptEngineLaunchExtension>();

		final IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_LANGUAGE_ID);

		for (final IConfigurationElement e : config) {
			try {
				if (LAUNCH_EXTENSION.equals(e.getName())) {
					if (e.getAttribute(ENGINE_ID).equals(engineID)) {
						final Object extension = e.createExecutableExtension("class");
						if (extension instanceof IScriptEngineLaunchExtension)
							extensions.add((IScriptEngineLaunchExtension) extension);
					}
				}
			} catch (final InvalidRegistryObjectException e1) {
			} catch (final CoreException e1) {
			}
		}

		return extensions;
	}

	@Override
	public Map<String, ScriptType> getAvailableScriptTypes() {
		if (fScriptTypes == null) {
			fScriptTypes = new HashMap<String, ScriptType>();
			final IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_SCRIPTTYPE_ID);

			for (final IConfigurationElement e : config) {
				if ("scriptType".equals(e.getName()))
					fScriptTypes.put(e.getAttribute(SCRIPTTYPE_NAME), new ScriptType(e));
			}
		}

		return fScriptTypes;
	}

	@Override
	public ScriptType getScriptType(IContentType contentType) {
		for (ScriptType scriptType : getAvailableScriptTypes().values()) {
			if (scriptType.getContentTypes().contains(contentType.getId()))
				return scriptType;
		}

		return null;
	}

	@Override
	public ScriptType getScriptType(String fileExtension) {
		for (ScriptType scriptType : getAvailableScriptTypes().values()) {
			if (scriptType.getDefaultExtension().equalsIgnoreCase(fileExtension))
				return scriptType;
		}

		return null;
	}
}
