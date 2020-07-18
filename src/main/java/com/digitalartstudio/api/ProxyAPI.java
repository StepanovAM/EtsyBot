package com.digitalartstudio.api;

import java.util.List;
import java.util.Map;

public interface ProxyAPI {
	
	List<String> getUrls();
	Map<String, Integer> getRemoteHosts();
	void parseResponse(StringBuilder response);
}
