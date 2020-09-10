package com.digitalartstudio.main;

import com.digitalartstudio.bot.EtsyBot;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EtsyBot bot = new EtsyBot();
//		bot.lookupProxyList(new FoxtoolsAPI(), new ProxyListAPI(),
//							new ProxyEleven(), new PubProxy());
		
//		bot.executeBatchBot("867167949", "seasonal sign");
		bot.executeBatchBot("867167949", "halloween poster");
//		bot.executeBatchBot("867167949", "happy halloween art");
//		bot.executeBatchBot("867167949", "pumpkin sign svg");
	}
}
