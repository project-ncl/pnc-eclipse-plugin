package com.redhat.eclipseDependencyAnalysis.jsonHttp;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Class TriggerBuild has an HTTP POST method that sends request on an URL containing a build configuration specific ID.
 */
public class TriggerBuild {
	
	public static String url = "http://ncl-demo.stage.engineering.redhat.com/pnc-rest/rest/build-configurations/";
	private String buildTriggerId;
	
	/**
	 * Creates JSON Object of and existing Build Configuration with only an ID so it can be sent to be triggered.
	 *
	 * @param id the id
	 * @return the JSON object
	 */
	public static JSONObject triggerBuildjson(String id){
		JSONObject triggerJSON = new JSONObject();
		triggerJSON.put("id", id);
		System.out.println("String of Json Object with id to be triggerred: " + triggerJSON);
		return triggerJSON;
	}
	
	/**
	 * Http method post on an URL containing an ID of a build configuration which needs to be triggered. 
	 *
	 * @param id the id
	 * @param body the body
	 * @param token the token
	 */
	public void http(String id, String body, String token) {
		System.out.println("trigger build http method called");
		String secondURL = url + id + "/build";
		System.out.println(secondURL);
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(secondURL);
            
            request.addHeader("content-type", "application/json");
            request.addHeader("accept", "application/json");
            request.addHeader("authorization", "Bearer " + token);
            request.addHeader("cache-control", "no-cache");
            
            HttpResponse result = httpClient.execute(request);
           
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            System.out.println(json);

            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);
                

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        System.out.println("PNC response of trigger request: " + obj.toString());
                        buildTriggerId = ((JSONObject) obj.get("content")).get("id").toString();
                        System.out.println("Build-trigger ID: " + buildTriggerId);
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println("PNC response of trigger request: " + obj.toString());
                    buildTriggerId = ((JSONObject) obj.get("content")).get("id").toString();
                    System.out.println("Build-trigger ID: " + buildTriggerId);
                }
            } catch (Exception e) {
            	result.toString();
            	System.out.println("Probem with parsing trigger response");
                e.printStackTrace();
            }

        } catch (IOException ex) {
        	System.out.println("Problem with trigger response");
        	ex.printStackTrace();
        }
    }

	/**
	 * Gets the trigger id.
	 *
	 * @return the trigger id
	 */
	public String getbuildTriggerId() {
		return buildTriggerId;
	}
	
}
