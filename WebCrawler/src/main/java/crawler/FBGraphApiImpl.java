package crawler;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.json.JSONArray;

public abstract class FBGraphApiImpl implements FBGraphApi{
	private static final String APP_ID="173605539837323";
	private static final String APP_SECRET = "b06a672b5062f969c6cd2849ec1d4196";
	private static String accessToken;
	
	public FBGraphApiImpl() throws UnirestException{
	}
	
	public static void getFbAccessToken() throws UnirestException{
		HttpResponse<JsonNode> jsonNode = Unirest.get("https://graph.facebook.com/oauth/access_token")
				.header("accept", "application/json")
				.queryString("client_id", APP_ID)
				.queryString("client_secret", APP_SECRET)
				.queryString("grant_type","client_credentials")
				.asJson();
		JSONObject myObj = jsonNode.getBody().getObject();

		// extract fields from the object
		accessToken = myObj.getString("access_token");
	}
	
	public String getAccessToken(){
		return accessToken;
	}
	
}
