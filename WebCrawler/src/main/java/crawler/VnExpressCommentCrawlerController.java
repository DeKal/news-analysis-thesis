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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jat.persistence.entity.Press;
import com.jat.service.PressService;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class VnExpressCommentCrawlerController extends Controller {

	@Autowired
	VnExpressContentExtractor extractor;

	@Autowired
	PressService pressService;

	public void crawlAll() {
		List<Press> lPress = pressService.listNoContentPress();
		for (Press press : lPress) {
			this.addSeed(press.getLink());
		}		
		this.crawl(extractor);
	}
	
}
