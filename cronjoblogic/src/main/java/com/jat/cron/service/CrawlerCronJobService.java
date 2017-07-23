package com.jat.cron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.exceptions.UnirestException;

import crawler.FBGraphApiImpl;
import crawler.VnExpressBreadCrumbCrawlerController;
import crawler.VnExpressCommentCrawlerController;
import crawler.VnExpressLinkCrawlerController;

@Service("CrawlerCronJobService")
public class CrawlerCronJobService {
	@Autowired
	VnExpressBreadCrumbCrawlerController bcCrawler;
	
	@Autowired
	VnExpressLinkCrawlerController crawlerLink;
	
	@Autowired
	VnExpressCommentCrawlerController crawlerContent;
	public void run(){
		try {
			FBGraphApiImpl.getFbAccessToken();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bcCrawler.crawlAll();
		crawlerLink.crawlAll();
		crawlerContent.crawlAll();
		System.out.println("Done Crawling");
	}
}
