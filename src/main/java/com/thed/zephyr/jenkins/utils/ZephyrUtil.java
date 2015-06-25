package com.thed.zephyr.jenkins.utils;

public class ZephyrUtil {

	public static String findIpAddress(String URL) {

		String ipAddressPort = IPAddressPortMatcher.getIpAddressPort(URL);
		
		String[] split = ipAddressPort.split(":");
		
		return split[0];
		
		}
	
	public static int findPort(String URL) {

		int portNumber;
		String ipAddressPort = IPAddressPortMatcher.getIpAddressPort(URL);
		
		String[] split = ipAddressPort.split(":");
		
		if (split.length > 1) {
			portNumber = Integer.parseInt(split[1]);
		} else {
			portNumber = 80;
		}

		return portNumber;

	}
	
}
