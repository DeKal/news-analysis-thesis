package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mashape.unirest.http.exceptions.UnirestException;



public class VnExpressCommentCrawlerController extends Controller {
	

	Extractor extractor = new VnExpressContentExtractor();

	public void crawlAll() {
		try (BufferedReader br = new BufferedReader(new FileReader("log_link.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				this.addSeed(sCurrentLine);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.crawl(extractor);
	}

}
