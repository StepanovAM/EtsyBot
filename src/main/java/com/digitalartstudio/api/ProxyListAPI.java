package com.digitalartstudio.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class ProxyListAPI implements ProxyAPI{
	
	private Map<String, Integer> inetAddresses;
	private String IP_REGEXP = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)";
	
	public ProxyListAPI() {
		this.inetAddresses = new HashMap<>();
	}
	
	public ProxyListAPI(Map<String, Integer> inetAddress) {
		this.inetAddresses = inetAddress;
	}
	
	@Override
	public Map<String, Integer> getRemoteHosts() {
		return inetAddresses;
	}

	@Override
	public void parseResponse(StringBuilder response) {
		Pattern pattern = Pattern.compile(IP_REGEXP);
		Stream.of(response.toString().split(System.lineSeparator())).forEach(str -> {
			if(pattern.matcher(str).matches()) {
				String split[] =  str.split(":");
				inetAddresses.put(split[0], Integer.parseInt(split[1]));
			}
		});
		
		System.out.println();
	}

	@Override
	public List<String> getUrls() {
		return List.of("https://www.proxy-list.download/api/v1/get?type=http");
	}
}
