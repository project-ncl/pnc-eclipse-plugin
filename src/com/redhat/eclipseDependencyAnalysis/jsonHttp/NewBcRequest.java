package com.redhat.eclipseDependencyAnalysis.jsonHttp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.redhat.eclipseDependencyAnalysis.buildActions.BuildConfiguration;
import com.redhat.eclipseDependencyAnalysis.buildActions.CreateEnvironment;

/**
 * The Class NewBcRequest handles creating a new Build Configuration.
 */
public class NewBcRequest {
	
	public String bcId;
	
	public static String url = "http://ncl-demo.stage.engineering.redhat.com/pnc-rest/rest/build-configurations";
	
	/**
	 * Creates JSON Object of and existing Build Configuration so it can be sent to be resolved.
	 *
	 * @param bc the bc
	 * @return the JSON object
	 */
	public static JSONObject jsonObj(BuildConfiguration bc){
		JSONObject bcJSON = new JSONObject();
		bcJSON.put("name", bc.getName());
		bcJSON.put("buildScript", bc.getBuildScript());
		bcJSON.put("scmRepoURL", bc.getScmUrl());
		bcJSON.put("scmRevision", bc.getScmRevision());
		bcJSON.put("project", BCProject.json("id", bc.getProjectId()));
		bcJSON.put("environment", CreateEnvironment.jsonEnviro("1"));
		
		System.out.println("String of BC to be sent: " + bcJSON);
		return bcJSON;
	}
	
	
	/**
	 * Http method creates the build configuration
	 *
	 * @param body the body
	 * @param token the token
	 */
	public void http(String body, String token) {
		System.out.println("crete new build configuration http method called");
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("accept", "application/json");
            request.addHeader("authorization", "Bearer " + token);
            request.addHeader("cache-control", "no-cache");
            request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
           
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(json);
            System.out.println("lets try to parse");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println("PNC response on creating new build configuration: " + obj.toString());
                        bcId = obj.get("id").toString();
                        System.out.println("Build configuration ID: " + bcId);
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println("PNC response on creating new build configuration: " + obj.toString());
                    bcId = ((JSONObject) obj.get("content")).get("id").toString();
                    //bcId = obj.get("id").toString();
                    System.out.println("Build configuration ID: " + bcId);
                }
            } catch (Exception e) {
            	result.toString();
            	System.out.println("probem with parsing create bc configuration!");
                e.printStackTrace();
            }

        } catch (IOException ex) {
        	System.out.println("problem with build configuration");
        	ex.printStackTrace();
        }
    }


	/**
	 * Gets the build configurations id.
	 *
	 * @return the bc id
	 */
	public String getBcId() {
		return bcId;
	}
	
	
}
