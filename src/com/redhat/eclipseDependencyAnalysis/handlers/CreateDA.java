package com.redhat.eclipseDependencyAnalysis.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

import com.redhat.eclipseDependencyAnalysis.daWizard.DAWizard;

/**
 * The Class CreateDA executes the creation of the Wizard.
 */
public class CreateDA implements IHandler {
	
	/**
	 * Executes the creating of the Wizard.
	 *
	 * @param shell the shell
	 */
	@Execute
	public void execute(Shell shell){
		WizardDialog dialog = new WizardDialog(shell, new DAWizard());
		dialog.open();
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
