package com.digitalartstudio.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.digitalartstudio.proxy.providers.ProxyProvider;

public class ProxyWorker {
	
	private List<ProxyProvider> proxyProviders = new ArrayList<>();
	private ProxyUpdater pUpdater;
	public volatile boolean isNotifyingUpdater;
	
	public void reveal(ProxyProvider... proxyProvider) {
		Stream.of(proxyProvider).forEach(proxy -> {
			proxy.updateHosts();
			proxyProviders.add(proxy);
		});
	}
	
	public ConcurrentMap<String, Integer> filterHosts() {
		return proxyProviders.stream()
			.flatMap(proxy -> proxy.getRemoteHosts().entrySet().stream())
			.collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue, (existing, replacement) -> existing));
	}

	public void launchUpdater() {
		pUpdater = new ProxyUpdater();
		pUpdater.setDaemon(true);
		pUpdater.start();
	}
	
	public void updateHosts() {
		synchronized(pUpdater) {
			pUpdater.notify();
		}
	}
	
	public ProxyUpdater getpUpdater() {
		return pUpdater;
	}

	public void setpUpdater(ProxyUpdater pUpdater) {
		this.pUpdater = pUpdater;
	}

	public List<ProxyProvider> getProxyProviders() {
		return proxyProviders;
	}

	public void setProxyProviders(List<ProxyProvider> proxyProviders) {
		this.proxyProviders = proxyProviders;
	}
	
	
	private class ProxyUpdater extends Thread{
		@Override
		public void run() {
			try {
				synchronized(this) {
					while(true) {
						proxyProviders.forEach(proxy -> proxy.updateHosts());
						wait();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}