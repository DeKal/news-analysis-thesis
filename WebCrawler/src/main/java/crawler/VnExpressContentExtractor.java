package crawler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jat.persistence.entity.Press;
import com.jat.service.PressService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Component
public class VnExpressContentExtractor implements Extractor {
	@Autowired
	PressService pressService;

	private Document doc;
	private String url;
	private Press press;

	@Override
	public void extract(String baseHtml, String url) {

		this.doc = Jsoup.parse(baseHtml);
		this.url = url;
		this.press = pressService.findPress(url);
		try {
			extractComment();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// extractFBComment();
		extractContent();
		
		pressService.addPress(press);
	}

	private void extractFBComment() {
		FBGraphApiGetComments fbApi;
		try {
			fbApi = new FBGraphApiGetComments(url);
			fbApi.getComment();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void extractComment() throws UnirestException {
		Element articleId = this.doc.select("meta[name=tt_article_id]").first();
		Element categoryId = this.doc.select("meta[name=tt_category_id]").first();
		Element siteId = this.doc.select("meta[name=tt_site_id]").first();
		
		HttpResponse<JsonNode> jsonNode = Unirest.get("http://usi.saas.vnexpress.net/index/get")
				.header("accept", "application/json").queryString("offset", "0")
				.queryString("objectid", articleId.attr("content")).queryString("objecttype", "1")
				.queryString("siteid", siteId.attr("content")).queryString("categoryid", categoryId.attr("content"))
				.queryString("template_type", "1").asJson();
		JsonNode body = jsonNode.getBody();

		if (body != null && body.toString().length() > 0) {

			JSONArray cmtArray = body.getObject().getJSONObject("data").getJSONArray("items");
			List<String> comments = new ArrayList<String>();
			for (int i = 0; i < cmtArray.length(); i++) {
				JSONObject cmt = cmtArray.getJSONObject(i);
				comments.add(cmt.getString("content"));
				
			}
			this.press.setComment(comments);
		}

	}

	private void extractContent() {
		Element htmlContent = this.doc.select(".block_col_480").first();
		if (htmlContent != null && htmlContent.text() != null) {
			this.press.setContent(htmlContent.text());
		}

	}

}
