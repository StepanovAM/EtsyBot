package com.digitalartstudio.proxy.providers;

import java.util.List;

import com.digitalartstudio.proxy.providers.json.proxyeleven.ProxyElevenJSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProxyElevenProxyProvider extends ProxyProvider{
	
	private String apikey = "MTU1Mg.XxRZQQ.CJpDS90pHWAbgerIkF1hD1_rkH4";
	
	@Override
	public List<String> getUrls() {
		return List.of("https://proxy11.com/api/proxy.json?key=" + apikey);
	}

	@Override
	public void parseResponse(StringBuilder json) {
		try {
			ProxyElevenJSON response =  new ObjectMapper().readValue(json.toString(), ProxyElevenJSON.class);
			response.getData().forEach(data -> hosts.put(data.getIp(), data.getPort()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
