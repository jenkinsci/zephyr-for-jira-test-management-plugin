package com.thed.zephyr.jenkins.reporter;

/**
 * @author mohan
 */

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import hudson.tasks.junit.SuiteResult;
import hudson.tasks.junit.TestResultAction;
import hudson.tasks.junit.CaseResult;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.thed.zephyr.jenkins.model.TestCaseResultModel;
import com.thed.zephyr.jenkins.model.ZephyrConfigModel;
import com.thed.zephyr.jenkins.model.ZephyrInstance;
import com.thed.zephyr.jenkins.utils.URLValidator;
import com.thed.zephyr.jenkins.utils.rest.Cycle;
import com.thed.zephyr.jenkins.utils.rest.Project;
import com.thed.zephyr.jenkins.utils.rest.Release;
import com.thed.zephyr.jenkins.utils.rest.ServerInfo;
import com.thed.zephyr.jenkins.utils.rest.TestCaseUtil;

public class ZfjReporter extends Notifier {
	/********************
	 * Constants
	 ********************/
	public static final long NEW_CYCLE_KEY_IDENTIFIER = 1000000000L;
	public static PrintStream logger;

	/********************
	 * public variables
	 ********************/
	/*  */
	public String projectKey;
	/*  */
	public String releaseKey;
	/*  */
	public String cycleKey;;
	/*  */
	public String cyclePrefix;
	/*  */
	public String serverAddress;
	/*  */
	public String username;
	/*  */
	public String password;
	/*  */
	public String cycleDuration;
	/*  */
	public boolean debugFlag;
	/*  */
	public boolean verboseDebugFlag;
	/*  */
	public boolean createPackage;

	/********************
	 * private variables
	 ********************/
	/*  */
	private Long projectId;
	/*  */
	private Long releaseId;
	/*  */
	private Long cycleId;
	/*  */
    private ZephyrConfigModel zephyrData;
	/*  */
    private FilePath workspace;
	/*  */
    private static final int JIRA_SUCCESS_CODE = 201;
	/*  */
    private static final String PluginName = new String("[ZapiTestResultReporter]");
	/*  */
    private static final String ADD_ZEPHYR_GLOBAL_CONFIG = "Please Add Zephyr Server in the Global config";
	/*  */
    private final String pInfo = String.format("%s [INFO]", PluginName);
	/*  */
    private final String prefixError = String.format("%s [ERROR]", PluginName);
	/*  */
    private static final String ZEPHYR_DEFAULT_MANAGER = "test.manager";
	/*  */
    private static final String ZEPHYR_DEFAULT_PASSWORD = "test.manager";
	/*  */
    private static final String NEW_CYCLE_KEY = "CreateNewCycle";
	/*  */

