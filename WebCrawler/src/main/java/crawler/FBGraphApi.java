package crawler;

import org.json.JSONObject;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface FBGraphApi {
	public JSONObject request() throws UnirestException;
}
