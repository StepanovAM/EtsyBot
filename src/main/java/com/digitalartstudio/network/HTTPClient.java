package com.digitalartstudio.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class HTTPClient {
	
	private HttpURLConnection connection;
	

	public void openConnectionProxy(String destUrl, String proxyIp, int proxyPort) throws MalformedURLException, IOException {
		Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
		connection = (HttpURLConnection) new URL(destUrl).openConnection(webProxy);
	}
	
	public void openSecureConnectionProxy(String destUrl, String proxyIp, int proxyPort) throws MalformedURLException, IOException {
		Proxy webProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp, proxyPort));
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
	
	public void setHeader(String key, String value) {
		connection.setRequestProperty(key, value);
	}
	
	public void connect() throws IOException {
		connection.connect();
	}
	
	public int getResponseCode() throws IOException {
		return connection.getResponseCode();
	}
	
	public Map<String, List<String>> getCookies(){
		return connection == null ? null : connection.getHeaderFields();
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
}
