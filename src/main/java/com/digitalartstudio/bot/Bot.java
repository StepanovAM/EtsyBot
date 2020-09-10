package com.digitalartstudio.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.digitalartstudio.network.HTTPClient;
import com.digitalartstudio.proxyproviders.ProxyProvider;

public class Bot {
	
	protected List<ProxyProvider> proxyProviders = new ArrayList<>();
	
	public void viewPage(HTTPClient client, String... pages) throws Exception {
		for(String destUrl : pages) {
			client.openSecureConnectionProxy(destUrl);
			client.setDeafaultOptions("GET");
			client.setCookiesAutomatically();
			client.readHTTPBodyResponse();
		}
	}
	
	public void lookupProxyList(ProxyProvider... proxyProvider) {
		Stream.of(proxyProvider).forEach(proxy -> {
			proxy.updateHosts();
			proxyProviders.add(proxy);
		});
	}
	
	public void launchProxyUpdater() {
		proxyProviders.stream().forEach(proxy -> proxy.updateHosts());
	}
}
