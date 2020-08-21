package com.digitalartstudio.bot;

import java.util.HashMap;
import java.util.Map;
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
		client.setDeafaultOptions("GET");
		
		String html = client.readHTTPBodyResponse().toString();
		String params = parseAddigToCartPOSTForm(html);
		String cookie = client.getConnection().getHeaderFields().get("Set-Cookie").parallelStream().map(s -> s.split(";")[0]).collect(Collectors.joining("; "));
		
		client.disconnect();
		
		client.openSecureConnectionProxy(cart, ip, port);
		client.setDeafaultOptions("POST");
		client.setHeader("Cookie", cookie);
		client.writeHTTPBodyRequest(params);
		client.disconnect();
	}
	
	private String parseAddigToCartPOSTForm(String html) {
		Element form = Jsoup.parse(html).getElementsByClass("add-to-cart-form").first();
		return form.getElementsByTag("input").parallelStream().map(input -> input.attr("name") + "=" + input.val()).collect(Collectors.joining("&"));
	}
	
	
	public void searchListingByTag(String id, String tag) throws Exception {
		String correctTag = tag.replace(" ", "%20");
		final String searchQuery = "https://www.etsy.com/search?q=" + correctTag;
		
		proxies.forEach(proxy -> {
			proxy.getRemoteHosts().forEach((ip, port) -> {
				new Thread(() ->  {
					try {
						Map<String, String> sessCokies = new HashMap<>();
						
						HTTPClient client = new HTTPClient();
						
						client.openConnection("https://www.etsy.com/");
						client.setDeafaultOptions("GET");
						client.separateCookieFromMeta().forEach(cookie -> sessCokies.put(cookie.split("=")[0], cookie.split("=")[1]));
						client.disconnect();
						
						String href = findListingByTag(client, searchQuery, id, sessCokies);
						sessCokies.put("search_options", "{\"prev_search_term\":\"" + correctTag + "\",\"item_language\":null,\"language_carousel\":null}");
						if(href.length() == 0) {
							int page = 2;
							do {
								href = findListingByTag(client, searchQuery + "&page=" + page, id, sessCokies);
								page++;
							} while(page < 20 || href.length() > 0);
							
							if(href.length() == 0) 
								throw new IllegalArgumentException("Не удалось найти листинг по заданному тэгу");
						}
						
				    	client.openConnection(href);
				    	client.setDeafaultOptions("GET");
						
						sessCokies.forEach((key, value) -> client.setHeader(key, value));
						
						client.connect();
						System.out.println(client.getResponseCode() + " " + client.getConnection().getResponseMessage());
					}catch(Exception e) {
						e.printStackTrace();
					}
				}).start();
			});
			
		});
	}
	
	private String findListingByTag(HTTPClient client, String url, String id, Map<String, String> sessCokies) throws Exception {
		client.openConnection(url);
		client.setDeafaultOptions("GET");
		
		sessCokies.forEach((key, value) -> client.setHeader(key, value));
	
		String html = client.readHTTPBodyResponse().toString();
		
		client.separateCookieFromMeta().forEach(cookie -> sessCokies.put(cookie.split("=")[0], cookie.split("=")[1]));
	    client.disconnect();
	    
	    Element el = Jsoup.parse(html).getElementsByAttributeValue("data-listing-id", id).first();
		if(el == null) 
			return "";
			
		Element el1 = el.select("a").first();
		if(el1 == null) 
			return "";
		
		return el1.attr("href");
	}
}
