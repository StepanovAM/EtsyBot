package com.digitalartstudio.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.digitalartstudio.json.pubproxy.Response;
import com.digitalartstudio.json.pubproxy.Data;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PubProxy implements ProxyAPI{
	
	private Response response;

	@Override
	public List<String> getUrls() {
		return List.of("http://pubproxy.com/api/proxy?limit=20",
				"http://pubproxy.com/api/proxy?limit=20");
	}

	@Override
	public Map<String, Integer> getRemoteHosts() {
		return response != null ? response.getData().stream().collect(Collectors.toMap(Data::getIp, Data::getPort)) : null;
	}

	@Override
	public void parseResponse(StringBuilder json) {
		try {
			Response resp = new ObjectMapper().readValue(json.toString(), Response.class);
			if(this.response == null)
				this.response = resp;
			else
				this.response.getData().addAll(resp.getData());
			System.out.println(response.getCount());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
