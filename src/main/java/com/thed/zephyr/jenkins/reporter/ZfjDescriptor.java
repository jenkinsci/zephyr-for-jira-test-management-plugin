package com.thed.zephyr.jenkins.reporter;

import static com.thed.zephyr.jenkins.reporter.ZfjConstants.ADD_ZEPHYR_GLOBAL_CONFIG;
import static com.thed.zephyr.jenkins.reporter.ZfjConstants.CYCLE_DURATION_1_DAY;
import static com.thed.zephyr.jenkins.reporter.ZfjConstants.CYCLE_DURATION_30_DAYS;
import static com.thed.zephyr.jenkins.reporter.ZfjConstants.CYCLE_DURATION_7_DAYS;
import static com.thed.zephyr.jenkins.reporter.ZfjConstants.NAME_POST_BUILD_ACTION;
import static com.thed.zephyr.jenkins.reporter.ZfjConstants.NEW_CYCLE_KEY;
import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.thed.zephyr.jenkins.model.ZephyrInstance;
import com.thed.zephyr.jenkins.utils.ConfigurationValidator;
import com.thed.zephyr.jenkins.utils.URLValidator;
import com.thed.zephyr.jenkins.utils.rest.Cycle;
import com.thed.zephyr.jenkins.utils.rest.Project;
import com.thed.zephyr.jenkins.utils.rest.RestClient;
import com.thed.zephyr.jenkins.utils.rest.ServerInfo;
import com.thed.zephyr.jenkins.utils.rest.Version;

@Extension
public final class ZfjDescriptor extends BuildStepDescriptor<Publisher> {

	private List<ZephyrInstance> jiraInstances;

	public ZfjDescriptor() {
		super(ZfjReporter.class);
		load();
	}

	@Override
	public Publisher newInstance(StaplerRequest req, JSONObject formData)
			throws FormException {
		return super.newInstance(req, formData);
	}

    @Override
    public boolean isApplicable(final Class<? extends AbstractProject> jobType) {
        return true;
    }


	@Override
	public boolean configure(StaplerRequest req, JSONObject formData)
			throws FormException {
		req.bindParameters(this);
		this.jiraInstances = new ArrayList<ZephyrInstance>();
		Object object = formData.get("jiraInstances");
		if(object instanceof JSONArray) {
			JSONArray jArr = (JSONArray) object;
			for (Iterator iterator = jArr.iterator(); iterator.hasNext();) {
				JSONObject jObj = (JSONObject) iterator.next();
				ZephyrInstance zephyrInstance = new ZephyrInstance();
				
				String server = jObj.getString("serverAddress").trim();
				String user = jObj.getString("username").trim();
				String pass = jObj.getString("password").trim();
				server = StringUtils.removeEnd(server, "/");

				zephyrInstance.setServerAddress(server);
				zephyrInstance.setUsername(user);
				zephyrInstance.setPassword(pass);
				
				RestClient restClient = new RestClient(server, user, pass);

				boolean zephyrServerValidation = ConfigurationValidator
						.validateZephyrConfiguration(restClient);
				restClient.destroy();
				if (zephyrServerValidation) {
					this.jiraInstances.add(zephyrInstance);
				}

			}
			
			
			
		} else if (object instanceof JSONObject) {
			JSONObject jObj = formData.getJSONObject("jiraInstances");
			ZephyrInstance zephyrInstance = new ZephyrInstance();
			
			String server = jObj.getString("serverAddress").trim();
			String user = jObj.getString("username").trim();
			String pass = jObj.getString("password").trim();
			server = StringUtils.removeEnd(server, "/");
			
			zephyrInstance.setServerAddress(server);
			zephyrInstance.setUsername(user);
			zephyrInstance.setPassword(pass);
			
			RestClient restClient = new RestClient(server, user, pass);

			boolean zephyrServerValidation = ConfigurationValidator
					.validateZephyrConfiguration(restClient);
			restClient.destroy();
			if (zephyrServerValidation) {
				this.jiraInstances.add(zephyrInstance);
			}			
		}
		save();
		return super.configure(req, formData);
	}


    @Override
    public String getDisplayName() {
        return NAME_POST_BUILD_ACTION;
    }


    public FormValidation doCheckProjectKey(@QueryParameter String value) {
    	if (value.isEmpty()) {
    		return FormValidation.error("You must provide a project key.");
    	} else {
    		return FormValidation.ok();
    	}
    }


