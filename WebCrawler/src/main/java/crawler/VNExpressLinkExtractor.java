package crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VNExpressLinkExtractor implements Extractor {
	private Document doc;
	
	@Override
	public void extract(String baseHtml, String url) {

		Set<String> set = new HashSet<String>();

		

		try {
			File file = new File("log_link_breadcrumb.txt");

			/*
			 * This logic is to create the file if the file is not already
			 * present
			 */
			if (!file.exists()) {
				this.doc = Jsoup.parse(baseHtml);

				Elements links = getLinkAt("#menu_web");

				for (Element link : links) {
					
					if (link.attr("abs:href").equals("")){
						set.add("http://vnexpress.net"+link.attr("href"));
					}
					else{
						if (!link.attr("abs:href").equals("http://vnexpress.net/")){
							VnExpressTabMenuCrawlerController crawler = new VnExpressTabMenuCrawlerController();
							Set<String> listLink = crawler.crawAll(link.attr("href"));
							for (String li : listLink)
								set.add(li);
						}
					}
				}
				
				file.createNewFile();
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter writer = new PrintWriter(bw);
				for (String link : set) {
					if (link.length() > 0)
						writer.println(link);
				}
//				Set<String> href_set = new HashSet<String>();
//				for (Element link : links) {
//					if (!set.contains(link.attr("href")))
//						if (!link.attr("abs:href").equals("http://vnexpress.net/"))
//							href_set.add("http://vnexpress.net"+link.attr("href"));
//				}
//				for (String link : href_set) {
//					if (link.length() > 0)
//						writer.println(link);
//				}
				writer.close();
			}
			
		} catch (IOException e) {
			// gg
		}

	}

	private Elements getLinkAt(String element) {
		Element boxNews = doc.select(element).first();
		Elements links = null;
		if (boxNews != null)
			links = boxNews.select("a[href]");

		if (links == null)
			links = new Elements();
		return links;
	}

}
