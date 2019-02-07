package com.thed.zephyr.jenkins.utils.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.thed.zephyr.jenkins.model.TestCaseResultModel;
import com.thed.zephyr.jenkins.model.ZephyrConfigModel;

import java.io.PrintStream;
import hudson.model.BuildListener;

public class TestCaseUtil implements RestBase {
	
    public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;
    private static final int MAX_BULK_OPERATION_COUNT = 50;
    private static final int MAX_BULK_ISSUE_CREATE_COUNT = 50;
    private static final int MAX_BULK_ISSUE_SEARCH_COUNT = 250;
    
    private static final int MAX_ALLOWED_EXECUTION_ID_FETCH = 50;



	private static final String URL_GET_ALL_TESTS = "{SERVER}/rest/api/2/search";
	private static final String URL_CREATE_TESTS = "{SERVER}/rest/api/2/issue/bulk";
	private static final String URL_ASSIGN_TESTS = "{SERVER}/rest/zapi/latest/execution/addTestsToCycle/";
	private static final String URL_CREATE_EXECUTIONS_URL = "{SERVER}/rest/zapi/latest/execution?projectId={projectId}&versionId={versionId}&cycleId={cycleId}";
	private static final String URL_EXECUTE_TEST = "{SERVER}/rest/zapi/latest/execution/updateBulkStatus";
	private static final String JQL_SEARCH_TESTS = "jql=project={pId}&issuetype={issueTypeId}&maxResults=" + MAX_BULK_ISSUE_SEARCH_COUNT + "&startAt={searchIssueStartcount}";
	private static final String URL_JOB_PROGRESS_bulkexecute = "{SERVER}/rest/zapi/latest/execution/jobProgress/{jobProgressToken}?type=update_bulk_execution_status_job_progress";
	private static final String URL_GET_CYCLES = "{SERVER}/rest/zapi/latest/cycle";
	private static final String URL_JOB_PROGRESS = "{SERVER}/rest/zapi/latest/execution/jobProgress/{jobProgressToken}?type=add_tests_to_cycle_job_progress";
	public static final String URL_JOB_PROGRESS_ZFJC = "{SERVER}/public/rest/api/1.0/jobprogress/{jobProgressToken}";
    public static final String URL_UPDATE_EXECUTION = "{SERVER}/rest/zapi/latest/execution/{ID}/execute";
 
    public static PrintStream logger;
    
	public static Map<Long, Map<String, String>> getTestCaseDetails(ZephyrConfigModel zephyrData) {
        
		JSONObject bulkIssues = new JSONObject();
		JSONArray issueUpdates = new JSONArray();
		List<JSONArray> issueKeyJSONArrayList = new ArrayList<JSONArray>();

		Map<Long, Map<String, String>> testCaseResultMap = new HashMap<Long, Map<String, String>>();
		List<TestCaseResultModel> testCases = zephyrData.getTestcases();
		if (testCases == null || testCases.size() == 0) {
			return testCaseResultMap;
		}
		
			
		Map<String, Map<Long, String>> searchedTests = searchIssues(zephyrData);
		
		for (Iterator<TestCaseResultModel> iterator = testCases.iterator(); iterator.hasNext();) {
			TestCaseResultModel testCaseWithStatus = (TestCaseResultModel) iterator
					.next();
			
				if (searchedTests.containsKey(testCaseWithStatus.getTestCaseName())) {
					
					Map<Long, String> tempTestIdTestKeyMap = searchedTests.get(testCaseWithStatus.getTestCaseName());
					Set<Entry<Long, String>> entrySet = tempTestIdTestKeyMap.entrySet();
					Entry<Long, String> entry = entrySet.iterator().next();					
					
					Map<String, String> map = new HashMap<String, String>();
					map.put(entry.getValue(), testCaseWithStatus.getError());
					testCaseResultMap.put(entry.getKey(), map);
                    
                    String error = testCaseWithStatus.getError();
                    logger.printf("error for %s = %s...%n",testCaseWithStatus.getTestCaseName(),testCaseWithStatus.getError());
                    
				} else {
					String testCase = testCaseWithStatus.getTestCase();
					JSONObject issue = new JSONObject(testCase);
					issueUpdates.put(issue);
					
					if (issueUpdates.length() == MAX_BULK_ISSUE_CREATE_COUNT) {
						issueKeyJSONArrayList.add(issueUpdates);
						issueUpdates = new JSONArray();
					}

				}
	}
		
		if (issueUpdates.length() > 0) {
			issueKeyJSONArrayList.add(issueUpdates);
		}

		if (issueKeyJSONArrayList.size() == 0) {
			return testCaseResultMap;
		}
		for (int i = 0; i < issueKeyJSONArrayList.size(); i++) {
			bulkIssues.put("issueUpdates", issueKeyJSONArrayList.get(i));
			createTests(zephyrData, bulkIssues, testCases);
		}
		

		
		Map<String, Map<Long, String>> searchedTestsAfterCreation = searchIssues(zephyrData);
		
		for (Iterator<TestCaseResultModel> iterator = testCases.iterator(); iterator.hasNext();) {
			TestCaseResultModel testCaseWithStatus = (TestCaseResultModel) iterator.next();
			
			if (searchedTestsAfterCreation.containsKey(testCaseWithStatus.getTestCaseName())) {
				
				Map<Long, String> tempTestIdTestKeyMap = searchedTestsAfterCreation.get(testCaseWithStatus.getTestCaseName());
				Set<Entry<Long, String>> entrySet = tempTestIdTestKeyMap.entrySet();
				Entry<Long, String> entry = entrySet.iterator().next();				
				
				Map<String, String> map = new HashMap<String, String>();
				map.put(entry.getValue(), testCaseWithStatus.getError());
				testCaseResultMap.put(entry.getKey(), map);

			}
						
	}
        return testCaseResultMap;	
	}