	public FormValidation doTestConnection(
			@QueryParameter String serverAddress,
			@QueryParameter String username,
			@QueryParameter String password) {
		
		serverAddress = StringUtils.removeEnd(serverAddress, "/");
		
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
		RestClient restClient = new RestClient(serverAddress, username, password);
		
		if (!ServerInfo.findServerAddressIsValidZephyrURL(restClient)) {
			return FormValidation.error("This is not a valid Jira Server");
		}
		
		if (!ServerInfo.validateCredentials(restClient)) {
			return FormValidation.error("Invalid user credentials");
		}
		restClient.destroy();
		return FormValidation.ok("Connection to JIRA has been validated");
	}


    public ListBoxModel doFillServerAddressItems(@QueryParameter String serverAddress) {
    	
        ListBoxModel m = new ListBoxModel();
        
		if (this.jiraInstances.size() > 0) {
			for (ZephyrInstance s : this.jiraInstances) {
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


    public ListBoxModel doFillProjectKeyItems(@QueryParameter String serverAddress) {
		ListBoxModel m = new ListBoxModel();
		
		
		if (StringUtils.isBlank(serverAddress)
				|| serverAddress.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
				|| (this.jiraInstances.size() == 0)) {
			m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
			return m;
		}
    	

		RestClient restClient = getRestclient(serverAddress);
		Map<Long, String> projects = Project.getAllProjects(restClient);

		Set<Entry<Long, String>> projectEntrySet = projects.entrySet();

		for (Iterator<Entry<Long, String>> iterator = projectEntrySet.iterator(); iterator.hasNext();) {
			Entry<Long, String> entry = iterator.next();
			m.add(entry.getValue(), entry.getKey()+"");
		}
		restClient.destroy();
        return m;
    }


	private RestClient getRestclient(String serverAddress) {
		String tempUserName = null;
		String tempPassword = null;
		for (ZephyrInstance z: jiraInstances) {
    		if(z.getServerAddress().trim().equals(serverAddress)) {
    			tempUserName = z.getUsername();
    			tempPassword = z.getPassword();
    		}
    	}
			RestClient restClient = new RestClient(serverAddress, tempUserName, tempPassword);
			
			return restClient;
	}


    public ListBoxModel doFillVersionKeyItems(@QueryParameter String projectKey, @QueryParameter String serverAddress) {
    	
    	ListBoxModel m = new ListBoxModel();

		if (StringUtils.isBlank(projectKey)
				|| projectKey.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
				|| (this.jiraInstances.size() == 0)) {
			m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
			return m;
		}

    	long parseLong = Long.parseLong(projectKey);
    	
    	RestClient restClient = getRestclient(serverAddress);
		Map<Long, String> versions = Version.getVersionsByProjectID(parseLong, restClient);

		Set<Entry<Long, String>> versionEntrySet = versions.entrySet();

		for (Iterator<Entry<Long, String>> iterator = versionEntrySet.iterator(); iterator.hasNext();) {
			Entry<Long, String> entry = iterator.next();
			m.add(entry.getValue(), entry.getKey()+"");
		}
		restClient.destroy();
        return m;

    }


    public ListBoxModel doFillCycleKeyItems(@QueryParameter String versionKey, @QueryParameter String serverAddress, @QueryParameter String projectKey) {
    	ListBoxModel m = new ListBoxModel();

		if (StringUtils.isBlank(versionKey)
				|| versionKey.trim().equals(ADD_ZEPHYR_GLOBAL_CONFIG)
				|| (this.jiraInstances.size() == 0)) {
			m.add(ADD_ZEPHYR_GLOBAL_CONFIG);
			return m;
		}
    	
    	long parseLong = Long.parseLong(versionKey);

    	RestClient restClient = getRestclient(serverAddress);
		Map<Long, String> cycles = Cycle.getAllCyclesByVersionId(parseLong, restClient, projectKey);
		
		Set<Entry<Long, String>> cycleEntrySet = cycles.entrySet();

		for (Iterator<Entry<Long, String>> iterator = cycleEntrySet.iterator(); iterator.hasNext();) {
			Entry<Long, String> entry = iterator.next();
			m.add(entry.getValue(), entry.getKey()+"");
		}
		
		m.add("New Cycle", NEW_CYCLE_KEY);
		restClient.destroy();
        return m;
    }


    public ListBoxModel doFillCycleDurationItems(@QueryParameter String versionKey, @QueryParameter String serverAddress) {
    	ListBoxModel m = new ListBoxModel();
    	m.add(CYCLE_DURATION_30_DAYS);
    	m.add(CYCLE_DURATION_7_DAYS);
    	m.add(CYCLE_DURATION_1_DAY);
    	return m;
    }

	public List<ZephyrInstance> getJiraInstances() {
		return jiraInstances;
	}

	public void setJiraInstances(List<ZephyrInstance> jiraInstances) {
		this.jiraInstances = jiraInstances;
	}

}