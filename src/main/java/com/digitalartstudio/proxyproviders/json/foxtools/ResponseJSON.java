package com.digitalartstudio.proxyproviders.json.foxtools;

import java.util.List;

public class ResponseJSON {
	private int pageNumber;
	private int pageCount;
	private List<ProxyResultItem> items;
	private List<Message> messages;
	private List<TraceItem> trace;
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<ProxyResultItem> getItems() {
		return items;
	}
	public void setItems(List<ProxyResultItem> items) {
		this.items = items;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<TraceItem> getTrace() {
		return trace;
	}
	public void setTrace(List<TraceItem> trace) {
		this.trace = trace;
	}
}
