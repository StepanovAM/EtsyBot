package com.digitalartstudio.bot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.digitalartstudio.network.HTTPClient;
import com.digitalartstudio.proxy.ProxyWorker;
import com.digitalartstudio.proxy.providers.ProxyProvider;

public class Bot {
	
	protected Map<String, Integer> whiteListHosts = new ConcurrentHashMap<>();
	protected ProxyWorker pWorker = new ProxyWorker(whiteListHosts);
	
	public void lookupProxyList(ProxyProvider... proxyProvider) {
		pWorker.reveal(proxyProvider);
	}
	
	public void launchProxyFilter() {
		whiteListHosts = pWorker.filterHosts();
	}
	
	public void launchProxyUpdater() {
		pWorker.launchUpdater();
	}
	
	public void removeHost(String ip, int port) {
		whiteListHosts.remove(ip, port);
		
		if(whiteListHosts.size() < 20 && pWorker.getpUpdater() != null) 
			pWorker.updateHosts();
	}
	
	public void viewPage(HTTPClient client, String... pages) throws Exception {
		for(String destUrl : pages) {
			client.openSecureConnectionProxy(destUrl);
			client.setDeafaultOptions("GET");
			client.setCookiesAutomatically();
			client.readHTTPBodyResponse();
		}
	}
}