    @DataBoundConstructor
    public ZfjReporter(
			String serverAddress,
			String projectKey,
			String releaseKey,
			String cycleKey,
			String cycleDuration,
			String cyclePrefix
	) {


    	this.serverAddress = serverAddress;
        this.projectKey = projectKey;
        this.releaseKey = releaseKey;
        this.cycleKey = cycleKey;
        this.cyclePrefix = cyclePrefix;
        this.createPackage = false;
        this.cycleDuration = cycleDuration;
    }

//    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public boolean perform(final AbstractBuild build,
                           final Launcher launcher,
                           final BuildListener listener) {
        logger = listener.getLogger();
        logger.printf("%s Examining test results...%n", pInfo);
        debugLog(listener,
                 String.format("Build result is %s%n",
                    build.getResult().toString())
                );
        
  
		if (StringUtils.isBlank(serverAddress)
				|| StringUtils.isBlank(projectKey)
				|| StringUtils.isBlank(releaseKey)
				|| StringUtils.isBlank(cycleKey)
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(serverAddress.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(projectKey.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(releaseKey.trim())
				|| ADD_ZEPHYR_GLOBAL_CONFIG.equals(cycleKey.trim()))	{

			logger.println("Cannot Proceed");
			return false;
		}
		
		List<ZephyrInstance> tempZephyrInstances = null;
		
		
		try {
			tempZephyrInstances = getDescriptor().getZephyrInstances();
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.println("Cannot Proceed because tempZephyrInstances is null");
			return false;
		}
		
		for (ZephyrInstance tZI : tempZephyrInstances) {

			if (StringUtils.isNotBlank(tZI.getServerAddress())
					&& tZI.getServerAddress().trim().equals(serverAddress)) {
				username = tZI.getUsername();
				password = tZI.getPassword();
				break;
			}

		}		
         
     
        
        this.workspace = build.getWorkspace();
        debugLog(listener,
                 String.format("%s Workspace is %s%n", pInfo, this.workspace.toString())
                );
        initializeZephyrData();
        
		String hostName = URLValidator.validateURL(serverAddress);

		if(!hostName.startsWith("http")) {
			logger.println(hostName);
			logger.println("Can not create Zephyr test cases");
			logger.println("Cancelling the job.");
			
		}
        
		zephyrData.setZephyrURL(hostName);
		zephyrData.setCycleDuration(cycleDuration);
        determineProjectID(hostName);
        determineReleaseID(hostName);
        determineCycleID(hostName);
        determineCyclePrefix();
        determineTestIssueTypeId(hostName);
        
        	Map<String, Boolean> zephyrTestCaseMap = new HashMap<String, Boolean>();
        	
            TestResultAction testResultAction = (TestResultAction) build.getTestResultAction();
            Collection<SuiteResult> suites = testResultAction.getResult().getSuites();
            
            if (suites == null) {
            	logger.println("Problem parsing JUnit test Results.");
            }
            
            
            Set<String> packageNames = new HashSet<String>();
            
		for (Iterator iterator = suites.iterator(); iterator.hasNext();) {
			SuiteResult suiteResult = (SuiteResult) iterator.next();
			List<CaseResult> cases = suiteResult.getCases();
			for (CaseResult caseResult : cases) {
				boolean isPassed = caseResult.isPassed();
				String name = caseResult.getFullName();
				if (!zephyrTestCaseMap.containsKey(name)) {
					zephyrTestCaseMap.put(name, isPassed);
					packageNames.add(caseResult.getPackageName());
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
				isssueType.put("id", zephyrData.getTestIssueTypeId()+"");
				
				JSONObject project = new JSONObject();
				project.put("id", zephyrData.getZephyrProjectId());

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
            
            zephyrData.setTestcases(testcases);
			zephyrData.setPackageNames(packageNames);
			zephyrData.setCreatePackage(createPackage);
			TestCaseUtil.processTestCaseDetails(zephyrData);
            
//      }
        logger.printf("%s Done.%n", pInfo);
        return true;
    }

	/**
	 *
	 * @param hostName
	 */
	private void determineTestIssueTypeId(String hostName) {
		long testIssueTypeId = ServerInfo.findTestIssueTypeId(hostName, username, password);
		zephyrData.setTestIssueTypeId(testIssueTypeId);
	}

	/**
	 *
	 */
	private void determineCyclePrefix() {
		if (StringUtils.isNotBlank(cyclePrefix)) {
			zephyrData.setCyclePrefix(cyclePrefix+ "_");
		} else {
			zephyrData.setCyclePrefix("Automation_");
		}
	}

	/**
	 *
	 */
	private void initializeZephyrData() {
        zephyrData = new ZephyrConfigModel();
        zephyrData.setUserName(username);
        zephyrData.setPassword(password);
	}

	/**
	 *
	 * @param hostName
	 */
	private void determineCycleID(String hostName) {
		if (cycleKey.equalsIgnoreCase(NEW_CYCLE_KEY)) {
			zephyrData.setCycleId(NEW_CYCLE_KEY_IDENTIFIER);
			return;
		}
		try {
			cycleId = Long.parseLong(cycleKey);
		} catch (NumberFormatException e1) {
			logger.println("Cycle Key appears to be the name of the cycle");
			try {
				Long cycleIdByCycleNameAndReleaseId = Cycle.getCycleIdByCycleNameAndReleaseId(cycleKey, releaseId, hostName, username, password);
				cycleId = cycleIdByCycleNameAndReleaseId;
			} catch (Exception e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		zephyrData.setCycleName(cycleKey);	
		zephyrData.setCycleId(cycleId);
	}

	/**
	 *
	 * @param hostName
	 */
	private void determineReleaseID(String hostName) {

		try {
			releaseId = Long.parseLong(releaseKey);
		} catch (NumberFormatException e1) {
			logger.println("Release Key appears to be Name of the Release");
			try {
				Long releaseIdByReleaseNameProjectId = Release.getReleaseIdByNameProjectId(releaseKey, projectId, hostName, username, password);
				releaseId = releaseIdByReleaseNameProjectId;
			} catch (Exception e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		zephyrData.setReleaseId(releaseId);
	}

	/**
	 *
	 * @param hostName
	 */
	private void determineProjectID(String hostName) {
		try {
			projectId = Long.parseLong(projectKey);
		} catch (NumberFormatException e1) {
			logger.println("Project Key appears to be Name of the project");
			try {
				Long projectIdByName = Project.getProjectIdByName(projectKey, hostName, username, password);
				projectId = projectIdByName;
			} catch (Exception e) {
				e.printStackTrace();
			}
			e1.printStackTrace();
		}
		
		zephyrData.setZephyrProjectId(projectId);
	}


    void debugLog(final BuildListener listener, final String message) {
        if (!this.debugFlag) {
            return;
        }
        PrintStream logger = listener.getLogger();
//        logger.printf("%s %s%n", pDebug, message);
    }


    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

    	private List<ZephyrInstance> zephyrInstances;
		Map<Long, String> projects;
		Map<Long, String> releases;
		Map<Long, String> cycles;
    	private String tempIPAddress;
    	private int tempPortNumber;
		private String tempUserName;
		private String tempPassword;

		/**
		 *
		 * @return
		 */
    	public List<ZephyrInstance> getZephyrInstances() {
			return zephyrInstances;
		}

		/**
		 *
		 * @param zephyrInstances
		 */
		public void setZephyrInstances(List<ZephyrInstance> zephyrInstances) {
			this.zephyrInstances = zephyrInstances;
		}


		/**
		 *
		 */
		public DescriptorImpl() {
			super(ZfjReporter.class);
			load();
		}

		/**
		 *
		 * @param req
		 * @param formData
		 * @return
		 * @throws FormException
		 */
		@Override
		public Publisher newInstance(StaplerRequest req, JSONObject formData)
				throws FormException {
			return super.newInstance(req, formData);
		}

		/**
		 *
		 * @param jobType
		 * @return
		 */
        @Override
        public boolean isApplicable(final Class<? extends AbstractProject> jobType) {
            return true;
        }

		/**
		 *
		 * @param req
		 * @param formData
		 * @return
		 * @throws FormException
		 */
		@Override
		public boolean configure(StaplerRequest req, JSONObject formData)
				throws FormException {
			req.bindParameters(this);
			this.zephyrInstances = new ArrayList<ZephyrInstance>();
			Object object = formData.get("zephyrInstances");
			if(object instanceof JSONArray) {
				JSONArray jArr = (JSONArray) object;
				for (Iterator iterator = jArr.iterator(); iterator.hasNext();) {
					JSONObject jObj = (JSONObject) iterator.next();
					ZephyrInstance zephyrInstance = new ZephyrInstance();
					
					zephyrInstance.setServerAddress(jObj.getString("serverAddress").trim());
					zephyrInstance.setUsername(jObj.getString("username").trim());
					zephyrInstance.setPassword(jObj.getString("password").trim());
					this.zephyrInstances.add(zephyrInstance);
				}
				
				
				
			} else if (object instanceof JSONObject) {
				JSONObject jObj = formData.getJSONObject("zephyrInstances");
				ZephyrInstance zephyrInstance = new ZephyrInstance();
				
				zephyrInstance.setServerAddress(jObj.getString("serverAddress").trim());
				zephyrInstance.setUsername(jObj.getString("username").trim());
				zephyrInstance.setPassword(jObj.getString("password").trim());
				
				this.zephyrInstances.add(zephyrInstance);
				
			}
			save();
			return super.configure(req, formData);
		}

		/**
		 *
		 * @return
		 */
        @Override
        public String getDisplayName() {
            return "Publish test result to Zephyr for JIRA";
        }

		/**
		 *
		 * @param value
		 * @return
		 */
        public FormValidation doCheckProjectKey(@QueryParameter String value) {
        	if (value.isEmpty()) {
        		return FormValidation.error("You must provide a project key.");
        	} else {
        		return FormValidation.ok();
        	}
        }

		/**
		 *
		 * @param serverAddress
		 * @param username
		 * @param password
		 * @return
		 */
		public FormValidation doTestConnection(
				@QueryParameter String serverAddress,
				@QueryParameter String username,
				@QueryParameter String password) {
			
			
			if (StringUtils.isBlank(serverAddress)) {
				return FormValidation.error("Please enter the server name");
			}

			if (StringUtils.isBlank(username)) {
				return FormValidation.error("Please enter the username");
			}

			if (StringUtils.isBlank(password)) {
				return FormValidation.error("Please enter the password");
			}

			if (!(serverAddress.trim().startsWith("https://") || serverAddress.trim().startsWith("http://"))) {
				return FormValidation.error("Incorrect server address format");
			}
			
			String jiraURL = URLValidator.validateURL(serverAddress);
			
			if(!jiraURL.startsWith("http")) {
				return FormValidation.error(jiraURL);
			}
			
			if (!ServerInfo.findServerAddressIsValidZephyrURL(jiraURL)) {
				return FormValidation.error("This is not a valid Jira Server");
			}
			
			if (!ServerInfo.validateCredentials(jiraURL, username, password)) {
				return FormValidation.error("Invalid user credentials");
			}
			
			return FormValidation.ok("Connection to JIRA has been validated");
		}

		/**
		 *
		 * @param serverAddress
		 * @return
		 */
        public ListBoxModel doFillServerAddressItems(@QueryParameter String serverAddress) {
        	
            ListBoxModel m = new ListBoxModel();
            
			if (this.zephyrInstances.size() > 0) {
				for (ZephyrInstance s : this.zephyrInstances) {
					m.add(s.getServerAddress());
				}
			} else if (StringUtils.isBlank(serverAddress)
					|| serverAddress.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)) {
				m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
			} else {
				m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
			}
            return m;
        }

		/**
		 *
		 * @param serverAddress
		 * @return
		 */
        public ListBoxModel doFillProjectKeyItems(@QueryParameter String serverAddress) {
    		ListBoxModel m = new ListBoxModel();
    		
    		String hostNameWithProtocol = getHostNameWithProtocol(serverAddress);
    		
			if (StringUtils.isBlank(serverAddress)
					|| serverAddress.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
					|| (this.zephyrInstances.size() == 0)) {
				m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
				return m;
			}
        	
        	tempUserName = ZEPHYR_DEFAULT_MANAGER;
        	tempPassword = ZEPHYR_DEFAULT_PASSWORD;
        	
        	for (ZephyrInstance z: zephyrInstances) {
        		if(z.getServerAddress().trim().equals(serverAddress)) {
        			tempUserName = z.getUsername();
        			tempPassword = z.getPassword();
        		}
        	}
        	
    		projects = Project.getAllProjects(hostNameWithProtocol, tempUserName, tempPassword);
    		Set<Entry<Long, String>> projectEntrySet = projects.entrySet();

    		for (Iterator iterator = projectEntrySet.iterator(); iterator.hasNext();) {
				Entry<Long, String> entry = (Entry<Long, String>) iterator.next();
				m.add(entry.getValue(), entry.getKey()+"");
			}
        	
            return m;
        }

		/**
		 *
		 * @param projectKey
		 * @param serverAddress
		 * @return
		 */
        public ListBoxModel doFillReleaseKeyItems(@QueryParameter String projectKey, @QueryParameter String serverAddress) {
    		String hostNameWithProtocol = getHostNameWithProtocol(serverAddress);
        	
        	ListBoxModel m = new ListBoxModel();

			if (StringUtils.isBlank(projectKey)
					|| projectKey.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
					|| (this.zephyrInstances.size() == 0)) {
				m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
				return m;
			}

        	long parseLong = Long.parseLong(projectKey);
        	
    		releases = Release.getAllReleasesByProjectID(parseLong, hostNameWithProtocol, tempUserName, tempPassword);
    		Set<Entry<Long, String>> releaseEntrySet = releases.entrySet();

    		for (Iterator iterator = releaseEntrySet.iterator(); iterator.hasNext();) {
				Entry<Long, String> entry = (Entry<Long, String>) iterator.next();
				m.add(entry.getValue(), entry.getKey()+"");
			}
        	
            return m;

        }

		/**
		 *
		 * @param releaseKey
		 * @param serverAddress
		 * @return
		 */
        public ListBoxModel doFillCycleKeyItems(@QueryParameter String releaseKey, @QueryParameter String serverAddress) {
    		String hostNameWithProtocol = getHostNameWithProtocol(serverAddress);
        	ListBoxModel m = new ListBoxModel();

			if (StringUtils.isBlank(releaseKey)
					|| releaseKey.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
					|| (this.zephyrInstances.size() == 0)) {
				m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
				return m;
			}
        	
        	long parseLong = Long.parseLong(releaseKey);

       		cycles = Cycle.getAllCyclesByReleaseID(parseLong, hostNameWithProtocol, tempUserName, tempPassword);
    		Set<Entry<Long, String>> releaseEntrySet = cycles.entrySet();

    		for (Iterator iterator = releaseEntrySet.iterator(); iterator.hasNext();) {
				Entry<Long, String> entry = (Entry<Long, String>) iterator.next();
				m.add(entry.getValue(), entry.getKey()+"");
			}
    		
    		m.add("New Cycle", NEW_CYCLE_KEY);
        	
            return m;
        }

		/**
		 *
		 * @param releaseKey
		 * @param serverAddress
		 * @return
		 */
        public ListBoxModel doFillCycleDurationItems(@QueryParameter String releaseKey, @QueryParameter String serverAddress) {
        	ListBoxModel m = new ListBoxModel();
        	m.add("30 days");
        	m.add("7 days");
        	m.add("1 day");
        	return m;
        }

    }

	/**
	 *
	 * @param urlString
	 * @return
	 */
    private static String getHostNameWithProtocol(String urlString) {
		URL url = null;
		String result = null;
			try {
				url = new URL(urlString);
				result = url.getProtocol();
				result += "://";
				result += url.getHost();
				
				int port = url.getPort();
				if (port > 0) {
					result += ":";
					result += port;
					
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return result;
    }
    
}

