package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

public class ServerInfo {
	
	private static String URL_GET_PROJECTS = "{SERVER}/rest/api/2/project?expand";
	private static String URL_GET_ISSUETYPES = "{SERVER}/rest/api/2/issuetype";
	private static String URL_ZCLOUD_GET_GENERAL_INFO = "{SERVER}/public/rest/api/1.0/config/generalinformation";
	private static String TEST_ISSSUETYPE_NAME = "Test";
	private static long ISSUE_TYPE_ID = 10100L;
	
	private static String URL_GET_USERS = "{SERVER}/rest/api/2/user?username=";



	public static boolean findServerAddressIsValidZephyrURL(RestClient restClient) {
		boolean status = false;
		
		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_PROJECTS.replace("{SERVER}", restClient.getUrl());
			
			response = restClient.getHttpclient().execute(new HttpGet(constructedURL));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				if (string.startsWith("[") && string.endsWith("]") ) return true;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (statusCode == 401) {
			return false;
		}  else if (statusCode == 404) {
			return false;
		} else{
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return status;
	
	}
	public static long findTestIssueTypeId(RestClient restClient) {
		long status = ISSUE_TYPE_ID;
		
		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_ISSUETYPES.replace("{SERVER}", restClient.getUrl());
			
			HttpGet getRequest = new HttpGet(constructedURL);
			getRequest.addHeader("Content-Type", "application/json");
			getRequest.addHeader("Accept-Encoding", "gzip, deflate, sdch");

			response = restClient.getHttpclient().execute(getRequest, restClient.getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity, "utf-8");
				
				JSONArray jArr = new JSONArray(string);
				
				for (int i = 0; i < jArr.length(); i++) {
					JSONObject jObj = (JSONObject) jArr.getJSONObject(i);
					if (jObj.getString("name").trim().equals(TEST_ISSSUETYPE_NAME)) {
						long testIssueTypeId = jObj.getLong("id");
						return testIssueTypeId;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (statusCode == 401 || statusCode == 404) {
			return status;
		}  else{
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return status;
	
	}
	
	
	public static boolean validateCredentials(RestClient restClient) {
		

		boolean status = true;

		HttpResponse response = null;
		try {
			String constructedURL = URL_GET_USERS.replace("{SERVER}", restClient.getUrl()) + restClient.getUserName();
			response = restClient.getHttpclient().execute(new HttpGet(constructedURL), restClient.getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode == 401) {
			status = false;
		}
		return status;
	
	
	}
	public static Map<Boolean, String> findServerAddressIsValidZephyrCloudURL(RestClient restClient) {
		Map<Boolean, String> statusMap = new HashMap<Boolean, String>();
		statusMap.put(false, "Error Validating Zephyr for JIRA Cloud and ZAPI Cloud");

		HttpResponse response = null;
		try {
			String constructedURL = URL_ZCLOUD_GET_GENERAL_INFO.replace("{SERVER}", restClient.getZephyrCloudURL());
			String jwtHeaderValue = generateJWT(restClient, constructedURL);
			HttpGet getRequest = new HttpGet(constructedURL);
			
			getRequest.addHeader("Content-Type", "application/json");
			getRequest.addHeader("Authorization", jwtHeaderValue);
			getRequest.addHeader("zapiAccessKey", restClient.getAccessKey());

			response = restClient.getHttpclient().execute(getRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();

		if (statusCode == 401) {
			statusMap.put(false, "UnAuthorized");
		} else if (statusCode == 404) {
			statusMap.put(false, "Invalid Zephyr for JIRA Cloud URL");
		} else if (statusCode == 200) {
			
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				if (string.startsWith("{") && string.endsWith("}") && string.contains("Zephyr")) {
					statusMap.put(true, "success");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return statusMap;
	}
	
	private static String generateJWT(RestClient restClient, String constructedURL) {
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(restClient.getZephyrCloudURL(), restClient.getAccessKey(), restClient.getSecretKey(), restClient.getUserName())
				.build();
		JwtGenerator jwtGenerator = client.getJwtGenerator();

		URI uri = null;
		try {
			uri = new URI(constructedURL);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		int expirationInSec = 360;
		String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);
		return jwt;
	}
	
}
