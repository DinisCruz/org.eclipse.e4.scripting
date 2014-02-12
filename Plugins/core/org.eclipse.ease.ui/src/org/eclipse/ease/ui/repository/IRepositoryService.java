package org.eclipse.ease.ui.repository;

import java.util.Collection;

/**
 * Global service to register user scripts and to query for registered scripts. To get the service instance use
 * 
 * <pre>
 * final IRepositoryService repositoryService = (IRepositoryService) PlatformUI.getWorkbench().getService(IRepositoryService.class);
 * </pre>
 */
public interface IRepositoryService {

	/**
	 * Trigger an immediate refresh of all script sources and contained scripts.
	 */
	void update();

	Collection<IScript> getScripts();

	void addScriptListener(IScriptListener listener);

	void removeScriptListener(IScriptListener listener);

	/**
	 * Get a script by providing its full name.
	 * 
	 * @param name
	 *            full name of script (including path)
	 * @return script instance
	 */
	IScript getScript(String name);

	Collection<IEntry> getLocations();

	void removeLocation(IEntry entry);

	void addLocation(IEntry entry);
}
