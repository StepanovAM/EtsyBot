package com.digitalartstudio.proxyproviders;

import java.util.List;

import com.digitalartstudio.proxyproviders.json.pubproxy.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PubProxyProxyProvider extends ProxyProvider{
	
	@Override
	public List<String> getUrls() {
		return List.of("http://pubproxy.com/api/proxy?limit=20",
				"http://pubproxy.com/api/proxy?limit=20");
	}

	@Override
	public void parseResponse(StringBuilder json) {
		try {
			Response response = new ObjectMapper().readValue(json.toString(), Response.class);
			response.getData().forEach(data -> hosts.put(data.getIp(), data.getPort()));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
