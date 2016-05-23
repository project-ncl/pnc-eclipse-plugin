package com.redhat.eclipseDependencyAnalysis.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * The Class OAuthConnect is used for communication with keycloak server so the user get access to the PNC functions needed. 
 */
public class OAuthConnect {
	
	/**
	 * Gets the access token from keycloak server.
	 *
	 * @param url the url
	 * @param urlParams the url params
	 * @return the access token
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getAccessToken(String url, Map<String, String> urlParams) 
            throws ClientProtocolException, IOException{
        return connect(url, urlParams)[0];
    }

    /**
     * Gets the refresh token from keycloak server. This is used later when there is possibility the user may have been waiting 
     * for a longer time, so the methods won't send expired token 
     *
     * @param url the url
     * @param urlParams the url params
     * @return the refresh token
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getRefreshToken(String url, Map<String, String> urlParams) 
            throws ClientProtocolException, IOException{
        return connect(url, urlParams)[1];
    }
    
    /**
     * Gets the tokens.
     *
     * @param url the url
     * @param urlParams the url params
     * @return the tokens
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String[] getTokens(String url, Map<String, String> urlParams) 
            throws ClientProtocolException, IOException{
        return connect(url, urlParams);
    }
    
    /**
     * Gets the access token.
     *
     * @param url the url
     * @param clientId the client id
     * @param username the username
     * @param password the password
     * @return the access token
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getAccessToken(String url, String clientId, String username, String password) 
            throws ClientProtocolException, IOException{
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("grant_type", "password");
        urlParams.put("client_id", clientId);
        urlParams.put("username", username);
        urlParams.put("password", password);
        return connect(url, urlParams)[0];
    }
    
    /**
     * Gets the refresh token.
     *
     * @param url the url
     * @param clientId the client id
     * @param username the username
     * @param password the password
     * @return the refresh token
     * @throws ClientProtocolException the client protocol exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String getrefreshToken(String url, String clientId, String username, String password) 
            throws ClientProtocolException, IOException{
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("grant_type", "password");
        urlParams.put("client_id", clientId);
        urlParams.put("username", username);
        urlParams.put("password", password);
        return connect(url, urlParams)[1];
    }
    
    /**
     * connects user to the server and returns one of the tokens
     * @param url
     * @param urlParams
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private static String[] connect(String url, Map<String, String> urlParams) 
            throws ClientProtocolException, IOException{
        
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // add header
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        List <BasicNameValuePair> urlParameters = new ArrayList <BasicNameValuePair>();
        for(String key : urlParams.keySet()) {
            urlParameters.add(new BasicNameValuePair(key, urlParams.get(key)));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        String refreshToken = "";
        String accessToken = "";
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                if(line.contains("refresh_token")) {
                    String[] respContent = line.split(",");
                    for (int i = 0; i < respContent.length; i++) {
                        String split = respContent[i];
                        if(split.contains("refresh_token")) {
                            refreshToken = split.split(":")[1].substring(1,split.split(":")[1].length() -1);
                        }
                        if(split.contains("access_token")) {
                            accessToken = split.split(":")[1].substring(1,split.split(":")[1].length() -1);
                        }
                    }
                }
            }

        } finally {
            response.close();
        }
        return new String[] {accessToken,refreshToken};
        
        
    }
}
