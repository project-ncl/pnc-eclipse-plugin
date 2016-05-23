package com.redhat.eclipseDependencyAnalysis.daWizard;


import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * The Class DAWizard is for instantiating the Wizard which contains all the pages..
 *
 * @author Denis Richtarik
 */
public class DAWizard extends Wizard{

	private Login page1;
	private Analyse page2;	

	/**
	 * Gets the page1.
	 *
	 * @return the page1
	 */
	public Login getPage1() {
		return page1;
	}

	/**
	 * Instantiates a new DA wizard.
	 */
	public DAWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		page1 = new Login();
		page2 = new Analyse();
		addPage(page1);
		addPage(page2);
	}
	
	/**
	 * @see org.eclipse.jface.wizard.Wizard#getWindowTitle()
	 */
	@Override
	public String getWindowTitle() {
		return "Project Newcastle Build Process";
	}
	
	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		if(getContainer().getCurrentPage() instanceof Build){
			return true;
		} else {
			if(getContainer().getCurrentPage() instanceof Analyse){
				return true;
			}
			return false;
		}
	}


	/**
	 * This is used so the Build pages table wont be filled with incorrect data, which at the start of the wizard aren't all
	 * received. The Build page is created only after the Analyse page is complete.
	 * @see org.eclipse.jface.wizard.Wizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if(page instanceof Analyse){
			Build pagee = new Build();
			addPage(pagee);
			return pagee;
		}else if(page == page1){
			return page2;
		}
		return super.getNextPage(page);
			
	};
	
	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	@Override
	public void addPage(IWizardPage page) {
		// TODO Auto-generated method stub
		super.addPage(page);
	}

}
