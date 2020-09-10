package com.digitalartstudio.proxyproviders.json.foxtools;

import java.util.Map;

public class ProxyResultItem {
	
	private String ip;
	private int port;
	private int type;
	private String anonymity;
	private double uptime;
	private Object checked;
	private String available;
	private String free;
	private Map<String, String> country;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAnonymity() {
		return anonymity;
	}
	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}
	public double getUptime() {
		return uptime;
	}
	public void setUptime(double uptime) {
		this.uptime = uptime;
	}
	public Object getChecked() {
		return checked;
	}
	public void setChecked(Object checked) {
		this.checked = checked;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public Map<String, String> getCountry() {
		return country;
	}
	public void setCountry(Map<String, String> country) {
		this.country = country;
	}
}
