package org.eclipse.ease.ui.view;

import org.eclipse.ease.ui.repository.IScript;
import org.eclipse.ease.ui.scripts.ui.ScriptComposite;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ScriptExplorerView extends ViewPart {
	public ScriptExplorerView() {
	}

	public static final String ID = "org.eclipse.ease.ui.view.ScriptExplorerView"; //$NON-NLS-1$
	private ScriptComposite scriptComposite;

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		scriptComposite = new ScriptComposite(null, getSite(), parent, SWT.NONE);
		scriptComposite.setDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Object element = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (element instanceof IScript)
					((IScript) element).run();
			}
		});
	}

	@Override
	public void setFocus() {
		scriptComposite.setFocus();
	}
}
