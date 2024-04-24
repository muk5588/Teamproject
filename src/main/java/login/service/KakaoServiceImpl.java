package login.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;

@Service
public class KakaoServiceImpl implements KakaoService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final static String KAKAO_URL = "https://kauth.kakao.com/oauth/authorize";
	private final static String REST_API_KEY = "51dc08395da36cf607b66233b4371516";
	private final static String REDIRECT_URL = "http://localhost:8088/login/kakaoLogin";
	private final static String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
	private final static String GET_INFO_URL = "https://kapi.kakao.com/v2/user/me";
	
	
	@Override
	public String getURL(String state) {
		String url = "";
		url += KAKAO_URL;
		url += "?client_id=" + REST_API_KEY;
		url += "&redirect_uri=" + REDIRECT_URL;
		url += "&state=" + state;
		url += "&response_type=code";
		return url;
	}//getURL


	@Override
	public JsonObject getToken(HashMap<String, Object> map) {
		String apiUrl ="";
		apiUrl += TOKEN_URL;
		
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			//POST 요청을 수행하려면 serDoOutput()을 true로 설정해줘야함.
			con.setDoOutput(true);
			
			String code = (String) map.get("code");
			
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		    String sb = "grant_type=authorization_code" +
				"&client_id=" + REST_API_KEY + // REST_API_KEY
				"&redirect_uri="+ REDIRECT_URL + // REDIRECT_URI
				"&code=" + code;
			bufferedWriter.write(sb);
			bufferedWriter.flush();
			
			int responseCode = con.getResponseCode();
			
			BufferedReader bufferedReader;
			if( responseCode == 200) {	//정상응답
				bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				logger.info("responseCode : {}", responseCode);
			} else {	//에러 발생
				bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				logger.info("responseCode : {}", responseCode);
			}
			
			String input;
			String res = "";
			while ((input = bufferedReader.readLine()) != null) {
				res += input;
			}
			
			bufferedReader.close();
			if (responseCode == 200) {
				logger.info("res : {}", res.toString());
				
				Gson gson = new Gson();
				
				JsonObject jsonObj = gson.fromJson(res, JsonObject.class);
				logger.info("jsonObj : {}", jsonObj);
				
				return jsonObj;
			}
			
		} catch (IOException e) {
			logger.debug("getToken - Exception : connection");
			e.printStackTrace();
		}
		
		return null;
	}//getToken(map)


	@Override
	public String getState() {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		return state;
	}//getState


	@Override
	public HashMap<String, Object> getUserInfo(JsonObject token) {
		HashMap<String, Object> info = new HashMap<>();
		
		try {
			URL url = new URL(GET_INFO_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer " + token.get("access_token"));
			
			int responseCode = con.getResponseCode();

			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			logger.info("###################br : {}", bufferReader);
			logger.info("responseCode : {}", responseCode);

			String line = "";
			String res = "";

			while ((line = bufferReader.readLine()) != null) {
				res += line;
			}

			Gson gson = new Gson();

			JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
			logger.info("jsonObject : {}", jsonObject);

			//현재 에러 위치
			logger.info("1------------------------------------------------------");
			
			String id = jsonObject.get("id").getAsString().trim();
			logger.debug("id : {} ",id);
			JsonObject properties = (JsonObject)jsonObject.get("properties");
			logger.info("properties : {} ",properties);
			
			logger.info("2------------------------------------------------------");
			JsonObject kakao_account = (JsonObject)jsonObject.get("kakao_account");
			logger.info("kakao_account : {} ",kakao_account);
			String email = kakao_account.get("email").getAsString().trim();
			logger.info("email : {} ",email);
			String name = kakao_account.get("name").getAsString().trim();
			logger.info("name : {} ",name);
			String phone_number = kakao_account.get("phone_number").getAsString().trim();
			logger.info("phone_number : {} ",phone_number);
			
			info.put("id", id);
			info.put("email", email);
			info.put("name", name);
			info.put("phone_number", phone_number);
			
			bufferReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


		return info;
	}//getUserInfo(Json token)

	@Override
	public boolean kakaoLogout(HttpSession session) {
		String ACCESS_TOKEN = (String) session.getAttribute("token1");
		if(ACCESS_TOKEN == null || "".equals(ACCESS_TOKEN)){
			return false;
		}
		
		String apiUrl = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
			con.setRequestMethod("POST");
			//POST 요청을 수행하려면 serDoOutput()을 true로 설정해줘야함.
			con.setDoOutput(true);
			con.setRequestProperty("Authorization", " Bearer "+ACCESS_TOKEN);
		    int responseCode = con.getResponseCode();
			
			BufferedReader bufferedReader;
			if( responseCode == 200) {	//정상응답
				bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				logger.info("responseCode : {}", responseCode);
			} else {	//에러 발생
				bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				logger.info("responseCode : {}", responseCode);
			}
			
			String input;
			String res = "";
			while ((input = bufferedReader.readLine()) != null) {
				res += input;
			}
			
			bufferedReader.close();
			if (responseCode == 200) {
				logger.info("res : {}", res.toString());
				
				Gson gson = new Gson();
				
				JsonObject jsonObj = gson.fromJson(res, JsonObject.class);
				logger.info("jsonObj : {}", jsonObj);
				String id = jsonObj.get("id").getAsString().trim();
				String sessionId = (String) session.getAttribute("socid");
				if( id == sessionId ) {
					return true;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}//kakaoLogout(session)





	
	
	
}
