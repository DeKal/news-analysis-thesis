package com.jat.service;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import crawler.FBGraphApiImpl;
import crawler.VnExpressCommentCrawlerController;

@Service("CrawlerCronJobService")
public class CrawlerCronJobService {
	public void run(){
		
		try {
			FBGraphApiImpl.getFbAccessToken();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VnExpressCommentCrawlerController crawler = new VnExpressCommentCrawlerController();
		crawler.crawlAll();
	}
}
