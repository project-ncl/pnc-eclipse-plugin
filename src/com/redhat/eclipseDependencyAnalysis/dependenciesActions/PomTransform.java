package com.redhat.eclipseDependencyAnalysis.dependenciesActions;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The Class PomTransform is for transforming POM to MavenProject or transforming MavenProject dependencies to JArray.
 */
public class PomTransform {
	
	public File pomFile = null;
	
	public MavenProject project;
	
	/**
	 * Instantiates a new pom transform and sets up the POM as MavenProject.
	 */
	public PomTransform(){
		pomAsMvnProject();
	}
	
	/**
	 * returns your POM as MavenProject to be read.
	 *
	 * @return the project
	 */
	
	public MavenProject getProject(){
		return project;
	}
	
	/**
	 * sets your POM as Maven Project.
	 */
	public void pomAsMvnProject(){
		pomFile = PomPath.getPom();
		
		Model model = null;
		FileReader reader = null;
		MavenXpp3Reader mavenreader = new MavenXpp3Reader();
		
		try {
			reader = new FileReader(pomFile);
			
		    model = mavenreader.read(reader);

		    model.setPomFile(pomFile);
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		project = new MavenProject(model);
	}
	
	/**
	 * sets your POM deps version, artifactId and groupId to JSON array so it can be sent to analysis.
	 *
	 * @return the JSON array
	 */
	public JSONArray pomToJArray(){
		List<Dependency> deps = this.project.getDependencies();
        
		try
		{
		    JSONArray jArray = new JSONArray();
		    for (Dependency dep : deps)
		    {
		         JSONObject depJSON = new JSONObject();
		         depJSON.put("version", dep.getVersion());
		         depJSON.put("artifactId", dep.getArtifactId());
		         depJSON.put("groupId", dep.getGroupId());
		         jArray.add(depJSON);
		    }
		    System.out.println("String of dependencies to be sent to DA: " + jArray.toJSONString());
		    return jArray;
		} catch (Exception e) {
		    e.printStackTrace();
			return null;
		}
	}

}
