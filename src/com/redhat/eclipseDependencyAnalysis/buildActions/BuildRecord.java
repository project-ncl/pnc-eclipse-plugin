package com.redhat.eclipseDependencyAnalysis.buildActions;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Class BuildRecord is to resolve if the trigger of the build is done. 
 */
public class BuildRecord {
	private String url = "http://ncl-demo.stage.engineering.redhat.com/pnc-rest/rest/build-configurations/";
	private String status;
	private String latestBuildRecordId;
	
	/**
	 * Http method sets the URL with an id of the BC its called for. HttpGet returns empty JSON object if the trigger isn't finished yet.
	 * If it isn't empty it collect the status of the build and it's id.
	 *
	 * @param id the id
	 * @param token the token
	 * @return true, if successful
	 */
	public boolean http(String id, String token) {
		System.out.println("Get Build Record http method called");
		String secondURL = url + id + "/build-records/latest";
		System.out.println(secondURL);
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(secondURL);

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
                        if(obj.isEmpty()){
                        	return false;
                        } else{
                        	latestBuildRecordId = ((JSONObject) obj.get("content")).get("id").toString();
                        	status = ((JSONObject) obj.get("content")).get("status").toString();
                        	return true;
                        }
                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    if(obj.isEmpty()){
                    	return false;
                    } else {
                    	latestBuildRecordId = ((JSONObject) obj.get("content")).get("id").toString();
                    	status = ((JSONObject) obj.get("content")).get("status").toString();
                    	return true;
                    }
                }
            } catch (Exception e) {
            	result.toString();
            	System.out.println("Probem with parsing buid record response");
                e.printStackTrace();
                return false;
            }

        } catch (IOException ex) {
        	System.out.println("Problem with buid record response");
        	ex.printStackTrace();
        	return false;
        }
		return false;
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
	
	

}
