package com.redhat.eclipseDependencyAnalysis.buildActions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.redhat.eclipseDependencyAnalysis.dependenciesActions.MyDep;

/**
 * The Class BuildConfiguration contains all the information about the created and triggered Build Configuration. It also contains
 * it's MyDep dependency for which it was created. 
 */
public class BuildConfiguration {
	private String name;
	private String scmUrl;
	private String scmRevision;
	private String buildScript;
	private String enviroment;
	private String project;
	private String projectId;
	private String bcId;
	private String buildTriggerId;
	private String status;
	private String latestBuildRecordId;
	private MyDep myDep;
	
	/**
	 * Gets the my dep.
	 *
	 * @return the my dep
	 */
	public MyDep getMyDep() {
		return myDep;
	}

	/**
	 * Sets the my dep.
	 *
	 * @param myDep the new my dep
	 */
	public void setMyDep(MyDep myDep) {
		this.myDep = myDep;
	}

	/**
	 * Gets the latest build record id.
	 *
	 * @return the latest build record id
	 */
	public String getLatestBuildRecordId() {
		return latestBuildRecordId;
	}

	/**
	 * Sets the latest build record id.
	 *
	 * @param latestBuildRecordId the new latest build record id
	 */
	public void setLatestBuildRecordId(String latestBuildRecordId) {
		this.latestBuildRecordId = latestBuildRecordId;
	}

	/**
	 * Gets the builds trigger id.
	 *
	 * @return the builds trigger id
	 */
	public String getBuildTriggerId() {
		return buildTriggerId;
	}

	/**
	 * Sets the builds trigger id.
	 *
	 * @param buildTriggerId the new builds trigger id
	 */
	public void setBuildTriggerId(String buildTriggerId) {
		this.buildTriggerId = buildTriggerId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the builds the record id.
	 *
	 * @return the builds the record id
	 */
	public String getBuildRecordId() {
		return buildTriggerId;
	}

	/**
	 * Sets the builds the record id.
	 *
	 * @param buildRecordId the new builds the record id
	 */
	public void setBuildRecordId(String buildRecordId) {
		this.buildTriggerId = buildRecordId;
	}

	private List<BuildConfiguration> BCs = new ArrayList<BuildConfiguration>();
	
	/**
	 * Instantiates a new builds the configuration.
	 *
	 * @param artifactId the artifact id
	 * @param scmURL the scm url
	 * @param scmRevision the scm revision
	 * @param buildScript the build script
	 * @param item the item
	 */
	public BuildConfiguration( String artifactId, String scmURL, String scmRevision, String buildScript, MyDep item){
		this.name = artifactId + "-" + scmRevision + "-" + UUID.randomUUID(); 
		this.scmUrl = scmURL;
		this.scmRevision = scmRevision;
		this.buildScript = buildScript;
		this.project = artifactId;
		this.enviroment = "";
		this.projectId = "";
		this.bcId = "";
		this.buildTriggerId = "";
		this.status = "";
		this.myDep = item;
		this.latestBuildRecordId = "";
		
	}
	/*
	public BuildConfiguration(List<MyDep> myDeps, String buildScript){
		for(MyDep item : myDeps){
			if(item.getScmURL() != "" && item.getScmRev() != ""){
				BuildConfiguration separateBC = new BuildConfiguration(item.getArtifactId(), item.getScmURL(), item.getScmRev(), buildScript, item);
				BCs.add(separateBC);
			}
		}
	}
	*/

	/**
	 * Gets the array of Build Configurations.
	 *
	 * @return the array of Build Configuration
	 */
	public List<BuildConfiguration> getBCs() {
		return BCs;
	}

	/**
	 * Sets the Build Configurations.
	 *
	 * @param bCs the new Build Configurations
	 */
	public void setBCs(List<BuildConfiguration> bCs) {
		BCs = bCs;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the scm url.
	 *
	 * @return the scm url
	 */
	public String getScmUrl() {
		return scmUrl;
	}

	/**
	 * Sets the scm url.
	 *
	 * @param scmUrl the new scm url
	 */
	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	/**
	 * Gets the scm revision.
	 *
	 * @return the scm revision
	 */
	public String getScmRevision() {
		return scmRevision;
	}

	/**
	 * Sets the scm revision.
	 *
	 * @param scmRevision the new scm revision
	 */
	public void setScmRevision(String scmRevision) {
		this.scmRevision = scmRevision;
	}

	/**
	 * Gets the builds the script.
	 *
	 * @return the builds the script
	 */
	public String getBuildScript() {
		return buildScript;
	}

	/**
	 * Sets the builds the script.
	 *
	 * @param buildScript the new builds the script
	 */
	public void setBuildScript(String buildScript) {
		this.buildScript = buildScript;
	}

	/**
	 * Gets the enviroment.
	 *
	 * @return the enviroment
	 */
	public String getEnviroment() {
		return enviroment;
	}

	/**
	 * Sets the enviroment.
	 *
	 * @param enviroment the new enviroment
	 */
	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}

	/**
	 * Gets the project.
	 *
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(String project) {
		this.project = project;
	}
	
	/**
	 * Gets the project id.
	 *
	 * @return the project id
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * Sets the project id.
	 *
	 * @param projectId the new project id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * Gets the bc id.
	 *
	 * @return the bc id
	 */
	public String getBcId() {
		return bcId;
	}

	/**
	 * Sets the bc id.
	 *
	 * @param bcId the new bc id
	 */
	public void setBcId(String bcId) {
		this.bcId = bcId;
	}	
}

