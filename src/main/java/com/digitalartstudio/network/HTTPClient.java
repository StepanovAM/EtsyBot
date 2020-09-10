package com.digitalartstudio.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class HTTPClient {
	
	private HttpURLConnection connection;
	private Proxy webProxy;
	private Map<String, String> sessCookies = new HashMap<>();    
	
	public HTTPClient() {}
	
	public HTTPClient(String proxyIp, int proxyPort, String type) {
		switch(type) {
			case "HTTP": webProxy  = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort)); break;
			case "SOCKS": webProxy  = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyIp, proxyPort)); break;
			default: webProxy  = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort)); break;
		}
	}
	
	public void openConnectionProxy(String destUrl) throws MalformedURLException, IOException {
		connection = (HttpURLConnection) new URL(destUrl).openConnection(webProxy);
	}
	
	public void openSecureConnectionProxy(String destUrl) throws MalformedURLException, IOException {
//		openSecureConnection(destUrl);
		connection = (HttpsURLConnection) new URL(destUrl).openConnection(webProxy);
	}
	
	public void openConnection(String destUrl) throws MalformedURLException, IOException {
		connection = (HttpURLConnection) new URL(destUrl).openConnection();
	}
	
	public void openSecureConnection(String destUrl) throws MalformedURLException, IOException {
		connection = (HttpsURLConnection) new URL(destUrl).openConnection();
	}
	
	public void setHTTPMethod(String method) throws ProtocolException {
		connection.setRequestMethod(method);
	}
	
	public boolean usingProxy() {
		return connection.usingProxy();
	}
	
	public void setCookiesAutomatically() {
		StringBuilder sb = new StringBuilder();
		sessCookies.forEach((key, value) -> sb.append(key + "=" + value + "; "));
		if(sb.length() > 0) connection.setRequestProperty("Cookie", sb.toString());
	}
	
	public void setHeader(String key, String value) {
		connection.setRequestProperty(key, value);
	}
	
	public void connect() throws IOException {
		connection.connect();
	}
	
	public int getResponseCode() throws IOException {
		return connection.getResponseCode();
	}
	
	public List<String> separateResponseCookieFromMeta(){
		return connection.getHeaderFields().get("Set-Cookie").parallelStream().map(str -> str.split(";")[0]).collect(Collectors.toList());
	}
	
	public void setDeafaultOptions(String method) throws Exception {
		setHeader("User-Agent", "Mozilla/5.0");
		setHeader("Connection", "Keep-Alive");
		setHTTPMethod(method);
	}
	
	public StringBuilder readHTTPBodyResponse() throws Exception{
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line + System.lineSeparator());
            }
            return response;
        }
	}
	
	public boolean writeHTTPBodyRequest(String body)  {
		connection.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
            wr.writeBytes(body);
            wr.flush();
        }catch(IOException e) {
        	e.printStackTrace();
        	return false;
        }
        return true;
	}
	
	public void disconnect() {
		connection.disconnect();
	}
	
	public HttpURLConnection getConnection() {
		return connection;
	}
	
	public void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	public Proxy getWebProxy() {
		return webProxy;
	}

	public void setWebProxy(Proxy webProxy) {
		this.webProxy = webProxy;
	}

	public Map<String, String> getSessCokies() {
		return sessCookies;
	}

	public void setSessCokies(Map<String, String> sessCokies) {
		this.sessCookies = sessCokies;
	}
}
