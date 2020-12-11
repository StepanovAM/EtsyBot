package com.digitalartstudio.bot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.ConcurrentMap;

import com.digitalartstudio.network.HTTPClient;
import com.digitalartstudio.proxy.ProxyWorker;
import com.digitalartstudio.proxy.providers.ProxyProvider;

public class Bot {
	
	protected ConcurrentMap<String, Integer> whiteListHosts;
	protected ProxyWorker pWorker = new ProxyWorker();
	
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
		
		if(whiteListHosts.size() < 470 && pWorker.getpUpdater() != null && !pWorker.isNotifyingUpdater) {
			pWorker.isNotifyingUpdater = true;
			pWorker.updateHosts();
			whiteListHosts = pWorker.filterHosts();
			pWorker.isNotifyingUpdater = false;
		}
		System.out.println("map size: " + whiteListHosts.size());
	}
	
	public void viewPage(HTTPClient client, String... pages) throws Exception {
		for(String destUrl : pages) {
			client.openSecureConnectionProxy(destUrl);
			client.setDeafaultOptions("GET");
			client.setCookiesAutomatically();
			client.readHTTPBodyResponse();
		}
	}
	
	public void writeToFile(String str, String fileName) throws Exception {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.write(str);
	    writer.close();
	}
}
