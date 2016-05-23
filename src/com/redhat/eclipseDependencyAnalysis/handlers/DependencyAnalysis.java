package com.redhat.eclipseDependencyAnalysis.handlers;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class DependencyAnalysis has one method fill() which adds the item "Dependency analysis" to the existing Menu.
 */
public class DependencyAnalysis extends ContributionItem{
	
	/**
	 * Instantiates a new dependency analysis.
	 */
	public DependencyAnalysis(){
		
	}
	
	/**
	 * Instantiates a new dependency analysis.
	 *
	 * @param id the id
	 */
	public DependencyAnalysis(String id){
		super(id);
	}
	
	/** 
	 * adds the item "Dependency analysis" to the existing Menu.
	 */
	@Override
	public void fill(Menu menu, int index){
		MenuItem menuItem = new MenuItem(menu, SWT.CHECK, index);
        menuItem.setText("Denendency Analysis");
        menuItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
            	CreateDA DAWizard = new CreateDA();
            	Shell shell = new Shell();
            	DAWizard.execute(shell);
            }
        });
	}

}