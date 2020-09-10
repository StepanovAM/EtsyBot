package com.digitalartstudio.proxyproviders;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ProxyListProxyProvider extends ProxyProvider{
	
	private String IP_REGEXP = "(\\d+\\.\\d+\\.\\d+\\.\\d+):(\\d+)";
	
	@Override
	public void parseResponse(StringBuilder response) {
		Pattern pattern = Pattern.compile(IP_REGEXP);
		Stream.of(response.toString().split(System.lineSeparator())).forEach(str -> {
			if(pattern.matcher(str).matches()) {
				String split[] =  str.split(":");
				hosts.put(split[0], Integer.parseInt(split[1]));
			}
		});
	}

	@Override
	public List<String> getUrls() {
		return List.of("https://www.proxy-list.download/api/v1/get?type=http");
	}
}
