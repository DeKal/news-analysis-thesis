package crawler;

public class VnExpressBreadCrumbCrawlerController extends Controller {
	Extractor extractor = new VNExpressLinkExtractor();
	
	public void crawlAll() {
		this.addSeed("http://vnexpress.net/");
		this.crawl(extractor);
	}
}
