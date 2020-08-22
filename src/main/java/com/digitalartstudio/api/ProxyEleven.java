package com.digitalartstudio.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.digitalartstudio.json.proxyeleven.Data;
import com.digitalartstudio.json.proxyeleven.ProxyElevenJSON;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProxyEleven implements ProxyAPI{
	
	private ProxyElevenJSON response;
	private String apikey = "MTU1Mg.XxRZQQ.CJpDS90pHWAbgerIkF1hD1_rkH4";
	
	@Override
	public List<String> getUrls() {
		return List.of("https://proxy11.com/api/proxy.json?key=" + apikey);
	}

	@Override
	public Map<String, Integer> getRemoteHosts() {
		return response != null ? response.getData().stream().collect(Collectors.toMap(Data::getIp, Data::getPort)) : null;
	}

	@Override
	public void parseResponse(StringBuilder response) {

		try {
			this.response =  new ObjectMapper().readValue(response.toString(), ProxyElevenJSON.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
