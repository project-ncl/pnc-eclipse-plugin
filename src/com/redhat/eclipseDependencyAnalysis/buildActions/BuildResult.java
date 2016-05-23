package com.redhat.eclipseDependencyAnalysis.buildActions;

/**
 * The Class BuildResult is to collect the data to be shown on the Build Wizard pages tables.
 */
public class BuildResult {
	
	public String dependencyName;
	
	public String versions;
	
	public String buildConfiguration;
	
	public String buildStatus;
	
	
	/**
	 * Gets the dependency name.
	 *
	 * @return the dependency name
	 */
	public String getDependencyName() {
		return dependencyName;
	}


	/**
	 * Sets the dependency name.
	 *
	 * @param dependencyName the new dependency name
	 */
	public void setDependencyName(String dependencyName) {
		this.dependencyName = dependencyName;
	}


	/**
	 * Instantiates the result of the build configuration and its trigger. Dependency name is from MyDep and it's set as group Id and
	 * artifact Id of the deprendency. Build Configuration is the URL with the id of the record and status is the resulting status
	 * of the build. 
	 *
	 * @param arfId the arf id
	 * @param grpId the grp id
	 * @param versions the versions
	 * @param buidConfiguration the buid configuration
	 * @param buildStatus the build status
	 */
	public BuildResult(String arfId, String grpId, String versions, String buidConfiguration, String buildStatus){
		this.dependencyName = arfId + ":" + grpId;
		this.versions = versions;
		this.buildConfiguration = "http://ncl-demo.stage.engineering.redhat.com/pnc-rest/rest/build-records/" + buidConfiguration;
		this.buildStatus = buildStatus;
	}


	/**
	 * Gets the versions.
	 *
	 * @return the versions
	 */
	public String getVersions() {
		return versions;
	}


	/**
	 * Sets the versions.
	 *
	 * @param versions the new versions
	 */
	public void setVersions(String versions) {
		this.versions = versions;
	}


	/**
	 * Gets the builds the configuration.
	 *
	 * @return the builds the configuration
	 */
	public String getBuildConfiguration() {
		return buildConfiguration;
	}


	/**
	 * Sets the builds the configuration.
	 *
	 * @param buildConfiguration the new builds the configuration
	 */
	public void setBuildConfiguration(String buildConfiguration) {
		this.buildConfiguration = buildConfiguration;
	}


	/**
	 * Gets the builds the status.
	 *
	 * @return the builds the status
	 */
	public String getBuildStatus() {
		return buildStatus;
	}


	/**
	 * Sets the builds the status.
	 *
	 * @param buildStatus the new builds the status
	 */
	public void setBuildStatus(String buildStatus) {
		this.buildStatus = buildStatus;
	}
	
	
}
