/**
 * Thread: UI
 * Menu: ScriptUtils > Resource > Convert to scripting project
 * Kudos: Arthur Daussy.
 * License: EPL 1.0
 * Description : {This script is used to convert the selected IProject to script project.}
 * EnableWhen::[With selection {
 *    Iterable {
 *        InstanceOf "org.eclipse.core.resources.IProject"
 *    }
 *}]::
 */

loadModule("WorkspaceResourceModule");
selectionHelper = loadModule("SelectionModule");
selection = selectionHelper.getIterableSelection();
iterator = selection.iterator();

while(iterator.hasNext())
	addProjectNature(iterator.next(),"org.eclipse.ease.storedscript.EScript_Monkey_Nature");
