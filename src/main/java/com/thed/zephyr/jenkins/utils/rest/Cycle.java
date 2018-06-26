package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thed.zephyr.jenkins.model.ZephyrConfigModel;

public class Cycle implements RestBase {

    public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;

	private static PrintStream logger;
	private static String URL_GET_CYCLES = "{SERVER}/rest/zapi/latest/cycle?projectId={projectId}&versionId={versionId}";
	private static String URL_CREATE_CYCLES = "{SERVER}/rest/zapi/latest/cycle";
	private static String URL_DELETE_CYCLE = "{SERVER}/rest/zapi/latest/cycle/{id}";

	public static void setLogger(PrintStream _logger) {
		logger = _logger;
	}

	public static Long createCycle(ZephyrConfigModel zephyrData) {

		Long cycleId = 0L;

		HttpResponse response = null;
		try {
			String createCycleURL = URL_CREATE_CYCLES.replace("{SERVER}", zephyrData.getRestClient().getUrl());
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E dd, yyyy hh:mm a");
			String dateFormatForCycleCreation = sdf.format(date);

			JSONObject jObject = new JSONObject();
			String cycleName = zephyrData.getCyclePrefix() + dateFormatForCycleCreation;
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("d/MMM/yy");
			String startDate = sdf1.format(date);
			
			GregorianCalendar gCal = new GregorianCalendar();

			if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("30 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +29);
			} else if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("7 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +6);
			}

			String endDate = sdf1.format(gCal.getTime());
			
			jObject.put("name", cycleName);
			jObject.put("projectId", zephyrData.getZephyrProjectId());
			jObject.put("versionId", zephyrData.getVersionId());
			jObject.put("startDate", startDate);
			jObject.put("endDate", endDate);
			jObject.put("build", zephyrData.getBuilNumber());
			
			
			StringEntity se = new StringEntity(jObject.toString(), "utf-8");
			
			HttpPost createCycleRequest = new HttpPost(createCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		int statusCode = 0;
		if (response != null && response.getStatusLine() != null) {
			statusCode = response.getStatusLine().getStatusCode();
		}

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject cycleObj = new JSONObject(string);
				cycleId = cycleObj.getLong("id");
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else if(statusCode == 405) {

			try {
				throw new ClientProtocolException("ZAPI plugin license is invalid"
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycleId;
	}
	
	public static Long deleteCycle(ZephyrConfigModel zephyrData) {

		Long cycleId = 0L;

		HttpResponse response = null;
		try {
			String deleteCycleURL = URL_DELETE_CYCLE.replace("{SERVER}", zephyrData.getRestClient().getUrl()).replace("{id}", zephyrData.getCycleId()+"");
			
			HttpDelete createCycleRequest = new HttpDelete(deleteCycleURL);
			
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = 0;
		if ( response != null && response.getStatusLine() != null) {
			statusCode = response.getStatusLine().getStatusCode();
		}
		
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycleId;
	}
	
	public static Map<String, String> getAllCyclesByVersionId(long versionId, RestClient restClient, String projectId) {


		Map<String, String> cycles = new TreeMap<String, String>();
		
		HttpResponse response = null;
		
		final String url = URL_GET_CYCLES.replace("{SERVER}", restClient.getUrl()).replace("{projectId}", projectId).replace("{versionId}", versionId+"");
		try {
			response = restClient.getHttpclient().execute(new HttpGet(url), restClient.getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = 0;
		if ( response != null && response.getStatusLine() != null) {
			statusCode = response.getStatusLine().getStatusCode();
		}

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject projObj = new JSONObject(string);
				for(int i = 0; i < projObj.length(); i++) {
					Iterator<String> keys = projObj.keys();
					
					while (keys.hasNext()) {
						String key = (String) keys.next();
						if (/*!key.trim().equals("-1") && */!key.trim().equals("recordsCount")) {
							JSONObject cycleObject = projObj.getJSONObject(key);
							String cycleName = cycleObject.getString("name");
							long id = Long.parseLong(key);
							cycles.put(id+"", cycleName);
						}
					}
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
	
		return cycles;
	}
	
	
	public static Map<String, String> getAllCyclesByVersionIdZFJC(long versionId, RestClient restClient, String projectId) {
		Map<String, String> cycles = new TreeMap<String, String>();

		HttpResponse response = null;
		try {
			String constructedURL = URL_ZFJC_GET_CYCLES_BY_PROJECT_AND_VERSION.replace(ZFJC_SERVER, restClient.getZephyrCloudURL()).replace(PROJECTID, projectId+"").replace(VERSIONID, versionId+"");
			String jwtHeaderValue = ServerInfo.generateJWT(restClient, constructedURL, HTTP_REQUEST_METHOD_GET);
			HttpGet getRequest = new HttpGet(constructedURL);
			
			getRequest.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_TEXT);
			getRequest.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
			getRequest.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());

			response = restClient.getHttpclient().execute(getRequest);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = 0;
		if ( response != null && response.getStatusLine() != null) {
			statusCode = response.getStatusLine().getStatusCode();
		}

		if (statusCode == 400) {
			System.out.println("Bad request. Please check content type and other request parameters");
		} else if (statusCode == 401) {
			System.out.println("UnAuthorized");
		} else if (statusCode == 404) {
			System.out.println("Invalid Zephyr for JIRA Cloud URL");
		} else if (statusCode == 200) {
			
			HttpEntity entity = response.getEntity();
			String stringCycles = null;
			try {
				stringCycles = EntityUtils.toString(entity);
				if (stringCycles != null && stringCycles.startsWith(JSON_ARRAY_START_CHAR) && stringCycles.endsWith(JSON_ARRAY_END_CHAR) && stringCycles.contains("tenantKey")) {
					
					try {
						JSONArray projObj = new JSONArray(stringCycles);
						for(int i = 0; i < projObj.length(); i++) {
							JSONObject cycleObj = projObj.getJSONObject(i);
							
									String cycleName = cycleObj.getString("name");
									String id = cycleObj.getString("id");
									cycles.put(id, cycleName);
						}
						
						
					} catch (JSONException e) {
						e.printStackTrace();
					}

					
				} else {
					System.out.println("Invalid cycle response : " + stringCycles);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return cycles;
	}
	
	public static void main(String[] args) {
		String jiraCloudAddress = "https://justforkix.atlassian.net";
		String jiraCloudUserName = "zal@getzephyr.com";
		String jiraCloudPassword = "Gr00v!12";
		String zephyrCloudAddress = "https://prod-api.zephyr4jiracloud.com/connect";
		String zephyrCloudAccessKey = "amlyYTpmMDJhZTJkZC1mZmE5LTQ5NWYtYTRiYy1lZTkyMzkyYjIwMzggemFsQGdldHplcGh5ci5jb20";
		String zephyrCloudSecretKey = "R8h5WK6TUSkiU2QmTnCGwn1r9M2nF0elb2Ll1gygAMI";

		RestClient restClient  = new RestClient(jiraCloudAddress, jiraCloudUserName, jiraCloudPassword, zephyrCloudAddress, zephyrCloudAccessKey, zephyrCloudSecretKey);

//		Map<String, String> allCyclesByVersionIdZFJC = getAllCyclesByVersionIdZFJC(10901L, restClient, "10703");
//		
//		System.out.println(allCyclesByVersionIdZFJC);
		
		long zephyrProjectId = 10703;
		long versionId = 10900;
		ZephyrConfigModel zephyrData = new ZephyrConfigModel();
		zephyrData.setRestClient(restClient);
		zephyrData.setCyclePrefix("TestPrefix1");
		zephyrData.setCycleDuration("7 days");
		zephyrData.setZephyrProjectId(zephyrProjectId);
		zephyrData.setVersionId(versionId);
		zephyrData.setZfjClud(true);
		
		String createCycleZFJC = createCycleZFJC(zephyrData);
		
		zephyrData.setCycleIdZfjCloud(createCycleZFJC);
		
		System.out.println("createCycleZFJC : " + createCycleZFJC);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("versionId", zephyrData.getVersionId());
		jsonObject.put("cycleId", zephyrData.getCycleIdZfjCloud());
		jsonObject.put("projectId", zephyrData.getZephyrProjectId());
		jsonObject.put("method", "1");
		jsonObject.put("assigneeType", "currentUser");

		JSONArray jArr = new JSONArray();
		jArr.put("SIP-881");
		jArr.put("SIP-882");
		jsonObject.put("issues", jArr);

		TestCaseUtil.assignTestsZFJC(zephyrData, jsonObject);
		
		Map<String, String> fetchExecutionIdsZFJC = new HashMap<String, String>();
		
				//TestCaseUtil.fetchExecutionIdsZFJC(zephyrData, jsonObject, fetchExecutionIdsZFJC, 0);
		
		System.out.println("fetchExecutionIdsZFJC : " + fetchExecutionIdsZFJC);
		
	}

	public static String createCycleZFJC(ZephyrConfigModel zephyrData) {


		String cycleId = null;

		HttpResponse response = null;
		try {
			
			RestClient restClient = zephyrData.getRestClient();
			String createCycleURL = URL_ZFJC_CREATE_CYCLE.replace(ZFJC_SERVER, restClient.getZephyrCloudURL());
			System.out.println("CreateCycleUrl: " + createCycleURL);
			String jwtHeaderValue = ServerInfo.generateJWT(restClient, createCycleURL, HTTP_REQUEST_METHOD_POST);
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("E dd, yyyy hh:mm a");
			String dateFormatForCycleCreation = sdf.format(date);

			JSONObject jObject = new JSONObject();
			String cycleName = zephyrData.getCyclePrefix() + dateFormatForCycleCreation;			
			GregorianCalendar gCal = new GregorianCalendar();

			if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("30 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +29);
			} else if (zephyrData.getCycleDuration().trim().equalsIgnoreCase("7 days")) {
				gCal.add(Calendar.DAY_OF_MONTH, +6);
			}
		
			jObject.put("name", cycleName);
			jObject.put("projectId", zephyrData.getZephyrProjectId());
			jObject.put("versionId", -1);
			jObject.put("startDate", date.getTime());
			jObject.put("endDate", gCal.getTimeInMillis());
			
			StringEntity se = new StringEntity(jObject.toString(), "utf-8");
			System.out.println(se);
			System.out.println(cycleName);
			HttpPost createCycleRequest = new HttpPost(createCycleURL);
			
			createCycleRequest.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
			createCycleRequest.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
			createCycleRequest.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());

			createCycleRequest.setEntity(se);
			response = restClient.getHttpclient().execute(createCycleRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = 0;
		if (response != null && response.getStatusLine() != null) {
			statusCode = response.getStatusLine().getStatusCode();
		}		

		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				logger.println(string);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject cycleObj = new JSONObject(string);
				cycleId = cycleObj.getString("id");
				logger.println(cycleId);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else	if (statusCode == 401) {
			HttpEntity entity = response.getEntity();
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				logger.println(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				throw new ClientProtocolException("Unauthorized. Please check your accessKey and secretKey are valid"
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}

		}	else if(statusCode == 405) {

			try {
				throw new ClientProtocolException("ZAPI plugin license is invalid"
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycleId;
	
	}
}