	private static void createTests(ZephyrConfigModel zephyrData, JSONObject bulkIssues,
			List<TestCaseResultModel> testCases) {
		String createURL = URL_CREATE_TESTS.replace("{SERVER}", zephyrData.getRestClient().getUrl());
		HttpPost httpPost = new HttpPost(createURL);
		httpPost.addHeader("Content-Type", "application/json");
		
		CloseableHttpResponse issueCreateResponse = null;
		try {
			StringEntity se = new StringEntity(bulkIssues.toString());
			httpPost.setEntity(se);
			
			issueCreateResponse = zephyrData.getRestClient().getHttpclient().execute(httpPost, zephyrData.getRestClient().getContext());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

		int statusCode1 = issueCreateResponse.getStatusLine().getStatusCode();
		if (issueCreateResponse != null)
			try {
				issueCreateResponse.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public static Map<Long, String> getAllCyclesByVersionId(ZephyrConfigModel zephyrData) {


		Map<Long, String> cycles = new TreeMap<Long, String>();
		
		CloseableHttpResponse response = null;
		
		final String url = URL_GET_CYCLES.replace("{SERVER}", zephyrData.getRestClient().getUrl()) + "?projectId=" + zephyrData.getZephyrProjectId() + "&versionId=" + zephyrData.getVersionId();
		try {
			response = zephyrData.getRestClient().getHttpclient().execute(new HttpGet(url), zephyrData.getRestClient().getContext());
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
	
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return cycles;
	}

	public static void assignTests(ZephyrConfigModel zephyrData, JSONObject jsonObject) {
		
		if (zephyrData.isZfjClud()) {
			
			try {
				assignTestsZFJC(zephyrData, jsonObject);
			} catch (Exception e){
				e.printStackTrace();
			}
			return;
		}

		Long cycleId = 0L;

		CloseableHttpResponse response = null;
		//RestClient restClient = zephyrData.getRestClient();
		try {
			String assignTestsToCycleURL = URL_ASSIGN_TESTS.replace("{SERVER}", zephyrData.getRestClient().getUrl());
			
		
			StringEntity se = new StringEntity(jsonObject.toString());
			
			HttpPost createCycleRequest = new HttpPost(assignTestsToCycleURL);
			
			createCycleRequest.setHeader("Content-Type", "application/json");
			createCycleRequest.setEntity(se);
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//int statusCode = response.getStatusLine().getStatusCode();
		//HttpEntity entity1 = response.getEntity();
		
		//String token = null;
		//try{
			//String tokenObject = EntityUtils.toString(entity1);
			//token = new JSONObject(tokenObject).getString("jobProgressToken");

		//}catch (ParseException e){
			//e.printStackTrace();
		//}catch(IOException e){
		//	e.printStackTrace();
		//}
		
		//checkJobProgress(zephyrData,token);

		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity1 = response.getEntity();
		String token = null;
		
				try{
					String tokenObject = EntityUtils.toString(entity1);
					token = new JSONObject(tokenObject).getString("jobProgressToken");

				}catch (ParseException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}
				
				//will check if the job is completed for a max 10 times and then we will try to continue even if it does not return as complted.
				int maxTryCount = 0;
				boolean checkJobProgress = true;
				
				while (checkJobProgress) {
					maxTryCount++;
					try {
						checkJobProgress = checkJobProgress(zephyrData,token);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(checkJobProgress) checkJobProgress = false;
					if(maxTryCount == 10) checkJobProgress = false;
				}
				
		
		if (statusCode >= 200 && statusCode < 300) { } else {
			try {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static boolean checkJobProgress(ZephyrConfigModel zephyrData, String token) {
		
		//Just to ensure the job is completed
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		boolean jobCompleted = false;
		CloseableHttpResponse response = null;
		
		String url = null;

		if (zephyrData.isZfjClud()){
			RestClient restClient = zephyrData.getRestClient();
			try {
				
				url = URL_JOB_PROGRESS_ZFJC.replace("{SERVER}", zephyrData.getRestClient().getZephyrCloudURL()).replace("{jobProgressToken}", token);
				String jwtHeaderValue = ServerInfo.generateJWT(restClient, url, HTTP_REQUEST_METHOD_GET);

				HttpGet jobProgressRequest = new HttpGet(url);

				jobProgressRequest.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
				jobProgressRequest.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
				jobProgressRequest.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());

				response = zephyrData.getRestClient().getHttpclient().execute(jobProgressRequest, zephyrData.getRestClient().getContext());
				String result = EntityUtils.toString(response.getEntity());
					String progress = new JSONObject(result).get("progress").toString();
				if (progress != null && progress.equals("1.0")) {
					jobCompleted = true;
				};

			
            }catch (Exception e) {
                e.printStackTrace();
                // log.debug("error during add tests to cycle job progress");
            }

		}
else {
			url = URL_JOB_PROGRESS
					.replace("{SERVER}", zephyrData.getRestClient().getUrl())
					.replace("{jobProgressToken}", token);
			RestClient restClient = zephyrData.getRestClient();
			try {
				//StringEntity se = new StringEntity(jsonObject.toString());
                HttpGet jobProgressRequest = new HttpGet(url);
				
				jobProgressRequest.setHeader("Content-Type", "application/json");
                //jobProgressRequest.setEntity(se);
				response = zephyrData.getRestClient().getHttpclient().execute(jobProgressRequest, zephyrData.getRestClient().getContext());
				String result = EntityUtils.toString(response.getEntity());
				
				String progress = new JSONObject(result).get("progress").toString();

				if (progress != null && progress.equals("1.0")) {
					jobCompleted = true;
				};

			} catch (Exception e) {
				e.printStackTrace();
				//log.debug("error during add tests to cycle job progress");
			}
		}

		return jobCompleted;
}

	

	
	public static Map<String, Long> fetchExecutionIds(ZephyrConfigModel zephyrData, JSONObject jsonObject) {

		Map<String, Long> issueKeyExecutionIdMap = new HashMap<String, Long>();

		CloseableHttpResponse response = null;
		try {
			
			String executionsURL = URL_CREATE_EXECUTIONS_URL.replace("{SERVER}", zephyrData.getRestClient().getUrl()).replace("{projectId}", zephyrData.getZephyrProjectId()+"").replace("{versionId}", zephyrData.getVersionId()+"").replace("{cycleId}", zephyrData.getCycleId()+"");
		
			HttpGet executionsURLRequest = new HttpGet(executionsURL);
			response = zephyrData.getRestClient().getHttpclient().execute(executionsURLRequest, zephyrData.getRestClient().getContext());
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
				JSONObject executionObject = new JSONObject(string);
				JSONArray executions = executionObject.getJSONArray("executions");
				
				for (int i = 0; i < executions.length(); i++) {
					JSONObject execution = executions.getJSONObject(i);
					String issueKey = execution.getString("issueKey").trim();
					long executionId = execution.getLong("id");
					
					issueKeyExecutionIdMap.put(issueKey, executionId);
				}
				
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
		
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	
		return issueKeyExecutionIdMap;
	}
    
    private static void addExecutionComment(String executionId,String comment,ZephyrConfigModel zephyrData,String logFilePath){
        CloseableHttpResponse response = null;
        JSONObject commentObj = new JSONObject();
        if (logFilePath != null){
            comment = comment + "log: "+logFilePath;
        }
        commentObj.put("comment",comment);
        
        String updateExecutionURL = URL_UPDATE_EXECUTION.replace("{SERVER}", zephyrData.getRestClient().getUrl());
        updateExecutionURL = updateExecutionURL.replace("{ID}", String.valueOf(executionId));
        logger.printf("updateExecutionURL=%s%n",updateExecutionURL);
        try {
            StringEntity commentEntity = new StringEntity(commentObj.toString());
            HttpPut updateExecution = new HttpPut(updateExecutionURL);
            updateExecution.setHeader("Content-Type", "application/json");
            updateExecution.setEntity(commentEntity);
            response = zephyrData.getRestClient().getHttpclient().execute(updateExecution, zephyrData.getRestClient().getContext());
            logger.printf("single update response header = %s%n",String.valueOf(response));
            String entity = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
        } catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public static void executeTests(ZephyrConfigModel zephyrData, List<Long> passList, Map<Long,String> failMap, String logFilePath) {
        CloseableHttpResponse response = null;
		try {
			String bulkExecuteTestsURL = URL_EXECUTE_TEST.replace("{SERVER}", zephyrData.getRestClient().getUrl());
						
			if (failMap.size() > 0) {
				JSONArray failedTests = new JSONArray();
				JSONObject failObj = new JSONObject();
				
                Iterator it = failMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    failedTests.put(pair.getKey());
                }
                logger.printf("failedTests = %s%n",failedTests.toString());
				failObj.put("executions", failedTests);
				failObj.put("status", 2);
				StringEntity failEntity = new StringEntity(failObj.toString());
				HttpPut bulkUpdateFailedTests = new HttpPut(bulkExecuteTestsURL);
                bulkUpdateFailedTests.setHeader("Content-Type", "application/json");
				bulkUpdateFailedTests.setEntity(failEntity);
				response = zephyrData.getRestClient().getHttpclient().execute(bulkUpdateFailedTests, zephyrData.getRestClient().getContext());
 				logger.printf("bulk update response = %s%n",String.valueOf(response.getEntity()));
                
                it = failMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    //logger.printf("id/error pair = %d,%s%n",pair.getKey(),pair.getValue());
                    String comment = pair.getValue().toString()+"\n";
                    addExecutionComment(pair.getKey().toString(),comment,zephyrData,logFilePath);
                }
			}
		
			if (passList.size() > 0) {
				if(response != null) {
					response.close();
				}
			
                JSONArray passedTests = new JSONArray();
                JSONObject passObj = new JSONObject();
                for (long passedTest: passList) {
                    passedTests.put(passedTest);
                    addExecutionComment(new Long(passedTest).toString(),"",zephyrData,logFilePath);
                }
                passObj.put("executions", passedTests);
                passObj.put("status", 1);
                StringEntity passEntity = new StringEntity(passObj.toString());
                HttpPut bulkUpdatePassedTests = new HttpPut(bulkExecuteTestsURL);
                bulkUpdatePassedTests.setHeader("Content-Type", "application/json");
                bulkUpdatePassedTests.setEntity(passEntity);
                response = zephyrData.getRestClient().getHttpclient().execute(bulkUpdatePassedTests, zephyrData.getRestClient().getContext());
			}


		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();
		//HttpEntity entity1 = response.getEntity();
		//String token = null;
		
				//try{
				//	String tokenObject = EntityUtils.toString(entity1);
					//token = new JSONObject(tokenObject).getString("jobProgressToken");

		//		}catch (ParseException e){
			//		e.printStackTrace();
				//}catch(IOException e){
					//e.printStackTrace();
				//}
		//checkJobProgressexecute(zephyrData,token);

		if (statusCode >= 200 && statusCode < 300) { } else {
			try {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void processTestCaseDetails(ZephyrConfigModel zephyrData, BuildListener listener, String logFilePath) {
                
        logger = listener.getLogger();
        logger.printf("%nprocessing test case details...%n");
		Map<Long, Map<String, String>> testCaseDetails  = getTestCaseDetails(zephyrData);
	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("versionId", zephyrData.getVersionId());
		jsonObject.put("projectId", zephyrData.getZephyrProjectId());
		jsonObject.put("method", "1");

		long cycleId = 0;
		
		if (zephyrData.isZfjClud()) {
			if(zephyrData.getCycleIdZfjCloud() == null || zephyrData.getCycleIdZfjCloud() == NEW_CYCLE_KEY_IDENTIFIER +"") {
				String cycleIdZFJC = Cycle.createCycleZFJC(zephyrData);
				zephyrData.setCycleIdZfjCloud(cycleIdZFJC);
				jsonObject.put("cycleId", cycleIdZFJC);
			} else {
				jsonObject.put("cycleId", zephyrData.getCycleIdZfjCloud());
			}
			
		} else {
			if(zephyrData.getCycleId() == null || zephyrData.getCycleId() == NEW_CYCLE_KEY_IDENTIFIER) {
				cycleId = Cycle.createCycle(zephyrData);
				zephyrData.setCycleId(cycleId);
				jsonObject.put("cycleId", cycleId);
			}
			jsonObject.put("cycleId", zephyrData.getCycleId());
		}
//		if((zephyrData.getCycleId() == null || zephyrData.getCycleId() == NEW_CYCLE_KEY_IDENTIFIER) && !zephyrData.isZfjClud()) {
//			cycleId = Cycle.createCycle(zephyrData);
//			zephyrData.setCycleId(cycleId);
//		} else if ((zephyrData.getCycleId() == null || zephyrData.getCycleId() == NEW_CYCLE_KEY_IDENTIFIER) && zephyrData.isZfjClud()) {
//			String cycleIdZFJC = Cycle.createCycleZFJC(zephyrData);
//			zephyrData.setCycleIdZfjCloud(cycleIdZFJC);
//		}
		
		
		List<JSONArray> issueKeyJSONArrayList = new ArrayList<JSONArray>();
		JSONArray jArr = new JSONArray();
		
		JSONObject createExecutionsJObj = new JSONObject();
		Set<Entry<Long, Map<String, String>>> entrySet = testCaseDetails.entrySet();
		for (Iterator<Entry<Long, Map<String, String>>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Long, Map<String, String>> entry = iterator
					.next();
			Map<String, String> value = entry.getValue();
			Set<String> keySet = value.keySet();
			for (Iterator<String> iterator2 = keySet.iterator(); iterator2.hasNext();) {
				String issueKey = iterator2.next();
				
				jArr.put(issueKey);
				
				if (jArr.length() == MAX_BULK_OPERATION_COUNT) {
					issueKeyJSONArrayList.add(jArr);
					jArr = new JSONArray();
				}
			}
		}
		
		issueKeyJSONArrayList.add(jArr);
		
		for (int i = 0; i < issueKeyJSONArrayList.size(); i++) {
			jsonObject.put("issues", issueKeyJSONArrayList.get(i));
			assignTests(zephyrData, jsonObject);
		}
		
		if (!zephyrData.isZfjClud()) {
			
			Map<String, Long> issueKeyExecutionIdMap = fetchExecutionIds(zephyrData, createExecutionsJObj);
			
			List<Long> passList = new ArrayList<Long>();
			Map<Long,String> failMap = new HashMap<Long,String>();
			for (Iterator<Entry<Long, Map<String, String>>> iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry<Long, Map<String, String>> entry = iterator
						.next();
				Map<String, String> value = entry.getValue();
				Set<String> keySet = value.keySet();
				for (Iterator<String> iterator2 = keySet.iterator(); iterator2.hasNext();) {
					String issueKey = iterator2.next();
					
					createExecutionsJObj.put("issueId", entry.getKey());
					createExecutionsJObj.put("versionId", zephyrData.getVersionId());
					createExecutionsJObj.put("cycleId", zephyrData.getCycleId());
					createExecutionsJObj.put("projectId", zephyrData.getZephyrProjectId());
					
					
//				executeTests(zephyrData, executionId, value.get(issueKey));
					
					String error = value.get(issueKey);
                    logger.printf("error = %s%n", error);
					Long executionId = issueKeyExecutionIdMap.get(issueKey);
                    
					if (error.isEmpty()) {
						passList.add(executionId );
					} else {
						failMap.put(executionId,error);
					}
				}
			}
            logger.printf("failMap = %s%n", failMap.toString());

			executeTests(zephyrData, passList, failMap, logFilePath);
		} else {
			
			int totalExecutionsCount = 0;
			Map<String, String> issueKeyExecutionIdMap = new HashMap<String, String>();
			
			
			totalExecutionsCount = fetchExecutionIdsZFJC(zephyrData, createExecutionsJObj, issueKeyExecutionIdMap, 0);
			
			while (issueKeyExecutionIdMap.size() < totalExecutionsCount) {
				fetchExecutionIdsZFJC(zephyrData, createExecutionsJObj, issueKeyExecutionIdMap, issueKeyExecutionIdMap.size());
			}
			
			List<String> passList = new ArrayList<String>();
			Map<String,String> failMap = new HashMap<String,String>();
			for (Iterator<Entry<Long, Map<String, String>>> iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry<Long, Map<String, String>> entry = iterator
						.next();
				Map<String, String> value = entry.getValue();
				Set<String> keySet = value.keySet();
				for (Iterator<String> iterator2 = keySet.iterator(); iterator2.hasNext();) {
					String issueKey = iterator2.next();
					
					createExecutionsJObj.put("issueId", entry.getKey());
					createExecutionsJObj.put("versionId", zephyrData.getVersionId());
					createExecutionsJObj.put("cycleId", zephyrData.getCycleIdZfjCloud());
					createExecutionsJObj.put("projectId", zephyrData.getZephyrProjectId());
					
					
//				executeTests(zephyrData, executionId, value.get(issueKey));
					
					String error = value.get(issueKey);
					String executionId = issueKeyExecutionIdMap.get(issueKey);
                    
					if(error.isEmpty()) {
						passList.add(executionId );
					} else {
						failMap.put(executionId,error);
					}
				}
			}
            
			//executeTestsZFJC(zephyrData, passList, failMap);
		}
	}
	
	

	private static Map<String, Map<Long, String>> searchIssues(ZephyrConfigModel zephyrData) {
		long searchIssueStartcount = -1L;
		long totalIssueCount = 0L;
		long searchedIsssuesCount = 0L;


		Map<String, Map<Long, String>> searchedTests = new HashMap<String, Map<Long, String>>();
		
		CustomReturnTypeBean returnValue = searchTests(zephyrData, searchIssueStartcount,
				searchedTests);

		totalIssueCount = returnValue.getTotalIssuesCount();
		searchedIsssuesCount = returnValue.getSearchedIssuesCount();
		while (searchedIsssuesCount < totalIssueCount) {
//			log.info("Total Issues : " + totalIssueCount);
//			log.info("Total issues fetched so far : " + searchedTests.size());
			CustomReturnTypeBean returnValue1 = searchTests(zephyrData, searchedIsssuesCount, searchedTests);
			searchedIsssuesCount = searchedIsssuesCount + returnValue1.getSearchedIssuesCount();
		}
		return searchedTests;
	}

	private static CustomReturnTypeBean searchTests(ZephyrConfigModel zephyrData,
			long searchIssueStartcount,
			Map<String, Map<Long, String>> searchedTests) {
		
		CustomReturnTypeBean customReturnTypeBean = new CustomReturnTypeBean();
		long totalIssueCount = 0L;
		CloseableHttpResponse response = null;
		String isssueSearchURL = null;

		String searchJQL = JQL_SEARCH_TESTS;
		searchJQL = searchJQL.replace("{pId}", zephyrData.getZephyrProjectId()+"");
		searchJQL = searchJQL.replace("{issueTypeId}", zephyrData.getTestIssueTypeId()+"");
		searchJQL = searchJQL.replace("{searchIssueStartcount}", searchIssueStartcount+"");
		
		isssueSearchURL = URL_GET_ALL_TESTS.replace("{SERVER}", zephyrData.getRestClient().getUrl()) + "?" + searchJQL;
		JSONArray searchedIssues = null;
		try {
			response = zephyrData.getRestClient().getHttpclient().execute(new HttpGet(isssueSearchURL), zephyrData.getRestClient().getContext());
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
				totalIssueCount = testCaseIssues.getLong("total");
//				log.info("Total Issues count : " + totalIssueCount);
				searchedIssues = testCaseIssues.getJSONArray("issues");
				
				customReturnTypeBean.setSearchedIssuesCount(searchedIssues.length());
				
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
		
		try {
			
			if(searchedIssues != null && searchedIssues.length() > 0 ) {
				
				for (int i = 0; i < searchedIssues.length(); i++) {
					JSONObject jsonObject = searchedIssues.getJSONObject(i);
					String testName = jsonObject.getJSONObject("fields").getString("summary").trim();
					long testId = jsonObject.getLong("id");
					String testKey = jsonObject.getString("key").trim();
					
					Map<Long,String> tempTestIdTestKeyMap = new HashMap<Long, String>();
					tempTestIdTestKeyMap.put(testId, testKey);
					searchedTests.put(testName, tempTestIdTestKeyMap);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		customReturnTypeBean.setTotalIssuesCount(totalIssueCount);
		
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return customReturnTypeBean;
	}

	
	static class CustomReturnTypeBean {
		
		private long totalIssuesCount;
		private long searchedIssuesCount;

		public long getTotalIssuesCount() {
			return totalIssuesCount;
		}

		public void setTotalIssuesCount(long totalIssuesCount) {
			this.totalIssuesCount = totalIssuesCount;
		}

		public long getSearchedIssuesCount() {
			return searchedIssuesCount;
		}

		public void setSearchedIssuesCount(long searchedIssuesCount) {
			this.searchedIssuesCount = searchedIssuesCount;
		}
	}
	
	
	
	
	
	
	
	
	public static void assignTestsZFJC(ZephyrConfigModel zephyrData, JSONObject jsonObject) {

		CloseableHttpResponse response = null;
		try {
			RestClient restClient = zephyrData.getRestClient();
			String assignTestsToCycleURL = URL_ZFJC_ASSIGN_TESTS.replace(ZFJC_SERVER, restClient.getZephyrCloudURL()).replace(CYCLEID, zephyrData.getCycleIdZfjCloud());
			String jwtHeaderValue = ServerInfo.generateJWT(restClient, assignTestsToCycleURL, HTTP_REQUEST_METHOD_POST);

		
			StringEntity se = new StringEntity(jsonObject.toString());
			
			HttpPost createCycleRequest = new HttpPost(assignTestsToCycleURL);
			
			createCycleRequest.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
			createCycleRequest.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
			createCycleRequest.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());
			
			createCycleRequest.setEntity(se);
			response = zephyrData.getRestClient().getHttpclient().execute(createCycleRequest, zephyrData.getRestClient().getContext());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int statusCode = response.getStatusLine().getStatusCode();
		HttpEntity entity1 = response.getEntity();
		String token = null;
		try{
			token = EntityUtils.toString(entity1);

		}catch (ParseException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//will check if the job is completed for a max 10 times and then we will try to continue even if it does not return as complted.
		int maxTryCount = 0;
		boolean checkJobProgress = true;
		
		while (checkJobProgress) {
			maxTryCount++;
			try {
				checkJobProgress = checkJobProgress(zephyrData,token);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(checkJobProgress) checkJobProgress = false;
			if(maxTryCount == 10) checkJobProgress = false;
		}
;

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
		
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int fetchExecutionIdsZFJC(ZephyrConfigModel zephyrData, JSONObject jsonObject, Map<String, String> issueKeyExecutionIdMap, int offset) {

		int totalCount = 0;
		Map<String, String> map = new HashMap<String, String>();

		CloseableHttpResponse response = null;
		try {
			
			RestClient restClient = zephyrData.getRestClient();
			String executionsURL = URL_ZFJC_CREATE_EXECUTIONS_URL.replace(ZFJC_SERVER, restClient.getZephyrCloudURL()).replace(CYCLEID, zephyrData.getCycleIdZfjCloud()).replace(PROJECTID, zephyrData.getZephyrProjectId()+"").replace(VERSIONID, zephyrData.getVersionId()+"").replace(PAGINATION_OFFSET, offset+"");
			String jwtHeaderValue = ServerInfo.generateJWT(restClient, executionsURL, HTTP_REQUEST_METHOD_GET);

			HttpGet executionsURLRequest = new HttpGet(executionsURL);

			executionsURLRequest.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
			executionsURLRequest.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
			executionsURLRequest.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());

		
			response = zephyrData.getRestClient().getHttpclient().execute(executionsURLRequest, zephyrData.getRestClient().getContext());
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
				JSONObject executionObject = new JSONObject(string);
				JSONArray executions = executionObject.getJSONArray("searchObjectList");
				
				totalCount = executionObject.getInt("totalCount");
				
				for (int i = 0; i < executions.length(); i++) {
					JSONObject execution = executions.getJSONObject(i);
					String issueKey = execution.getString("issueKey").trim();
					String executionId = execution.getJSONObject("execution").getString("id");
					
					map.put(issueKey, executionId);
				}
				
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
	
		if(response != null) {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		issueKeyExecutionIdMap.putAll(map);
		return totalCount;
	}

























	//private static void executeTestsZFJC(ZephyrConfigModel zephyrData, List<String> passList, Map<Long,String> failMap) {
	//
	//	CloseableHttpResponse response = null;
	//	try {
	//
	//		RestClient restClient = zephyrData.getRestClient();
	//		String bulkExecuteTestsURL = URL_ZFJC_EXECUTE_TEST.replace(ZFJC_SERVER, restClient.getZephyrCloudURL());
	//		String jwtHeaderValue = ServerInfo.generateJWT(restClient, bulkExecuteTestsURL, HTTP_REQUEST_METHOD_POST);
	//
	//
	//		
	//		int failListSize = failList.size();
	//		if (failListSize > 0) {
	//			
	//			int bulkOperationSetCount = failListSize / MAX_BULK_OPERATION_COUNT;
	//			bulkOperationSetCount += (failListSize % MAX_BULK_OPERATION_COUNT) > 0 ? 1 : 0;
	//			
	//			for (int i = 0; i < bulkOperationSetCount; i++) {
	//				
	//				List<String> tempFailList = failList.subList((i*MAX_BULK_OPERATION_COUNT), ((bulkOperationSetCount - i) > 1) ? ((i*MAX_BULK_OPERATION_COUNT) + MAX_BULK_OPERATION_COUNT) : failList.size());
	//				JSONArray failedTests = new JSONArray();
	//				JSONObject failObj = new JSONObject();
	//				
	//				for (String failedTest: tempFailList) {
	//					failedTests.put(failedTest);
	//				}
	//				failObj.put("executions", failedTests);
	//				failObj.put("status", 2);
	//				failObj.put("stepStatus", -1);
	//				failObj.put("testStepStatusChangeFlag", true);
	//				failObj.put("clearDefectMappingFlag", false);
	//				StringEntity failEntity = new StringEntity(failObj.toString());
	//				
	//				HttpPost bulkUpdateFailedTests = new HttpPost(bulkExecuteTestsURL);
	//				bulkUpdateFailedTests.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
	//				bulkUpdateFailedTests.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
	//				bulkUpdateFailedTests.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());
	//				bulkUpdateFailedTests.setEntity(failEntity);
	//				
	//				if(response != null) {
	//					response.close();
	//				}
	//				response = restClient.getHttpclient().execute(bulkUpdateFailedTests);
	//			}
	//		}
	//		
	//	
	//		int passListSize = passList.size();
	//		if (passListSize > 0) {
	//			
	//			int bulkOperationSetCount = passListSize / MAX_BULK_OPERATION_COUNT;
	//			bulkOperationSetCount += (passListSize % MAX_BULK_OPERATION_COUNT) > 0 ? 1 : 0;
	//			
	//			for (int i = 0; i < bulkOperationSetCount; i++) {
	//				
	//				List<String> tempPassList = passList.subList((i*MAX_BULK_OPERATION_COUNT), ((bulkOperationSetCount - i) > 1) ? ((i*MAX_BULK_OPERATION_COUNT) + MAX_BULK_OPERATION_COUNT) : passList.size());
	//
	//				JSONArray passedTests = new JSONArray();
	//				JSONObject passObj = new JSONObject();
	//				for (String passedTest: tempPassList) {
	//					passedTests.put(passedTest);
	//				}
	//				passObj.put("executions", passedTests);
	//				passObj.put("status", 1);
	//				passObj.put("stepStatus", -1);
	//				passObj.put("testStepStatusChangeFlag", true);
	//				passObj.put("clearDefectMappingFlag", false);
	//				StringEntity passEntity = new StringEntity(passObj.toString());
	//				
	//				HttpPost bulkUpdatePassedTests = new HttpPost(bulkExecuteTestsURL);
	//				bulkUpdatePassedTests.addHeader(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON);
	//				bulkUpdatePassedTests.addHeader(HEADER_AUTHORIZATION, jwtHeaderValue);
	//				bulkUpdatePassedTests.addHeader(HEADER_ZFJC_ACCESS_KEY, restClient.getAccessKey());
	//				bulkUpdatePassedTests.setEntity(passEntity);
	//				
	//				if(response != null) {
	//					response.close();
	//				}
	//				response = restClient.getHttpclient().execute(bulkUpdatePassedTests);
	//			}
	//		}
	//	} catch (ClientProtocolException e) {
	//		e.printStackTrace();
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	//
	//	int statusCode = response.getStatusLine().getStatusCode();
	//
	//	if (statusCode >= 200 && statusCode < 300) {
	//		HttpEntity entity = response.getEntity();
	//		String string = null;
	//		try {
	//			string = EntityUtils.toString(entity);
	//		} catch (ParseException e) {
	//			e.printStackTrace();
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		}
	//
	//		
	//	} else {
	//		try {
	//			throw new ClientProtocolException("Unexpected response status: "
	//					+ statusCode);
	//		} catch (ClientProtocolException e) {
	//			e.printStackTrace();
	//		}
	//	}
	//
	//
	//}

}