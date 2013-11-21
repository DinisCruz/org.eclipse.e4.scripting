/*******************************************************************************
 * Copyright (c) 2013 Atos
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Arthur Daussy - initial implementation
 *******************************************************************************/
package org.eclipse.ease.module.platform.modules;

import org.eclipse.ease.module.platform.modules.editors.Editor;
import org.eclipse.ease.modules.AbstractScriptModule;
import org.eclipse.ease.modules.WrapToScript;
import org.eclipse.ui.PlatformUI;


/**
 * A Example module for interacting with a Simplify text editor
 * 
 * @author adaussy
 * 
 */
public class TextEditorModule extends AbstractScriptModule {


	public TextEditorModule() {
	}

	/**
	 * @return A simplification of a text {@link Editor}
	 */
	@WrapToScript
	public Editor getActiveEditor() {
		return new Editor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
	}

}
