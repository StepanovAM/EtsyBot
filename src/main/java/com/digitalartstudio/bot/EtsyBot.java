package com.digitalartstudio.bot;

import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.digitalartstudio.network.HTTPClient;

public class EtsyBot extends Bot{
	
	private String cart = "https://www.etsy.com/cart/listing.php";
	
	public void addToCart(String... listing){
		proxies.forEach(proxy -> {
			proxy.getRemoteHosts().forEach((ip, port) -> {
				new Thread(() ->  {
					for(String destUrl : listing) {
						try {
							singleRequestPuttingToCart(destUrl, ip, port);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			});
			
		});
	}
	
	public void singleRequestPuttingToCart(String listing, String ip, int port) throws Exception {
		HTTPClient client = new HTTPClient();
		client.openSecureConnectionProxy(listing, ip, port);
		client.setHeader("User-Agent", "Mozilla/5.0");
		client.setHeader("Connection", "Keep-Alive");
		client.setHTTPMethod("GET");
		
		String html = client.readHTTPBodyResponse().toString();
		String params = parsePOSTForm(html);
		String cookie = client.getConnection().getHeaderFields().get("Set-Cookie").parallelStream().map(s -> s.split(";")[0]).collect(Collectors.joining("; "));
		
		client.disconnect();
		
		client.openSecureConnectionProxy(cart, ip, port);
		client.setHeader("User-Agent", "Mozilla/5.0");
		client.setHeader("Connection", "Keep-Alive");
		client.setHeader("Cookie", cookie);
		client.setHTTPMethod("POST");
		client.writeHTTPBodyRequest(params);
		client.disconnect();
	}
	
	private String parsePOSTForm(String html) {
		Element form = Jsoup.parse(html).getElementsByClass("add-to-cart-form").first();
		return form.getElementsByTag("input").parallelStream().map(input -> input.attr("name") + "=" + input.val()).collect(Collectors.joining("&"));
	}
}
