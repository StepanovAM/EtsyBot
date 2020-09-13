package com.digitalartstudio.proxy.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digitalartstudio.network.HTTPClient;

public abstract class ProxyProvider {
	
	protected Map<String, Integer> hosts = new HashMap<>();
	
	public abstract List<String> getUrls();
	public abstract void parseResponse(StringBuilder response);
	
	public Map<String, Integer> getRemoteHosts(){
		return hosts;
	};
	public void setHosts(Map<String, Integer> hosts) {
		this.hosts = hosts;
	}
	public void updateHosts() {
		HTTPClient httpClient = new HTTPClient();
		getUrls().forEach(url -> {
			try {
				httpClient.openConnection(url);
				parseResponse(httpClient.readHTTPBodyResponse());
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
