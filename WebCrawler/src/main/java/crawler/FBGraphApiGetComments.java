package crawler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FBGraphApiGetComments extends FBGraphApiImpl
{
	private String url;
	private String id;
	public FBGraphApiGetComments(String url) throws UnirestException {
		super();
		this.setUrl(url);
		
		FBGraphApiGetUrl fbUrl = new FBGraphApiGetUrl(url);
		this.setId(fbUrl.getUrlFBId());
		
	}

	@Override
	public JSONObject request() throws UnirestException {
		
		HttpResponse<JsonNode> jsonNode = Unirest.get("https://graph.facebook.com/{id}/comments")
				.header("accept", "application/json")
				.queryString("access_token", getAccessToken())
				.routeParam("id", this.getId())
				.asJson();
		
		JSONObject myObj = jsonNode.getBody().getObject();
		return myObj;
	}
	public void getComment() throws UnirestException{
		JSONObject cmtObj = this.request();
		JSONArray cmtArray = cmtObj.getJSONArray("data");
		
		try {

			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream("log_comment_fb/" + this.getId()  + ".txt", true), "UTF-8");
			BufferedWriter fbw = new BufferedWriter(writer);
			
			fbw.write(this.url);
			fbw.newLine();
			fbw.newLine();
			for (int i = 0; i < cmtArray.length(); i++) {
				JSONObject cmt = cmtArray.getJSONObject(i);
				fbw.write(cmt.getString("message"));
				fbw.newLine();
				fbw.newLine();
			}
			
			fbw.close();
		} catch (IOException e) {
			// gg
		}
		
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
