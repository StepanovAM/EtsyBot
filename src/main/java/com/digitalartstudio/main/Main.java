package com.digitalartstudio.main;

import com.digitalartstudio.api.FoxtoolsAPI;
import com.digitalartstudio.api.ProxyEleven;
import com.digitalartstudio.api.ProxyListAPI;
import com.digitalartstudio.api.PubProxy;
import com.digitalartstudio.bot.EtsyBot;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EtsyBot bot = new EtsyBot();
		bot.lookupProxyList(new FoxtoolsAPI(), new ProxyListAPI(),
							new ProxyEleven(), new PubProxy());
		
		String tag = "pisces zodiac sign print";
		String id = "822848230";
		
		bot.executeBot(id, tag);
	}
}
