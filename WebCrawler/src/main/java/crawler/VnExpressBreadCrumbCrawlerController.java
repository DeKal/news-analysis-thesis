package crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VnExpressBreadCrumbCrawlerController extends Controller {
	@Autowired
	VNExpressLinkExtractor extractor;
	
	public void crawlAll() {
		this.addSeed("http://vnexpress.net/");
		this.crawl(extractor);
	}
}
