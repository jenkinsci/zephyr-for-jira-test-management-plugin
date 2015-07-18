package com.thed.zephyr.jenkins.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.thed.zephyr.jenkins.utils.rest.RestClient;
import com.thed.zephyr.jenkins.utils.rest.ServerInfo;

public class ConfigurationValidator {

	public static boolean validateZephyrConfiguration(RestClient restClient) {

		boolean status = false;
		String url = restClient.getUrl();
		String userName = restClient.getUserName();
		String password = restClient.getPassword();

		if (StringUtils.isBlank(url)) {
			return status;
		}

		if (StringUtils.isBlank(userName)) {
			return status;
		}

		if (StringUtils.isBlank(password)) {
			return status;
		}

		if (!(url.trim().startsWith("https://") || url
				.trim().startsWith("http://"))) {
			return status;
		}

		String zephyrURL = URLValidator.validateURL(url);

		if (!zephyrURL.startsWith("http")) {
			return status;
		}

		if (!ServerInfo.findServerAddressIsValidZephyrURL(restClient)) {
			return status;
		}

		boolean validCreds = ServerInfo.validateCredentials(restClient);
		if (!validCreds) {
			return status;
		}

		status = true;
		return status;
	}
}
