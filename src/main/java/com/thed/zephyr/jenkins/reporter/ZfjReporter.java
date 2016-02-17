package com.thed.zephyr.jenkins.reporter;

import hudson.FilePath;

/**
 * @author mohan
 */

import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.AbstractBuild;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.junit.SuiteResult;
import hudson.tasks.junit.TestResultAction;
import jenkins.tasks.SimpleBuildStep;
import hudson.tasks.junit.CaseResult;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import com.thed.zephyr.jenkins.model.TestCaseResultModel;
import com.thed.zephyr.jenkins.model.ZephyrConfigModel;
import com.thed.zephyr.jenkins.model.ZephyrInstance;
import com.thed.zephyr.jenkins.utils.rest.Project;
import com.thed.zephyr.jenkins.utils.rest.RestClient;
import com.thed.zephyr.jenkins.utils.rest.ServerInfo;
import com.thed.zephyr.jenkins.utils.rest.TestCaseUtil;

import static com.thed.zephyr.jenkins.reporter.ZfjConstants.*;

public class ZfjReporter extends Notifier implements SimpleBuildStep{

	public static PrintStream logger;

	private String serverAddress;
	private String projectKey;
	private String versionKey;
	private String cycleKey;;
	private String cyclePrefix;
	private String cycleDuration;

	
    private static final String PluginName = new String("[ZapiTestResultReporter]");
    private final String pInfo = String.format("%s [INFO]", PluginName);

    @DataBoundConstructor
	public ZfjReporter(String serverAddress, String projectKey,
			String versionKey, String cycleKey, String cycleDuration,
			String cyclePrefix) {


    	this.serverAddress = serverAddress;
        this.projectKey = projectKey;
        this.versionKey = versionKey;
        this.cycleKey = cycleKey;
        this.cyclePrefix = cyclePrefix;
        this.cycleDuration = cycleDuration;
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public boolean perform(final AbstractBuild build,
                           final Launcher launcher,
                           final BuildListener listener) {
        logger = listener.getLogger();
        logger.printf("%s Examining test results...%n", pInfo);
        logger.printf(String.format("Build result is %s%n", build.getResult().toString()));
		return perform(build);
    }

	private boolean prepareZephyrTests(final Run build,
			ZephyrConfigModel zephyrConfig) {
		
		boolean status = true;
		Map<String, Boolean> zephyrTestCaseMap = new HashMap<String, Boolean>();
		
		TestResultAction testResultAction = build.getAction(TestResultAction.class);
		Collection<SuiteResult> suites = null;
		
		try {
			suites = testResultAction.getResult().getSuites();
		} catch (Exception e) {
			logger.println(e.getMessage());
		}
		
		if (suites == null || suites.size() == 0) {
			logger.println("Problem parsing JUnit test Results.");
			return false;
		}
		
		
		
for (Iterator<SuiteResult> iterator = suites.iterator(); iterator.hasNext();) {
		SuiteResult suiteResult = iterator.next();
		List<CaseResult> cases = suiteResult.getCases();
		for (CaseResult caseResult : cases) {
			boolean isPassed = caseResult.isPassed();
			String name = caseResult.getFullName();
			if (!zephyrTestCaseMap.containsKey(name)) {
				zephyrTestCaseMap.put(name, isPassed);
			}
		}
}
		
		logger.print("Total Test Cases : " + zephyrTestCaseMap.size());
		List<TestCaseResultModel> testcases = new ArrayList<TestCaseResultModel>();

		
		Set<String> keySet = zephyrTestCaseMap.keySet();
		
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String testCaseName = iterator.next();
			Boolean isPassed = zephyrTestCaseMap.get(testCaseName);
			
			
			JSONObject isssueType = new JSONObject();
			isssueType.put("id", zephyrConfig.getTestIssueTypeId()+"");
			
			JSONObject project = new JSONObject();
			project.put("id", zephyrConfig.getZephyrProjectId());

			JSONObject fields = new JSONObject();
			fields.put("project", project);
			fields.put("summary", testCaseName);
			fields.put("description", "Creating the Test via Jenkins");
			fields.put("issuetype", isssueType);
			
			JSONObject issue = new JSONObject();
			issue.put("fields", fields);
			
			TestCaseResultModel caseWithStatus = new TestCaseResultModel();
			caseWithStatus.setPassed(isPassed);
			caseWithStatus.setTestCase(issue.toString());
			caseWithStatus.setTestCaseName(testCaseName);
			testcases.add(caseWithStatus);
		}
		
		zephyrConfig.setTestcases(testcases);
		
		return status;
	}

