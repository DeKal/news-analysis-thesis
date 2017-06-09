package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VnExpressLinkCrawlerController extends Controller {
	private Extractor extractor = new VNExpressNewsLinkExtractor();
	
	public void crawlAll() {
		Set<String> rootLink = new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader("log_link_breadcrumb.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				int length = sCurrentLine.length();
				if (sCurrentLine.charAt(length-1) != '/')
					rootLink.add(sCurrentLine + '/');
				else 
					rootLink.add(sCurrentLine);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String link : rootLink){
			for (int i=0; i <3; ++i){
				this.addSeed(link + "/page/"+ i +".html");
			}
		}
		
		this.crawl(this.extractor);
	}

}
