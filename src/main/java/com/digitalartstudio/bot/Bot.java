package com.digitalartstudio.bot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.digitalartstudio.api.ProxyAPI;
import com.digitalartstudio.network.HTTPClient;

public class Bot {
	
	protected List<ProxyAPI> proxies = new ArrayList<>();
	
	public void viewPage(HTTPClient client, String... pages) throws Exception {
		for(String destUrl : pages) {
			client.openSecureConnectionProxy(destUrl);
			client.setDeafaultOptions("GET");
			client.connect();
		}
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
