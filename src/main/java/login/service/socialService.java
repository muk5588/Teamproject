package login.service;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public interface socialService {


	/**
	 * 토큰 가져오기
	 * 
	 * @param code
	 * @return 
	 */
	public String getAccessToken(String code);


	/**
	 * access
	 * 
	 * @param access_Token
	 * @return
	 */
	public HashMap<String, Object> getUserInfo(String access_Token);

//	네이버
	
	/**
	 * 네이버 상태 토큰으로 사용할 랜덤 문자열 생성
	 * 
	 * @return
	 */
	public String getState(String code);

	/**
	 * 
	 * 
	 * @param token
	 * @return
	 */
	public HashMap<String, Object> getNaverInfo(JsonObject token);

	/**
	 * 
	 * 
	 * @return
	 */
	public Map<String, Object> getStateApiUrl();

	/**
	 * 
	 * @param code
	 * @param state
	 * @return
	 */
	public String getApiURL(String code, String state);

	public JsonObject getTokenNaver(String apiURL);




	
	
	
	
}
