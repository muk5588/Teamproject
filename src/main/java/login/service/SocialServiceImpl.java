package login.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import user.dto.UserDTO;

@Service
public class SocialServiceImpl implements SocialService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final static String CLIENT_ID = "vwSp_7gB7SNQ8Di_skGL";// 애플리케이션 클라이언트 아이디값";
	private final static String CALLBACK_URI = "http://localhost:8088/login/naver/callback";
	private final static String CLIENT_SECRET = "mFSPW6AxlM";// 애플리케이션 클라이언트 시크릿값";

	@Override
	public String getApiURL(String state) {
		String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode(CALLBACK_URI, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("리다이렉트 URL : URL인코더.인코드('콜백 URL',인코딩타입 ) 부분 Exception발생");
			e.printStackTrace();
		}
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + CLIENT_ID;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;

		return apiURL;
	}// getApiURL(state)

	@Override
	public String getstate() {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		return state;
	}// getstate()

	@Override
	public String getApiURL(String code, String state) {
		String redirectURI = null;

		try {
			redirectURI = URLEncoder.encode(CALLBACK_URI, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL = "";
		apiURL += "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
		apiURL += "&client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLIENT_SECRET;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;

		return apiURL;
	}// getApiURL(code,state)

	@Override
	public JsonObject getToken(String apiURL) {

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			int responseCode = con.getResponseCode();

			BufferedReader bufferedReader;
			if (responseCode == 200) { // 정상 호출
				bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				logger.info("responseCode : {}", responseCode);
			} else { // 에러 발생
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

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}// getToken(apiURL)

	@Override
	public HashMap<String, Object> getUserInfo(JsonObject token) {
		// https://developers.naver.com/docs/login/devguide/devguide.md#3-4-5-%EC%A0%91%EA%B7%BC-%ED%86%A0%ED%81%B0%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%ED%94%84%EB%A1%9C%ED%95%84-api-%ED%98%B8%EC%B6%9C%ED%95%98%EA%B8%B0
		// 접근 토큰을 이용하여 프로필 API 호출하기
		String reqUrl = "https://openapi.naver.com/v1/nid/me";

		//현재 hashMap 처리 DTO로 할건지 MAP으로 할건지...
		HashMap<String, Object> info = new HashMap<>();
		UserDTO userInfo = new UserDTO();

		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// 요청 헤더명 => Authorization: {토큰 타입] {접근 토큰]
//			"Authorization: Bearer AAAAPIuf0L+qfDkMABQ3IJ8heq2mlw71DojBj3oc2Z6OxMQESVSrtR0dbvsiQbPbP1/cxva23n7mQShtfK4pchdk/rc="
			con.setRequestProperty("Authorization", "Bearer " + token.get("access_token"));
			con.setRequestMethod("GET");

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

			JsonObject response = jsonObject.getAsJsonObject("response");
			logger.info("response : {}", response);

			String email = response.get("email").getAsString().trim();
			String id = response.get("id").getAsString().trim();
			String name = response.get("name").getAsString().trim();

			logger.info("email : {} ",email);
			logger.info("id : {} ",id);
			logger.info("name : {} ",name);
			
			bufferReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("info : {}", info);

		return info;
	}//getUserInfo(token)

}
