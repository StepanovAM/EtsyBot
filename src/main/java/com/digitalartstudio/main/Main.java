package com.digitalartstudio.main;

import com.digitalartstudio.bot.EtsyBot;

public class Main {
	
	public static void main(String[] args) throws Exception {
//		Bot bot = new Bot();
//		bot.lookupProxyList(new FoxtoolsAPI());
//		bot.lookupProxyList(new ProxyListAPI());
//		bot.lookupProxyList(new ProxyEleven());
//		bot.lookupProxyList(new PubProxy());
//		bot.viewPage("https://www.etsy.com/listing/840837007/set-of-zodiac-signs-digital-files?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=SET+OF+ZODIACS&ref=sr_gallery-1-4&organic_search_click=1",
//				"https://www.etsy.com/listing/841368407/wash-your-hands-bathroom-sign-digital?ga_order=date_desc&ga_search_type=all&ga_view_type=gallery&ga_search_query=wash+your+hands+sign&ref=sr_gallery-1-1&organic_search_click=1");
////		bot.viewPage("https://www.etsy.com/listing/822848230/zodiac-sign-digital-pisces-printable?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=pisces+zodiac+sign+print&ref=sr_gallery-1-10&organic_search_click=1",
////				"https://www.etsy.com/listing/821799862/digital-zodiac-sign-aries-printable?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=aries+poster&ref=sr_gallery-2-3&organic_search_click=1",
////				"https://www.etsy.com/listing/836698703/digital-zodiac-sign-libra-printable?ga_order=most_relevant&ga_search_type=all&ga_view_type=gallery&ga_search_query=libra+print&ref=sr_gallery-3-29&organic_search_click=1",
////				"https://www.etsy.com/listing/822335860/zodiac-sign-digital-leo-printable?ref=shop_home_active_10&pro=1",
////				"https://www.etsy.com/listing/840837007/set-of-zodiac-signs-digital-files?ref=shop_home_active_1&pro=1",
////				"https://www.etsy.com/listing/840823719/summer-prints-set-of-posters-cocktails?ref=shop_home_active_2&pro=1",
////				"https://www.pinterest.ru/pin/836965911991191422/feedback/?invite_code=fa03daf459464f54b14369b162ae11c6&sender_id=836966049407813314");
	
	
		EtsyBot bot = new EtsyBot();
//		bot.addToCart("https://www.etsy.com/listing/825838370/summer-print-whiskey-sour-poster?ref=shop_home_active_5");
		bot.searchListingByTag("822848230", "pisces zodiac sign print");
		
//		pisces zodiac sign print
	}
}
