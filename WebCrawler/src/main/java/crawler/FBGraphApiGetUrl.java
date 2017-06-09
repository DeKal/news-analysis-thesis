package crawler;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FBGraphApiGetUrl  extends FBGraphApiImpl{
	private String url;
	public FBGraphApiGetUrl(String url) throws UnirestException {
		super();
		this.setUrl(url);
	}

	@Override
	public JSONObject request() throws UnirestException {
		// TODO Auto-generated method stub
		HttpResponse<JsonNode> jsonNode = Unirest.get("https://graph.facebook.com")
				.header("accept", "application/json")
				.queryString("id", this.url)
				.asJson();
		JSONObject urlData = jsonNode.getBody().getObject();
		JSONObject urlInfo = urlData.getJSONObject("og_object");
		
		return urlInfo;
	}
	public String getUrlFBId() throws UnirestException{
		JSONObject urlInfo = request();
		return urlInfo.getString("id");
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
