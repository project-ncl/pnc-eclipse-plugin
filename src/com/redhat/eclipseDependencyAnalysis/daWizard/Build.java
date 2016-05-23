package com.redhat.eclipseDependencyAnalysis.daWizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.redhat.eclipseDependencyAnalysis.buildActions.BuildConfiguration;
import com.redhat.eclipseDependencyAnalysis.buildActions.BuildResult;
import com.redhat.eclipseDependencyAnalysis.buildActions.ContentForNewBC;
import com.redhat.eclipseDependencyAnalysis.jsonHttp.DAJsonHttp;

/**
 * The Class Build is the last page of the wizard to display the data of the resulting builds.
 */
public class Build extends WizardPage {

	private Composite container;
	private Group grpDependencyDetails;
	private Table table;
	private TableViewer tableViewer;
	private ContentForNewBC content;

	/**
	 * Create the wizard.
	 */
	public Build() {
		super("Page two");
		setTitle("Dependency Analysis");
		setDescription("Build results");
	}

	/**
	 * Create contents of the wizard.
	 *
	 * @param parent the parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		
		setControl(container);
		
		grpDependencyDetails = new Group(container, SWT.NONE);
		FormData fd_grpDependencyDetails = new FormData();
		fd_grpDependencyDetails.bottom = new FormAttachment(100, -38);
		fd_grpDependencyDetails.right = new FormAttachment(100, -10);
		fd_grpDependencyDetails.top = new FormAttachment(0, 10);
		fd_grpDependencyDetails.left = new FormAttachment(0, 10);
		grpDependencyDetails.setLayoutData(fd_grpDependencyDetails);
		grpDependencyDetails.setText("Dependency details");
		grpDependencyDetails.setLayout(new FormLayout());
			
		createViewer(parent);
	}
	
	/**
	 * Creates the viewer. Gets the data from previous page which is set to a new attribute analysePage. Then from each BuildConfigurations
	 * MyDep it sets the version, artifact Id and group Id to analyse the dependency to show new versions as a result of the build.
	 * 
	 *
	 * @param parent the parent
	 */
	public void createViewer(Composite parent){
		tableViewer = new TableViewer(grpDependencyDetails, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		createColumns(parent, tableViewer);
		
		
		table = tableViewer.getTable();
		Analyse analysePage = (Analyse)getWizard().getPage("Page two");
		
		content = analysePage.toNextPage;
		
		JSONObject jObject = new JSONObject();
		
		List<BuildResult> results = new ArrayList<BuildResult>();
		
		for(BuildConfiguration item : content.getBCs()){
			JSONArray jArray = new JSONArray();
	        JSONObject depJSON = new JSONObject();
	        depJSON.put("version", item.getMyDep().getVersion());
	        depJSON.put("artifactId", item.getMyDep().getArtifactId());
	        depJSON.put("groupId", item.getMyDep().getGroupId());
	        jArray.add(depJSON);

	        DAJsonHttp.http(jArray.toJSONString());
	        
			BuildResult res = new BuildResult(item.getMyDep().getArtifactId(), item.getMyDep().getGroupId(), DAJsonHttp.version, item.getBuildRecordId(), item.getStatus());
			results.add(res);
		}
		
		tableViewer.setContentProvider(new ArrayContentProvider());

	    
		FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -5);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(0, 5);
		fd_table.left = new FormAttachment(0, 5);
		
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);	
	}
	
	/**
	 * Creates the columns.
	 *
	 * @param parent the parent
	 * @param viewer the viewer
	 */
	public void createColumns(final Composite parent, final TableViewer viewer){
		
		String[] titles = { "Dependency", "Versions", "Build URL", "Build status"};
	    int[] bounds = { 100, 230, 285, 100};

	 // first column is for the Dependency name
	    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	  BuildResult r = (BuildResult) element;
	    	  return r.getDependencyName();
	      }
	    });
	    
	    // second column is for the Versions
	    col = createTableViewerColumn(titles[1], bounds[1], 1);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	  BuildResult r = (BuildResult) element;
	    	  return r.getVersions();
	      }
	    });
	    
	 // first column is for the Build URL
	    col = createTableViewerColumn(titles[2], bounds[2], 2);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	  BuildResult r = (BuildResult) element;
	    	  return r.getBuildConfiguration();
	      }
	    });
	    
	    // second column is for the Build satus
	    col = createTableViewerColumn(titles[3], bounds[3], 3);
	    col.setLabelProvider(new ColumnLabelProvider() {
	      @Override
	      public String getText(Object element) {
	    	  BuildResult r = (BuildResult) element;
	    	  return r.getBuildStatus();
	      }
	    });
	}
	
	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
	    final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer,
	        SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	  }

	/**
	 * Can finish. If the page Finish button can be pressed.
	 *
	 * @return true
	 */
	public Boolean canFinish(){
		return true;
	}
	
}


