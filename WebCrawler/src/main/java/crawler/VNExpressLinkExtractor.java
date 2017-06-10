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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jat.persistence.entity.VNExpressRootLink;
import com.jat.service.VnExpressRootLinkService;

@Component
public class VNExpressLinkExtractor implements Extractor {
	@Autowired
	VnExpressRootLinkService vnExpressRootLink;

	private Document doc;

	@Override
	public void extract(String baseHtml, String url) {

		Set<String> set = new HashSet<String>();

		this.doc = Jsoup.parse(baseHtml);

		Elements links = getLinkAt("#menu_web");

		for (Element link : links) {

			if (link.attr("abs:href").equals("")) {
				set.add("http://vnexpress.net" + link.attr("href"));
			} else {
				if (!link.attr("abs:href").equals("http://vnexpress.net/")) {
					VnExpressTabMenuCrawlerController crawler = new VnExpressTabMenuCrawlerController();
					Set<String> listLink = crawler.crawAll(link.attr("href"));
					for (String li : listLink)
						set.add(li);
				}
			}
		}

		for (String link : set) {
			if (link.length() > 0) {
				VNExpressRootLink linkObj = vnExpressRootLink.findLink(link);
				if (linkObj == null) {
					linkObj = new VNExpressRootLink(link);
				}
				vnExpressRootLink.addLink(linkObj);
			}
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
