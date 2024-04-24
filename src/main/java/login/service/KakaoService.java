package login.service;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface KakaoService {

	public String getState();

	public String getURL(String state);

	public JsonObject getToken(HashMap<String, Object> map);

	public HashMap<String, Object> getUserInfo(JsonObject token);
	
	public boolean kakaoLogout(HttpSession session);
}
