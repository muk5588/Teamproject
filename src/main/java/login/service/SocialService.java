package login.service;

import com.google.gson.JsonObject;
import user.dto.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface SocialService {

	/**
	 * state 를 반환해주는 메소드
	 * BigInteger(130,SecureRandom).toString() 을 이용해 생성한다
	 * @return SecureRandom으로 생성된 값을 담은 String 
	 */
	public String getstate();

	/**
	 *  입력된 state, client_id, redirect_uri 을 포함한 (String) apiURL 을 반환한다
	 * @param state - 랜덤 String
	 * @return apiURL
	 */
	public String getApiURL(String state);

	/**
	 * code 와 state 를 포함한 (String) apiURL 을 반환한다
	 * @param code
	 * @param state
	 * @return
	 */
	public String getApiURL(String code, String state);

	/**
	 * 네이버에서 토큰을 얻어온다.
	 * @param apiURL
	 * @return
	 */
	public JsonObject getToken(String apiURL);

	/**
	 * 접근 토큰을 이용하여 프로필 API 호출하기
	 * @param token - 네이버에서 얻은 토큰값
	 * @return  유저 정보
	 */
	public HashMap<String, Object> getUserInfo(JsonObject token);

	public String getSosid(HashMap<String, Object> info);
	
	public void socialJoin(User dto);


	public User socialLogin(String socid);
	/**
	 *  입력된 state, client_id, redirect_uri 을 포함한 (String) apiURL 을 반환한다
	 * @param state - 랜덤 String
	 * @return apiURL
	 */
	public String googleURL(String state);

	public String getGoogleURL(String code, String state);

	public JsonObject getGoogleToken(String apiURL);

	public String getKakaoid(HashMap<String, Object> userInfo);

	public boolean naverLogout(HttpSession session);

	/**
	 * 유저 접속로그 추가
	 * @param dto - 소셜 유저 정보를 담은 DTO 객체
	 */
	public void insertAccessHistory(User dto);


}
