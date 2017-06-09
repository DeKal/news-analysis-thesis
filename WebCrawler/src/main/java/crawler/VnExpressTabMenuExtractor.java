package crawler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class VnExpressTabMenuExtractor implements Extractor {
	Set<String> links = new HashSet<String>();
	private Document doc;
	@Override
	public void extract(String baseHtml, String url) {
		// TODO Auto-generated method stub
		this.doc = Jsoup.parse(baseHtml);
		Elements links = getLinkAt("#breakumb_web");
		for (Element link : links) {
			this.links.add(link.attr("abs:href"));
		}

	}
	public Set<String> getQuickResult(){
		return links;	
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
