package crawler;

import java.io.IOException;
import java.util.Set;

import com.mashape.unirest.http.exceptions.UnirestException;

import jat.algo.api.AlgoAnalyzeAPI;

import org.json.JSONObject;

public class MainTest {

	public static void main(String[] args) throws UnirestException {
		// TODO Auto-generated method stub
//		VnExpressBreadCrumbCrawlerController crawler = new VnExpressBreadCrumbCrawlerController();
//		crawler.crawlAll();
//		VnExpressLinkCrawlerController crawler_link = new VnExpressLinkCrawlerController();
//		crawler_link.crawlAll();
		
		/*
		FBGraphApiImpl.getFbAccessToken();
		VnExpressCommentCrawlerController crawler = new VnExpressCommentCrawlerController();
		crawler.crawlAll();
		*/
		
		AlgoAnalyzeAPI algoAnalyzeAPI = new AlgoAnalyzeAPI();
		
		try {
			int svm = algoAnalyzeAPI.getCommentSentiSVM("test");
			System.out.println("svm: " + svm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			int wordNet = algoAnalyzeAPI.getCommentSentiVNWord("test");
			System.out.println("wordnet: " + wordNet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
