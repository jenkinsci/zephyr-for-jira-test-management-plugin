package com.thed.zephyr.jenkins.model;

public class ZephyrCloudInstance {
	private String jiraCloudAddress;
	private String zephyrCloudAddress;
	private String jiraCloudUserName;
	private String jiraCloudPassword;
	private String zephyrCloudAccessKey;
	private String zephyrCloudSecretKey;

	public String getJiraCloudAddress() {
		return jiraCloudAddress;
	}

	public void setJiraCloudAddress(String jiraCloudAddress) {
		this.jiraCloudAddress = jiraCloudAddress;
	}

	public String getZephyrCloudAddress() {
		return zephyrCloudAddress;
	}

	public void setZephyrCloudAddress(String zephyrCloudAddress) {
		this.zephyrCloudAddress = zephyrCloudAddress;
	}

	public String getJiraCloudUserName() {
		return jiraCloudUserName;
	}

	public void setJiraCloudUserName(String jiraCloudUserName) {
		this.jiraCloudUserName = jiraCloudUserName;
	}

	public String getJiraCloudPassword() {
		return jiraCloudPassword;
	}

	public void setJiraCloudPassword(String jiraCloudPassword) {
		this.jiraCloudPassword = jiraCloudPassword;
	}

	public String getZephyrCloudAccessKey() {
		return zephyrCloudAccessKey;
	}

	public void setZephyrCloudAccessKey(String zephyrCloudAccessKey) {
		this.zephyrCloudAccessKey = zephyrCloudAccessKey;
	}

	public String getZephyrCloudSecretKey() {
		return zephyrCloudSecretKey;
	}

	public void setZephyrCloudSecretKey(String zephyrCloudSecretKey) {
		this.zephyrCloudSecretKey = zephyrCloudSecretKey;
	}

}
