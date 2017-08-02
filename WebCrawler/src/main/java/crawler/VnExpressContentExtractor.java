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

import com.jat.persistence.entity.Comment;
import com.jat.persistence.entity.Press;
import com.jat.service.PressService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import jat.algo.api.AlgoAnalyzeAPI;

@Component
public class VnExpressContentExtractor implements Extractor {
	@Autowired
	PressService pressService;
	@Autowired
	AlgoAnalyzeAPI algoAPI;

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
		} catch (Exception e) {
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

	private void extractComment() throws Exception {
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
			List<Comment> comments = new ArrayList<Comment>();
			for (int i = 0; i < cmtArray.length(); i++) {
				JSONObject cmt = cmtArray.getJSONObject(i);
				String content = cmt.getString("content");
				int senti = algoAPI.getCommentSentiSVM(content);
				Comment cmtContext = new Comment(content, senti);
				comments.add(cmtContext);

			}
			this.press.setComment(comments);
		}

	}

	private void extractContent() {
		this.press.setPublisher("VnExpress");
		Element keyWords = this.doc.select("meta[name=keywords]").first();
		if (keyWords != null) {
			this.press.setKeyWords(keyWords.attr("content"));
		}
		Element htmlContent = this.doc.select(".block_col_480").first();
		if (htmlContent != null && htmlContent.text() != null) {
			String content = htmlContent.select(".fck_detail").text();
			String shortIntro = htmlContent.select(".short_intro").text();
			String title = htmlContent.select(".title_news h1").text();
			String time = htmlContent.select(".block_timer").first().text();

			this.press.setContent(content);
			this.press.setTime(time);
			this.press.setShortIntro(shortIntro);
			this.press.setTitle(title);
			

		}

	}

}
