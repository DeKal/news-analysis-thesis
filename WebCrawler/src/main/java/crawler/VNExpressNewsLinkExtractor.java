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

public class VNExpressNewsLinkExtractor implements Extractor {
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
			
		
		try {
			File file = new File("log_link.txt");

	    	/* This logic is to create the file if the
	    	 * file is not already present
	    	 */
	    	if(!file.exists()){
	    	   file.createNewFile();
	    	}
	    	FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter writer = new PrintWriter(bw);
			for (String link : set) {
				if (link.length() > 0)
					writer.println(link);
			}
			writer.close();
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