	private boolean validateBuildConfig() {
		boolean valid = true;
		if (StringUtils.isBlank(serverAddress)
				|| StringUtils.isBlank(projectKey)
				|| StringUtils.isBlank(versionKey)
				|| StringUtils.isBlank(cycleKey)
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(serverAddress.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(projectKey.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(versionKey.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(cycleKey.trim()))	{

			logger.println("Cannot Proceed");
			valid = false;
		}
		return valid;
	}

	private void determineTestIssueTypeId(ZephyrConfigModel zephyrConfig) {
		long testIssueTypeId = ServerInfo.findTestIssueTypeId(zephyrConfig.getRestClient());
		zephyrConfig.setTestIssueTypeId(testIssueTypeId);
	}

	private void determineCyclePrefix(ZephyrConfigModel zephyrConfig) {
		if (StringUtils.isNotBlank(cyclePrefix)) {
			zephyrConfig.setCyclePrefix(cyclePrefix+ "_");
		} else {
			zephyrConfig.setCyclePrefix("Automation_");
		}
	}

	private ZephyrConfigModel initializeZephyrData() {
		ZephyrConfigModel zephyrConfig = new ZephyrConfigModel();
		
		String hostName = StringUtils.removeEnd(serverAddress, "/");
		fetchCredentials(zephyrConfig, hostName);
	
		zephyrConfig.setCycleDuration(cycleDuration);
		determineProjectID(zephyrConfig);
		determineVersionID(zephyrConfig);
		determineCycleID(zephyrConfig);
		determineCyclePrefix(zephyrConfig);
        determineTestIssueTypeId(zephyrConfig);

		
		return zephyrConfig;
	}

	private void fetchCredentials(ZephyrConfigModel zephyrConfig, String url) {
		List<ZephyrInstance> jiraServers = getDescriptor().getJiraInstances();

		for (ZephyrInstance jiraServer : jiraServers) {
			if (StringUtils.isNotBlank(jiraServer.getServerAddress()) && jiraServer.getServerAddress().trim().equals(serverAddress)) {
				String userName = jiraServer.getUsername();
				String password = jiraServer.getPassword();
				
				RestClient restClient = new RestClient(url, userName, password);
				zephyrConfig.setRestClient(restClient);
				
				break;
			}
		}
	}

	private void determineCycleID(ZephyrConfigModel zephyrConfig) {
		if (cycleKey.equalsIgnoreCase(NEW_CYCLE_KEY)) {
			zephyrConfig.setCycleId(NEW_CYCLE_KEY_IDENTIFIER);
			return;
		}
		long cycleId = 0;
		try {
			cycleId = Long.parseLong(cycleKey);
		} catch (NumberFormatException e1) {
			logger.println("Cycle Key appears to be the name of the cycle");
			e1.printStackTrace();
		}
		zephyrConfig.setCycleName(cycleKey);	
		zephyrConfig.setCycleId(cycleId);
	}

	private void determineVersionID(ZephyrConfigModel zephyrData) {

		long versionId = 0;
		try {
			versionId = Long.parseLong(versionKey);
		} catch (NumberFormatException e1) {
			logger.println("Version Key appears to be Name of the Version");
			e1.printStackTrace();
		}
		zephyrData.setVersionId(versionId);
	}

	private void determineProjectID(ZephyrConfigModel zephyrData) {
		long projectId = 0;
		try {
			projectId = Long.parseLong(projectKey);
		} catch (NumberFormatException e1) {
			logger.println("Project Key appears to be Name of the project");
			try {
				Long projectIdByName = Project.getProjectIdByName(projectKey, zephyrData.getRestClient());
				projectId = projectIdByName;
			} catch (Exception e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		
		zephyrData.setZephyrProjectId(projectId);
	}



    @Override
    public ZfjDescriptor getDescriptor() {
        return (ZfjDescriptor) super.getDescriptor();
    }

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getVersionKey() {
		return versionKey;
	}

	public void setVersionKey(String versionKey) {
		this.versionKey = versionKey;
	}

	public String getCycleKey() {
		return cycleKey;
	}

	public void setCycleKey(String cycleKey) {
		this.cycleKey = cycleKey;
	}

	public String getCyclePrefix() {
		return cyclePrefix;
	}

	public void setCyclePrefix(String cyclePrefix) {
		this.cyclePrefix = cyclePrefix;
	}

	public String getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(String cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        logger = listener.getLogger();
        logger.printf("%s Examining test results...%n", pInfo);
        logger.printf(String.format("Build result is %s%n", run.getResult().toString()));
        perform(run);
    }

    private boolean perform(final Run build) {
        if (!validateBuildConfig()) {
            logger.println("Cannot Proceed. Please verify the job configuration");
            return false;
        }

        ZephyrConfigModel zephyrConfig = initializeZephyrData();

        boolean prepareZephyrTests = prepareZephyrTests(build, zephyrConfig);

        if (!prepareZephyrTests) {
            logger.println("Error parsing surefire reports.");
            logger.println("Please ensure \"Publish JUnit test result report is added\" as a post build action");
            return false;
        }

        TestCaseUtil.processTestCaseDetails(zephyrConfig);

        zephyrConfig.getRestClient().destroy();
        logger.printf("%s Done.%n", pInfo);
        return true;
    }
}

