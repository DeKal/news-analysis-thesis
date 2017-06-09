package crawler;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class VNExpCmtCallBackAPI implements Callback<JsonNode> {
	public VNExpCmtCallBackAPI(String id, String url) {
		super();
		this.id = id;
		this.url = url;
	}

	private String id;
	private String url;

	public void completed(HttpResponse<JsonNode> response) {
		int code = response.getStatus();

		JsonNode body = response.getBody();

		try {

			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream("log_comment/" + this.id + ".txt", true), "UTF-8");
			BufferedWriter fbw = new BufferedWriter(writer);

			if (body != null && body.toString().length() > 0) {
				fbw.write(this.url);
				fbw.newLine();
				JSONArray cmtArray = body.getObject().getJSONObject("data").getJSONArray("items");
				for (int i = 0; i < cmtArray.length(); i++) {
					JSONObject cmt = cmtArray.getJSONObject(i);
					fbw.write(cmt.getString("content"));
					fbw.newLine();
					fbw.write("----------------");
					fbw.newLine();
				}
				fbw.newLine();
			}
			fbw.close();
		} catch (IOException e) {
			// gg
		}
	}

	public void cancelled() {
		System.out.println("The request has been cancelled");
	}

	@Override
	public void failed(UnirestException arg0) {
		// TODO Auto-generated method stub

	}
}
