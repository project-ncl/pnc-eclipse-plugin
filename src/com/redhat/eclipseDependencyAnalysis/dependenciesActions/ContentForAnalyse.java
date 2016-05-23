package com.redhat.eclipseDependencyAnalysis.dependenciesActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.redhat.eclipseDependencyAnalysis.jsonHttp.DAJsonHttp;

public class ContentForAnalyse {
	
	/** The array of pom dependencies. */
	public List<Dependency> pomDeps;
	
	/** The array pnc dependencies from DA. */
	public List<String> pncDeps;
	
	/** The array of my dependencies. */
	public List<MyDep> myDeps = new ArrayList<MyDep>();
	
	/**
	 * Instantiates a new content for analyse. Sets pomDeps using PomTransofrm class to get all the dependencies the POM file
	 * includes.
	 */
	public ContentForAnalyse(){
		PomTransform transformation = new PomTransform();
		
		pomDeps = transformation.getProject().getDependencies();
	}
	
	/**
	 * Gets the PNC dependencies versions from DA for the all project's POM dependencies.
	 *
	 * @return the PN cdeps
	 */
	public void getPNCdeps(){
		PomTransform transformation = new PomTransform();
		DAJsonHttp pncConnect = new DAJsonHttp();
		
		pncConnect.http(transformation.pomToJArray().toJSONString());
		pncDeps = pncConnect.versions;
		for(String item : pncDeps){
			System.out.println(item);
		}
	}
	
	/**
	 * Sets only the dependencies in POM. Not including the DA versions. This is used in Analyse WizardPage as a content 
	 * for the table so the DA doesnt have to be called right after the start of the Wizard. 
	 */
	public void setPomDepsOnly(){
		for(Dependency dep : pomDeps){
			MyDep mydep = new MyDep(dep.getGroupId(), dep.getArtifactId(), dep.getVersion());
			myDeps.add(mydep);
		}
	}
	
	/**
	 * After the user clicks the Dependency Analysis button this is sent to the content of the table as an updated content
	 * with DA versions of the dependencies.
	 */
	public void setMyDeps(){
		for(Dependency dep : pomDeps){
			int i = 0;
			MyDep mydep = new MyDep(dep.getGroupId(), dep.getArtifactId(), dep.getVersion(), pncDeps.get(i));
			myDeps.add(mydep);
			i++;
		}
	}
	
	/**
	 * Each pom to json obj. DA requires JArray. Each dependency is put in right format to use static http method of DAJsonHttp class
	 * to get the PNC version of the dependency.
	 */
	public void eachPomToJsonObj(){
	    
	    for (Dependency dep : pomDeps){
	    	JSONArray jArray = new JSONArray();
	    	JSONObject depJSON = new JSONObject();
	    	depJSON.put("version", dep.getVersion());
	    	depJSON.put("artifactId", dep.getArtifactId());
	    	depJSON.put("groupId", dep.getGroupId());
	    	jArray.add(depJSON);
		         
	    	DAJsonHttp.http(jArray.toJSONString());
	         
	    	MyDep mydep = new MyDep(dep.getGroupId(), dep.getArtifactId(), dep.getVersion(), DAJsonHttp.version);
	         
	    	myDeps.add(mydep);
	    }
	}
}
