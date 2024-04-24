package login.service;

import java.util.HashMap;

import com.google.gson.JsonObject;

public interface KakaoService {

	public String getState();

	public String getURL(String state);

	public JsonObject getToken(HashMap<String, Object> map);

	public HashMap<String, Object> getUserInfo(JsonObject token);

}
