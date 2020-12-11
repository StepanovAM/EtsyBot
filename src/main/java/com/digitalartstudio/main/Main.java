package com.digitalartstudio.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.digitalartstudio.bot.EtsyBot;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EtsyBot bot = new EtsyBot();
		bot.launchProxyFilter();
		bot.launchProxyUpdater();
		
		
		Map<String, List<String>> data = new HashMap<>();
		data.put("867167949", List.of("seasonal sign", "halloween poster"));
		bot.executeInPoolThreadBatchBot(data);
//		bot.executeInPoolThreadBatchBot("867167949", "happy halloween art");
//		bot.executeInPoolThreadBatchBot("867167949", "pumpkin sign svg");
		
//		bot.executeInPoolThreadBatchBot("822847738", "instant zodiac sign");
//		bot.executeInPoolThreadBatchBot("822847738", "aquarius birth sign");
//		bot.executeInPoolThreadBatchBot("822847738", "zodiac wall art");
//		bot.executeInPoolThreadBatchBot("822847738", "zodiac sign print");
		
	}
}
