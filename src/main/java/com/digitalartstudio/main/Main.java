package com.digitalartstudio.main;

import com.digitalartstudio.bot.EtsyBot;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EtsyBot bot = new EtsyBot();
//		bot.lookupProxyList(new FoxtoolsAPI(), new ProxyListAPI(),
//							new ProxyEleven(), new PubProxy());
		

		bot.executeBatchBot("822848230", "print pisces zodiac");
	}
}
