package com.redhat.eclipseDependencyAnalysis.jsonHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Class DAJsonHttp is for the communication with the DA to get the array of the POM file dependencies provided as an JSON array.
 */
public class DAJsonHttp {
	
	public static List<String> versions = new ArrayList<String>();
	
	public static String version;
	
	public static String url = "http://ncl-da-branch-nightly.stage.engineering.redhat.com/da/rest/v-0.4/reports/lookup/gavs";
    
    /**
     * Http. Static method which the versions of the result of the DA do Array of Strings.
     *
     * @param body the body
     */
    public static void http(String body) {
    	
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("accept", "application/json");
            request.setEntity(params);
            HttpResponse result = httpClient.execute(request);
           
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            try {
                JSONParser parser = new JSONParser();
                Object resultObject = parser.parse(json);

                if (resultObject instanceof JSONArray) {
                    JSONArray array=(JSONArray)resultObject;
                    for (Object object : array) {
                        JSONObject obj =(JSONObject)object;
                        version = obj.get("availableVersions").toString();
                        System.out.println("DA response: " + obj.toString());
                        versions.add(obj.get("availableVersions").toString());
                        System.out.println("Version: " + version);

                    }

                }else if (resultObject instanceof JSONObject) {
                    JSONObject obj =(JSONObject)resultObject;
                    System.out.println(obj.toString());
                    version = obj.get("availableVersions").toString();
                    versions.add(obj.get("availableVersions").toString());
                    System.out.println("Version: " + version);
                }
                
                System.out.println("availableVersions of PNC deps: " + versions);
                System.out.println("Verzia: " + version);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
    
}
