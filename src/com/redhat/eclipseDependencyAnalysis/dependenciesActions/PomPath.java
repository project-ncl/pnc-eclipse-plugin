package com.redhat.eclipseDependencyAnalysis.dependenciesActions;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * The Class PomPath returns the path to your projects POM file.
 */

public class PomPath {
	static String filename = "pom.xml";
	static File pom;
	static String absoluteFilePath = "";
	static String workingDirectory;
	static String workingDirectorySecond;
	
	/**
	 * Gets the pom by selecting the workbench, then gets the location of the selected project of that workbench. Then
	 * it adds the "pom.xml" string to the path and returns it. 
	 *
	 * @return the pom
	 */
	//works with new Eclipse instance, returns your project's pom
	public static File getPom(){
		File pomFile = null;
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	    if (window != null)
	    {
	        IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
	        Object firstElement = selection.getFirstElement();
	        if (firstElement instanceof IAdaptable)
	        {
	            IProject project = (IProject)((IAdaptable)firstElement).getAdapter(IProject.class);
	            IPath WorkingDirectoryPath = project.getLocation();
	            System.out.println("Current working directory" + WorkingDirectoryPath);
	            absoluteFilePath = WorkingDirectoryPath + File.separator + filename;
	            pomFile = new File(absoluteFilePath);
	            System.out.println("POM's absolute filepath : " + absoluteFilePath);
	    		
	    		return pomFile;
	        }
	    }
	    return pomFile;
	}
	/*
	public static File getPom2(){
		String workingDirectory = System.getProperty("user.dir");
		System.out.println("Current working directory = " + workingDirectory);
							
		String absoluteFilePath = "";
			
		absoluteFilePath = workingDirectory + File.separator + filename;

		System.out.println("Final filepath : " + absoluteFilePath);
		
		File pomFile = new File(absoluteFilePath);
		
		return pomFile;
	}
	*/
}
