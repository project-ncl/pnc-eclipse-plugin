package com.redhat.eclipseDependencyAnalysis.dependenciesActions;

/**
 * The Class MyDep stands for My Dependency and is used for collecting data from POM file and user input data such as scmURL nad scmRev.
 * Also collects version from Dependency Analysis.
 */
public class MyDep{
	private String groupId;
	private String artifactId;
	private String version;
	private String pncVersion;
	private String scmURL;
	private String scmRev;
	
	/**
	 * Instantiates a new my dep also with the information about the PNC version of the dependency.
	 *
	 * @param groupId the group id
	 * @param artifactId the artifact id
	 * @param version the version
	 * @param pncVersion the pnc version
	 */
	public MyDep(String groupId, String artifactId, String version, String pncVersion){
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.pncVersion = pncVersion;
		this.scmURL = "";
		this.scmRev = "";
	}
	
	/**
	 * Instantiates a new plain only with data from POM file my dep.
	 *
	 * @param groupId the group id of the dependency from POM file
	 * @param artifactId the artifact id of the dependency from POM file
	 * @param version the version of the dependency from POM file
	 */
	public MyDep(String groupId, String artifactId, String version){
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = "";
		this.pncVersion = "";
		this.scmURL = "";
		this.scmRev = "";
	}

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * Gets the artifact id.
	 *
	 * @return the artifact id
	 */
	public String getArtifactId() {
		return artifactId;
	}

	/**
	 * Sets the artifact id.
	 *
	 * @param artifactId the new artifact id
	 */
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the pnc version.
	 *
	 * @return the pnc version
	 */
	public String getPncVersion() {
		return pncVersion;
	}

	/**
	 * Sets the pnc version.
	 *
	 * @param pncVersion the new pnc version
	 */
	public void setPncVersion(String pncVersion) {
		this.pncVersion = pncVersion;
	}

	/**
	 * Gets the scm url.
	 *
	 * @return the scm url
	 */
	public String getScmURL() {
		return scmURL;
	}

	/**
	 * Sets the scm url.
	 *
	 * @param scmURL the new scm url
	 */
	public void setScmURL(String scmURL) {
		this.scmURL = scmURL;
	}

	/**
	 * Gets the scm rev.
	 *
	 * @return the scm rev
	 */
	public String getScmRev() {
		return scmRev;
	}

	/**
	 * Sets the scm rev.
	 *
	 * @param scmRev the new scm rev
	 */
	public void setScmRev(String scmRev) {
		this.scmRev = scmRev;
	}
	
	
	
}
