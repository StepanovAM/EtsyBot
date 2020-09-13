package com.digitalartstudio.proxy.providers;

import java.util.List;

import com.digitalartstudio.proxy.providers.json.foxtools.ResponseJSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FoxtoolsProxyProvider extends ProxyProvider{
	
	private ResponseJSON response;

	@Override
	public void parseResponse(StringBuilder json) {
		try {
			response = new ObjectMapper().readValue(json.toString(), FoxtoolsProxyProvider.class).getResponse();
			response.getItems().forEach(result -> hosts.put(result.getIp(), result.getPort()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getUrls() {
		return List.of("http://api.foxtools.ru/v2/Proxy",
				"http://api.foxtools.ru/v2/Proxy?page=2");
	}

	public ResponseJSON getResponse() {
		return response;
	}

	public void setResponse(ResponseJSON response) {
		this.response = response;
	}
}
