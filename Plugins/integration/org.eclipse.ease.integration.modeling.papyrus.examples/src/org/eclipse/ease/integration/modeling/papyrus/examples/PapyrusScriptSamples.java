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
package org.eclipse.ease.integration.modeling.papyrus.examples;

import org.osgi.framework.Bundle;

public class PapyrusScriptSamples extends org.eclipse.ease.ui.wizard.AbstractNewSampleWizard {

	@Override
	protected Bundle getBundle() {
		return Activator.getDefault().getBundle();
	}

	@Override
	protected String getManifestPath() {
		return "samples/manifest.txt";
	}

	@Override
	protected String getScriptContainerFolder() {
		return "samples/";
	}

	@Override
	protected String getInitalProjectName() {
		return "Samples for Papyrus integration into EASE";
	}

}
