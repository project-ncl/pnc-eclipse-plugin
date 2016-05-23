package com.redhat.eclipseDependencyAnalysis.buildActions;

import org.json.simple.JSONObject;

/**
 * The Class CreateEnvironment is to set up the Environment for new build configuration.
 */
public class CreateEnvironment {
		
	/**
	 * Json environment is to setup a JSON Object of the Environment with its ID. It's required for setting up a new Build Configuration.
	 *
	 * @param enviro the enviro
	 * @return the JSON object
	 */
	public static JSONObject jsonEnviro(String enviro){
		JSONObject enviroJSON = new JSONObject();
		enviroJSON.put("id", enviro);
		return enviroJSON;
	}
}
