package crawler;

import java.util.List;
import java.util.Set;

public class VnExpressTabMenuCrawlerController extends Controller {
	VnExpressTabMenuExtractor extractor = new  VnExpressTabMenuExtractor();
	public Set<String> crawAll(String url) {
		this.addSeed(url);
		this.crawl(extractor);
		return (Set<String>) extractor.getQuickResult();
	}

}
