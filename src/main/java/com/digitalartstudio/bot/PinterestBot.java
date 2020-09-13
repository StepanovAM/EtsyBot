package com.digitalartstudio.bot;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.digitalartstudio.network.HTTPClient;

public class PinterestBot {
	
	public static void main(String[] args) throws Exception {
		new PinterestBot().addingPinToBoard();
	}
	
	public void addingPinToBoard() throws Exception {
		HTTPClient client = new HTTPClient();
		client.openConnection("https://www.pinterest.ru/pin/623607879647990316/");
		client.setDeafaultOptions("GET");
		client.getSessCokies().put("_pinterest_sess", "TWc9PSZpc29SaXFPTW4xeHdCcWJ5ZlRac2crZTlZY2pzdndqbnRnbjU5czJUdmFqazE1QlUxOTZQYlRBaUJYaTJIdDRkSVFVcW9wMHAvbGRSTkFnbC9aWlAxNlgvYm04MXZSNGxwK2NsSWY3N2kzdllGRlB2SDAremlBY0k3bFpFMlBuOEpjRi9rZS9TZ0FuWWRFYnErT2hDUVhTZ1l0MTRNdWFBYU9sMHNMbVMrY29CNGt4akdDcnFlKzJmKzRwZ2ZEcEc1K1BKTHU4eWFjTDJkNHY4b3k2Nkx0RERRbHRJVXZxeHh3VFpCd3NaZWVuMkxkczd5QXVyRE5WbktKdXdLY25OY2psRTBUSFNkNVNLYWJoYWlHQnI3bmlXeDVFNExSdWJJK2N6enoybkdWc0RJUlFLRlAycjZ6NHM2MmVnSWhsdEZyN2F3OUpqZndKN09OU2d6ZVlHMk1vS2VEOUNDbXFmK2xXaFVLREdrK3ZaTU4yckw1SUc5SWNrdmdFQW5oVUZORmhMeEJJdm1kTFdZZEN6QlRzZ3JRZ1A2azEvVUkweWF0MExCTmdNZ2d3Y1RKcTNTQndHNVlMVWFrNDBUbEptd29tYXZJc0ozZjBtbmJLai9lWUVYcUJ6cTVFcU9HY2s0Q0xBYnU1VUdaZ3pNL3NlaHdEWE93R0pBUWYzUzE4NnFia25Ed0lBcWJRcVg3UUV5a3MrQnprT09MSzcvQUNMR0c2Z3hNNG9RZWlzb3ovOHRKUkZVN3R6Mit1UXNTTVk3RFBzRjdJemtkZTJtY3FpN0FzdkczZkRXbTcxcHR2R0c5RTBRRlJXQm9IRSthbUVtcmgrZktGUm1qWmxtbm0zeStvVmQzZjVwQkcxWld3QjNKWGZZb25CY0M2VGdIaXZidGIyZ2JJS1BMa2duL2IyNEdGSE5ETGVMRWdZeUs0bEdZMHVPRlVMMWlROWhVaFp2M09lcXdRMHdvRk00eVpyWDU4TFgvcU9pSGRGVTU5OGZJeFExaGZ6ZVRPY3I5a2pjQkVTRTlWKzYzWDlZenNuaGh0b0NQaUVVUDNzUGR2QVVuRGNSV3IzNWd6ajcxRERrM1JaTVIvdWlNT3VqQ1J2Jjc2OEpQamtkcW5Ha0hOZlh1R3ltNTdQSkZZST0=");
		client.setCookiesAutomatically();
		client.separateResponseCookieFromMeta().stream().peek(System.out::println).forEach(cookie -> client.getSessCokies().put(cookie.split("=")[0], cookie.split("=")[1]));   
		client.disconnect();
		
		System.out.println();
		
		client.openConnection("https://www.pinterest.ru/pin/623607879647990316/");
		client.setDeafaultOptions("GET");
		client.setCookiesAutomatically();
		client.separateResponseCookieFromMeta().stream().peek(System.out::println);
		client.disconnect();
		
		System.out.println();
		
//		client.openConnection("https://www.pinterest.ru/resource/ActiveUserResource/create/");
//		client.setDeafaultOptions("POST");
//		client.setCookiesAutomatically();
//		String params = "source_url=%2Fpin%2F623607879647990316%2F&data=%7B%22options%22%3A%7B%22data%22%3A%7B%22browser%22%3A1%2C%22clientUUID%22%3A%22054267b3-f52e-4536-9ffa-a5cc69e861ee%22%2C%22event_type%22%3A7137%2C%22view_type%22%3A3%2C%22unauth_id%22%3A%22aba90e4e77d34343bfad491366fcf4d7%22%2C%22appVersion%22%3A%2268243e5%22%2C%22auxData%22%3A%7B%22stage%22%3A%22prod%22%7D%7D%2C%22no_fetch_context_on_resource%22%3Afalse%7D%2C%22context%22%3A%7B%7D%7D";
//		client.writeHTTPBodyRequest(params);
//		System.out.println(client.getResponseCode() + " " + client.getConnection().getResponseMessage());
//		client.separateResponseCookieFromMeta().forEach(cookie -> client.getSessCokies().put(cookie.split("=")[0], cookie.split("=")[1]));
//		client.disconnect();
		
		client.openConnection("https://www.pinterest.ru/resource/PinResource/delete/");
		client.setDeafaultOptions("POST");
		client.setCookiesAutomatically();
		String params1 = "source_url=/jerixe5113/etsyshops/&data={\"options\":{\"id\":\"623607879647990316\",\"no_fetch_context_on_resource\":false},\"context\":{}}";
		client.writeHTTPBodyRequest(params1);
		System.out.println("OK: " + client.getResponseCode() + " " + client.getConnection().getResponseMessage());
		client.separateResponseCookieFromMeta().stream().peek(System.out::println);
	}
	
	
	public void writeToFile(String str, String fileName) throws Exception {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.write(str);
	    writer.close();
	}
}
