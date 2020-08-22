package com.digitalartstudio.bot;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;
import com.digitalartstudio.api.ProxyAPI;
import com.digitalartstudio.network.HTTPClient;

public abstract class Bot {
	
	protected List<ProxyAPI> proxies;
	
	public Bot() {
		proxies = new ArrayList<>();
	}
	
	public void viewPage(String... pages) {
		proxies.forEach(proxy -> {
			proxy.getRemoteHosts().forEach((proxyIp, proxyPort) -> {
				new Thread(() -> {
					for(String destUrl : pages) {
						HTTPClient httpClient = new HTTPClient();
						try {
							httpClient.openConnectionProxy(destUrl, proxyIp, proxyPort);
							httpClient.setHeader("User-Agent", "Mozilla/5.0");
							httpClient.setHeader("Keep-Alive", "300");
							httpClient.setHTTPMethod("GET");
							httpClient.connect();
							System.out.println("OK: " + httpClient.getResponseCode() + ", using proxy? " + httpClient.usingProxy() + ", " + proxyIp + ":" + proxyPort);
						}catch(Exception e) {
							e.printStackTrace();
							break;
						}
						httpClient.disconnect();
					}
				}).start();
			});
			
		});
	}
	
	public void lookupProxyList(ProxyAPI... api) {
		HTTPClient httpClient = new HTTPClient();
		Stream.of(api).forEach(proxy -> {
			proxy.getUrls().forEach(url -> {
				try {
					httpClient.openConnection(url);
					proxy.parseResponse(httpClient.readHTTPBodyResponse());
				}catch (Exception e) {
					e.printStackTrace();
				}
				httpClient.disconnect();
			});
			proxies.add(proxy);
		});
	}
}
