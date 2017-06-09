package crawler;

import java.util.Set;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

public class MainTest {

	public static void main(String[] args) throws UnirestException {
		// TODO Auto-generated method stub
//		VnExpressBreadCrumbCrawlerController crawler = new VnExpressBreadCrumbCrawlerController();
//		crawler.crawlAll();
//		VnExpressLinkCrawlerController crawler_link = new VnExpressLinkCrawlerController();
//		crawler_link.crawlAll();
		
		FBGraphApiImpl.getFbAccessToken();
		VnExpressCommentCrawlerController crawler = new VnExpressCommentCrawlerController();
		crawler.crawlAll();
		
	}

}
