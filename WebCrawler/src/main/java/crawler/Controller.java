package crawler;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	protected static final int numberOfCrawlers = 7;
	private String crawlStorageFolder = "/data/crawl/root";
	
	private CrawlConfig config ;
	private CrawlController controller;
	
	public Controller(){
		this.config = new CrawlConfig();
		this.config.setCrawlStorageFolder(crawlStorageFolder);
		this.config.setPolitenessDelay(300);
		this.config.setMaxDepthOfCrawling(0);
		//this.config.setMaxPagesToFetch(0);
		this.config.setIncludeBinaryContentInCrawling(false);
		this.config.setResumableCrawling(false);
		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(this.config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		try {
			this.controller = new CrawlController(this.config, pageFetcher, robotstxtServer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addSeed(String seed){
		this.controller.addSeed(seed);
	}
	public void crawl(Extractor extractor){
		MyCrawler.setExtractor(extractor);
		controller.start(MyCrawler.class, numberOfCrawlers);
	}
	public CrawlController getController(){
		return controller;
	}
	
}
