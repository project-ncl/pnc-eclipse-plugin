package com.redhat.eclipseDependencyAnalysis.buildActions;

import java.util.ArrayList;
import java.util.List;

import com.redhat.eclipseDependencyAnalysis.dependenciesActions.MyDep;

/**
 * The Class ContentForNewBC creates content of Build configurations for the Analyse table.
 */
public class ContentForNewBC {
	
	private List<BuildConfiguration> BCs = new ArrayList<BuildConfiguration>();
	
	/**
	 * Instantiates a new content for new build configuration.
	 *
	 * @param myDeps the my deps
	 * @param buildScript the build script
	 */
	public ContentForNewBC(List<MyDep> myDeps, String buildScript){
		for(MyDep item : myDeps){
			if(item.getScmURL() != "" && item.getScmRev() != ""){
				BuildConfiguration separateBC = new BuildConfiguration(item.getArtifactId(), item.getScmURL(), item.getScmRev(), buildScript, item);
				BCs.add(separateBC);
			}
		}
	}

	/**
	 * Gets the build configurations.
	 *
	 * @return the build configurations
	 */
	public List<BuildConfiguration> getBCs() {
		return BCs;
	}
	
	/**
	 * Prints the info about all the build configurations.
	 */
	public void printInfo(){
		for(BuildConfiguration item : BCs){
			System.out.println("Name: " + item.getName());
			System.out.println("SCM REPO: " + item.getScmUrl());
			System.out.println("SCM REV: " + item.getScmRevision());
			System.out.println("Project: " + item.getProject());
			System.out.println("Enviro: " + item.getEnviroment());
			System.out.println("Build Script: " + item.getBuildScript());
			System.out.println("BCId: " + item.getBcId());
			System.out.println("buildTriggerId: " + item.getBuildTriggerId());
			System.out.println("status: " + item.getStatus());
			System.out.println("latestBuildRecordId: " + item.getLatestBuildRecordId());
			
		}
	}

}
