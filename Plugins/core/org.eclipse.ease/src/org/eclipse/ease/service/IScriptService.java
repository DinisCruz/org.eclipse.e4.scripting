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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.ease.IScriptEngine;
import org.eclipse.ease.IScriptEngineLaunchExtension;
import org.eclipse.ease.modules.IModuleWrapper;
import org.eclipse.ease.modules.ModuleDefinition;

/**
 * Global service to create script engines and to query configuration data from the scripting extensions. To get the service instance use
 * 
 * <pre>
 * final IScriptService scriptService = (IScriptService) PlatformUI.getWorkbench().getService(IScriptService.class);
 * </pre>
 */
public interface IScriptService {

	/**
	 * Create a script engine for a certain kind of scripts.
	 * 
	 * @param scriptType
	 *            type of script
	 * @return script engine or <code>null</code>
	 */
	IScriptEngine createEngine(String scriptType);

	/**
	 * * @return script engine or <code>null</code>
	 * 
	 * Create a dedicated script engine. Allows to create an engine for a dedicated engine identifier.
	 * 
	 * @param engineID
	 *            engine identifier
	 * @return script engine or <code>null</code>
	 */
	IScriptEngine createEngineByID(String engineID);

	/**
	 * Get available engine descriptions.
	 * 
	 * @return available descriptions or empty collection
	 */
	Collection<EngineDescription> getEngines();

	/**
	 * Get available engine descriptions for a certain script type. The resulting list is sorted by priority. Index 0 contains the engine with the highest
	 * priority.
	 * 
	 * @param scriptType
	 *            type of script
	 * @return available descriptions or empty collection
	 */
	List<EngineDescription> getEngines(String scriptType);

	/**
	 * Get the unique module wrapper for a dedicated script engine.
	 * 
	 * @param engineID
	 *            engine identifier
	 * @return module wrapper or <code>null</code>
	 */
	IModuleWrapper getModuleWrapper(String engineID);

	/**
	 * Get a map of available modules. Keys contain the short module name, values contain its descriptor.
	 * 
	 * @return available modules or empty map
	 */
	Map<String, ModuleDefinition> getAvailableModules();

	/**
	 * Get a map of available script tpye. Keys contain the type name, values contain its descriptor.
	 * 
	 * @return available script types or empty map
	 */
	Map<String, ScriptType> getAvailableScriptTypes();

	/**
	 * Get Launch extensions for a dedicated script engine.
	 * 
	 * @param engineID
	 *            engine identifier
	 * @return launch extensions or empty collection
	 */
	Collection<IScriptEngineLaunchExtension> getLaunchExtensions(final String engineID);
}