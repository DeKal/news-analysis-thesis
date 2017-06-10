package crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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

import com.jat.persistence.entity.Press;
import com.jat.service.PressService;

@Component
public class VNExpressNewsLinkExtractor implements Extractor {
	@Autowired
	private PressService pressService;
	
	private Document doc;

	@Override
	public void extract(String baseHtml, String url) {

		Set<String> set = new HashSet<String>();

		this.doc = Jsoup.parse(baseHtml);

		Elements links = getLinkAt("#box_news_top");

		for (Element link : links) {
			set.add(link.attr("abs:href"));
		}

		links = getLinkAt(".block_mid_new");
		for (Element link : links) {
			set.add(link.attr("abs:href"));
		}

		for (String link : set) {
			if (link.length() > 0) {
				Press press = pressService.findPress(link);
				if (press == null){
					press = new Press();
					press.setLink(link);
				}
				pressService.addPress(press);
				

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
