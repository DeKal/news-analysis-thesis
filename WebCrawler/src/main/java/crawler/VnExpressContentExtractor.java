package crawler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class VnExpressContentExtractor implements Extractor {
	
	private Document doc;
	private String url;
	@Override
	public void extract(String baseHtml, String url) {

		this.doc = Jsoup.parse(baseHtml);
		this.url = url;
		extractComment();
		extractFBComment();
		
		//extractContent();
		
	}
	private void extractFBComment(){
		FBGraphApiGetComments fbApi;
		try {
			fbApi = new FBGraphApiGetComments(url);
			fbApi.getComment();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void extractComment(){
		Element articleId = this.doc.select("meta[name=tt_article_id]").first();
		Element categoryId = this.doc.select("meta[name=tt_category_id]").first();
		Element siteId = this.doc.select("meta[name=tt_site_id]").first();
		String aaa= articleId.attr("content");
		Future<HttpResponse<JsonNode>> future = Unirest.get("http://usi.saas.vnexpress.net/index/get")
				.header("accept", "application/json")
				.queryString("offset", "0")
				.queryString("objectid", articleId.attr("content"))
				.queryString("objecttype","1")
				.queryString("siteid", siteId.attr("content"))
				.queryString("categoryid", categoryId.attr("content"))
				.queryString("template_type","1")
				.asJsonAsync(new VNExpCmtCallBackAPI(articleId.attr("content"),this.url));
	}
	private void extractContent() {
		Element htmlContent = this.doc.select(".block_col_480").first();

		try {

			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("log_content.txt", true), "UTF-8");
			BufferedWriter fbw = new BufferedWriter(writer);

			if (htmlContent != null && htmlContent.text() != null) {
				fbw.write(htmlContent.text());
				fbw.newLine();
			}
			fbw.close();
		} catch (IOException e) {
			// gg
		}
	}

}
