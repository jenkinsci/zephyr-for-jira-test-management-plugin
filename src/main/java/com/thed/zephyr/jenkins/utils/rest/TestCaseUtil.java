package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thed.zephyr.jenkins.model.TestCaseResultModel;
import com.thed.zephyr.jenkins.model.ZephyrConfigModel;

public class TestCaseUtil {
	
    public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;


	private static final String URL_GET_TEST_CASE_DETAILS = "{SERVER}/flex/services/rest/latest/testcase";
	private static final String URL_GET_ALL_TESTS = "{SERVER}/rest/api/2/search";
	private static final String URL_CREATE_TESTS = "{SERVER}/rest/api/2/issue/";
	private static final String URL_ASSIGN_TESTS = "{SERVER}/rest/zapi/latest/execution/addTestsToCycle/";
	private static final String URL_CREATE_EXECUTIONS_URL = "{SERVER}/rest/zapi/latest/execution";
	private static final String URL_EXECUTE_TEST = "{SERVER}/rest/zapi/latest/execution/{id}/quickExecute";
	private static final String JQL_SEARCH_TESTS = "jql=project={pId}&issuetype={issueTypeId}";
	
	private static final String URL_GET_CYCLES = "{SERVER}/rest/zapi/latest/cycle";

	
	private static HttpClientContext getClientContext(String hostAddressWithProtocol, String userName, String password) {
		URL url;
		HttpClientContext context = null;
		try {
			url = new URL(hostAddressWithProtocol);
			HttpHost targetHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(userName, password));
			
			AuthCache authCache = new BasicAuthCache();
			authCache.put(targetHost, new BasicScheme());
			
			context = HttpClientContext.create();
			context.setCredentialsProvider(credsProvider);
			context.setAuthCache(authCache);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return context;
	}
	
	
	public static Map<Long, Map<String, Boolean>> getTestCaseDetails(ZephyrConfigModel zephyrData) {


		Map<Long, Map<String, Boolean>> testCaseResultMap = new HashMap<Long, Map<String, Boolean>>();
		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		List<TestCaseResultModel> testCases = zephyrData.getTestcases();
		if (testCases == null || testCases.size() == 0) {
			return testCaseResultMap;
		}
		
		
		for (Iterator<TestCaseResultModel> iterator = testCases.iterator(); iterator.hasNext();) {
			TestCaseResultModel testCaseWithStatus = (TestCaseResultModel) iterator
					.next();
			
		String url = null;
		
		String searchJQL = JQL_SEARCH_TESTS;
		searchJQL = searchJQL.replace("{pId}", zephyrData.getZephyrProjectId()+"");
		searchJQL = searchJQL.replace("{issueTypeId}", zephyrData.getTestIssueTypeId()+"");
		
		url = URL_GET_ALL_TESTS.replace("{SERVER}", hostAddressWithProtocol) + "?" + searchJQL;
		try {
			response = client.execute(new HttpGet(url), context);
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
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				
				JSONObject testCaseIssues = new JSONObject(string);
				
				JSONArray jsonArray = testCaseIssues.getJSONArray("issues");
				
				if(jsonArray != null && jsonArray.length() > 0 ) {
					
					boolean exists = false;
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String testName = jsonObject.getJSONObject("fields").getString("summary");
						long testId = jsonObject.getLong("id");
						String testKey = jsonObject.getString("key");
						
						if (testName.trim() != null && testName.trim().equals(testCaseWithStatus.getTestCaseName())) {
							Map<String, Boolean> map = new HashMap<String, Boolean>();
							map.put(testKey, testCaseWithStatus.getPassed());
							testCaseResultMap.put(testId, map);
							exists = true;
							break;
						}
					}
					
					if (!exists) {

						String createURL = URL_CREATE_TESTS.replace("{SERVER}", hostAddressWithProtocol);
						HttpPost httpPost = new HttpPost(createURL);
						httpPost.addHeader("Content-Type", "application/json");
						
						StringEntity se = new StringEntity(testCaseWithStatus.getTestCase());
						httpPost.setEntity(se);
						
						HttpResponse response1 = client.execute(httpPost, context);
						
						int statusCode1 = response1.getStatusLine().getStatusCode();

						if (statusCode1 >= 200 && statusCode1 < 300) {
							HttpEntity entity1 = response1.getEntity();
							String string1 = null;
							try {
								string1 = EntityUtils.toString(entity1);
							} catch (ParseException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						if (StringUtils.isNotBlank(string1)) {
							
							JSONObject jObject = new JSONObject(string1);
							long id = jObject.getLong("id");
							String testKey = jObject.getString("key");
							Map<String, Boolean> map = new HashMap<String, Boolean>();
							map.put(testKey, testCaseWithStatus.getPassed());
							testCaseResultMap.put(id, map);
						}
							
						}
					
					}
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
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
	
	}
		return testCaseResultMap;
	}
	
	public static void findOrAddTestcase(String[] args) {

		ZephyrConfigModel zephyrData = new ZephyrConfigModel();
		zephyrData.setZephyrURL("http://localhost:1212/");
		zephyrData.setUserName("vm_admin");
		zephyrData.setPassword("minimini");
		
		TestCaseResultModel caseWithStatus = new TestCaseResultModel();
		caseWithStatus.setPassed(true);
		caseWithStatus.setTestCaseName("A Mohan Test Case 1");
		caseWithStatus.setTestCase("{\"fields\":{\"summary\":\"A Mohan Test Case 1\",\"project\":{\"id\":\"10000\"},\"issuetype\":{\"id\":\"10001\"},\"description\":\"Creating of a Test via Jenkins\"}}");
		
		List<TestCaseResultModel> list = new ArrayList<TestCaseResultModel>();
		list.add(caseWithStatus);
		zephyrData.setTestcases(list);
		getTestCaseDetails(zephyrData);
	}
	
	public static Map<Long, String> getAllCyclesByReleaseID(ZephyrConfigModel zephyrData) {


		Map<Long, String> cycles = new TreeMap<Long, String>();
		
		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		
		final String url = URL_GET_CYCLES.replace("{SERVER}", hostAddressWithProtocol) + "?projectId=" + zephyrData.getZephyrProjectId() + "&versionId=" + zephyrData.getReleaseId();
		try {
			response = client.execute(new HttpGet(url), context);
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
						if (!key.trim().equals("-1")) {
							JSONObject cycleObject = projObj.getJSONObject(key);
							String cycleName = cycleObject.getString("name");
							long id = Long.parseLong(key);
							cycles.put(id, cycleName);
						}
					}
					
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		} else {
			
			cycles.put(0L, "No Cycle");
			try {
				throw new ClientProtocolException("Unexpected response status: "
						+ statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		return cycles;
	}

	public static Long assignTests(ZephyrConfigModel zephyrData, JSONObject jsonObject) {

		Long cycleId = 0L;

		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			String assignTestsToCycleURL = URL_ASSIGN_TESTS.replace("{SERVER}", hostAddressWithProtocol);
			
		
			StringEntity se = new StringEntity(jsonObject.toString());
			
			HttpPost createCycleRequest = new HttpPost(assignTestsToCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = client.execute(createCycleRequest, context);
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
	
	public static Long createExecutions(ZephyrConfigModel zephyrData, JSONObject jsonObject) {

		Long executionId = 0L;

		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			String assignTestsToCycleURL = URL_CREATE_EXECUTIONS_URL.replace("{SERVER}", hostAddressWithProtocol);
			
		
			StringEntity se = new StringEntity(jsonObject.toString());
			
			HttpPost createCycleRequest = new HttpPost(assignTestsToCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = client.execute(createCycleRequest, context);
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
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject cycleObj = new JSONObject(string);
				executionId = Long.parseLong((String) cycleObj.keys().next());
				
			} catch (JSONException e) {
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
	
		return executionId;
	}
	
	public static Long executeTests(ZephyrConfigModel zephyrData, long executionId, boolean status) {


		String hostAddressWithProtocol = zephyrData.getZephyrURL();
		HttpClientContext context = getClientContext(hostAddressWithProtocol, zephyrData.getUserName(), zephyrData.getPassword());
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			String assignTestsToCycleURL = URL_EXECUTE_TEST.replace("{SERVER}", hostAddressWithProtocol).replace("{id}", executionId+"");
			
			JSONObject executionJObj = new JSONObject();
			
			if(status) {
				executionJObj.put("status", 1);
			} else {
				executionJObj.put("status", 2);
			}
			
		
			StringEntity se = new StringEntity(executionJObj.toString());
			
			HttpPost createCycleRequest = new HttpPost(assignTestsToCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = client.execute(createCycleRequest, context);
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
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			
			try {
				JSONObject cycleObj = new JSONObject(string);
				executionId = Long.parseLong((String) cycleObj.keys().next());
				
			} catch (JSONException e) {
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
	
		return executionId;
	}
	public static void processTestCaseDetails(ZephyrConfigModel zephyrData) {
		Map<Long, Map<String, Boolean>> testCaseDetails = getTestCaseDetails(zephyrData);
		
		long cycleId;
		if(zephyrData.getCycleId() == NEW_CYCLE_KEY_IDENTIFIER) {
			cycleId = Cycle.createCycle(zephyrData);
		} else {
			Cycle.deleteCycle(zephyrData);
			cycleId = Cycle.createCycle(zephyrData);
		}
		
		zephyrData.setCycleId(cycleId);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("versionId", zephyrData.getReleaseId());
		jsonObject.put("cycleId", zephyrData.getCycleId());
		jsonObject.put("projectId", zephyrData.getZephyrProjectId());
		jsonObject.put("method", "1");
		
		JSONArray jArr = new JSONArray();
		
		JSONObject createExecutionsJObj = new JSONObject();
		Set<Entry<Long, Map<String, Boolean>>> entrySet = testCaseDetails.entrySet();
		for (Iterator<Entry<Long, Map<String, Boolean>>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Long, Map<String, Boolean>> entry = iterator
					.next();
			Map<String, Boolean> value = entry.getValue();
			Set<String> keySet = value.keySet();
			for (Iterator<String> iterator2 = keySet.iterator(); iterator2.hasNext();) {
				String issueKey = iterator2.next();
				
				jArr.put(issueKey);
			}
		}
		
		jsonObject.put("issues", jArr);

		assignTests(zephyrData, jsonObject);
		
		for (Iterator<Entry<Long, Map<String, Boolean>>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Long, Map<String, Boolean>> entry = iterator
					.next();
			Map<String, Boolean> value = entry.getValue();
			Set<String> keySet = value.keySet();
			for (Iterator<String> iterator2 = keySet.iterator(); iterator2.hasNext();) {
				String issueKey = iterator2.next();
				
				createExecutionsJObj.put("issueId", entry.getKey());
				createExecutionsJObj.put("versionId", zephyrData.getReleaseId());
				createExecutionsJObj.put("cycleId", zephyrData.getCycleId());
				createExecutionsJObj.put("projectId", zephyrData.getZephyrProjectId());
				

				Long executionId = createExecutions(zephyrData, createExecutionsJObj);
				executeTests(zephyrData, executionId, value.get(issueKey));
				
			}
		}
	}
}
