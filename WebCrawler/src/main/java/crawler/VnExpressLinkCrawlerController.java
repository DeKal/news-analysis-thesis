package crawler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jat.persistence.entity.VNExpressRootLink;
import com.jat.service.VnExpressRootLinkService;

@Component
public class VnExpressLinkCrawlerController extends Controller {
	@Autowired
	private VNExpressNewsLinkExtractor extractor;

	@Autowired
	VnExpressRootLinkService vnExpressRootLinkService;

	public void crawlAll() {
		Set<String> rootLink = new HashSet<String>();
		List<VNExpressRootLink> links = vnExpressRootLinkService.getLink();
		for (VNExpressRootLink link : links) {
			String sCurrentLink = link.getLink();

			int length = sCurrentLink.length();
			if (sCurrentLink.charAt(length - 1) != '/')
				rootLink.add(sCurrentLink + '/');
			else
				rootLink.add(sCurrentLink);

		}

		for (String link : rootLink) {
			for (int i = 0; i < 3; ++i) {
				this.addSeed(link + "/page/" + i + ".html");
			}
		}

		this.crawl(this.extractor);
	}

}
