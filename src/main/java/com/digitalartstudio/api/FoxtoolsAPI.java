package com.digitalartstudio.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.digitalartstudio.json.foxtools.ProxyResultItem;
import com.digitalartstudio.json.foxtools.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FoxtoolsAPI implements ProxyAPI{
	
	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	@Override
	public Map<String, Integer> getRemoteHosts() {
		return response != null ? response.getItems().stream().collect(Collectors.toMap(ProxyResultItem::getIp, ProxyResultItem::getPort)) : null;
	}

	@Override

	public void parseResponse(StringBuilder json) {

		try {
			Response resp = new ObjectMapper().readValue(json.toString(), FoxtoolsAPI.class).getResponse();
			if(response == null)
				response = resp;
			else
				response.getItems().addAll(resp.getItems());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getUrls() {
		return List.of("http://api.foxtools.ru/v2/Proxy",
				"http://api.foxtools.ru/v2/Proxy?page=2");
	}
}
