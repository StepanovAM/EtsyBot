package com.digitalartstudio.bot;

import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.digitalartstudio.constants.Constants;
import com.digitalartstudio.network.HTTPClient;

public class EtsyBot extends Bot{
	private String cart = "https://www.etsy.com/cart/listing.php";
	
	public void executeBot(String id, String tag) {
		final String correctTag = tag.replace(" ", "%20");
		
		proxies.forEach(proxy -> {
			proxy.getRemoteHosts().forEach((ip, port) -> {
				new Thread(() ->  {
					try {
						HTTPClient client = new HTTPClient(ip, port, "HTTP");
						viewPage(client, Constants.ESTY);
						
						client.separateResponseCookieFromMeta().forEach(cookie -> client.getSessCokies().put(cookie.split("=")[0], cookie.split("=")[1]));   
						client.disconnect();
						
						String html = performEtsySearch(client, Constants.ESTY + "search?q=" + correctTag);
						String href = parseListingOnSearchResult(html, id);
						System.out.println(href);
						
						client.getSessCokies().put("search_options", "{\"prev_search_term\":\"" + correctTag + "\",\"item_language\":null,\"language_carousel\":null}");
						updateSessionCookie(client);
						
						while(href.length() != 0 && !href.contains("listing/" + id)) {
							html = performEtsySearch(client, href);
							href = parseListingOnSearchResult(html, id);
							updateSessionCookie(client);
							System.out.println(href);
						}
						
						if(href.length() == 0) 
							throw new IllegalArgumentException("Не удалось найти листинг по заданному тэгу");
							
						addToCart(client, href);
						System.out.println("DONE: " + client.getSessCokies().get(Constants.ETSY_UAID));
					}catch(Exception e) {
						e.printStackTrace();
					}
				}).start();
			});
		});
	}
	
	public String performEtsySearch(HTTPClient client, String url) throws Exception {
		client.openSecureConnectionProxy(url);
		client.setDeafaultOptions("GET");
		client.setCookiesAutomatically();
		return client.readHTTPBodyResponse().toString();
	}
	
	public String parseListingOnSearchResult(String html, String id) {
	    Document doc = Jsoup.parse(html);
	    
	    Element div = doc.getElementsByAttributeValue("data-listing-id", id).first();
	    if(div!=null) { 
	    	Element a = div.select("a").first();
	    	if(a!=null) return a.attr("href");
	    }
	    
		Element li = doc.getElementsByAttributeValue("aria-label", "Review Page Results").last().select("li").last();
		Element a = li.select("a").first();
		return  a.attr("href");
	}
	
	public void addToCart(HTTPClient client, String... listing) throws Exception{
		for(String destUrl : listing) {
			client.openSecureConnectionProxy(destUrl);
			client.setDeafaultOptions("GET");
			client.setCookiesAutomatically();
			updateSessionCookie(client);
			
			String html = client.readHTTPBodyResponse().toString();
			String params = parseAddigToCartPOSTForm(html);
			
			client.disconnect();
			
			client.openSecureConnectionProxy(cart);
			client.setDeafaultOptions("POST");
			client.setCookiesAutomatically();
			client.writeHTTPBodyRequest(params);
			
			System.out.println("OK: " + client.getResponseCode() + ", using proxy? " + client.usingProxy());
			
			client.disconnect();
		}
	}
	
	public String parseAddigToCartPOSTForm(String html) {
		Element form = Jsoup.parse(html).getElementsByClass("add-to-cart-form").first();
		return form.getElementsByTag("input").parallelStream().map(input -> input.attr("name") + "=" + input.val()).collect(Collectors.joining("&"));
	}
	
	private void updateSessionCookie(HTTPClient client) {
		client.separateResponseCookieFromMeta().forEach(cookie -> {
			String parts[] = cookie.split("=");
			if (!parts[0].equals(Constants.ETSY_UAID)  && !parts[0].equals(Constants.ETSY_USER_PREFS)  && !parts[0].equals(Constants.ETSY_FVE))
				client.getSessCokies().put(parts[0], parts[1]);
		});
	}
}