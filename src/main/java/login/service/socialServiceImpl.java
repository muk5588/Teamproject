package login.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import login.controller.LoginController;
import login.dao.KakaoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.UserDTO;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class socialServiceImpl implements socialService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
	private static final String REDIRECT_URI = "https://localhost:8088/oauth";
	private static final String GRANT_TYPE = "authorization_code";
	private static final String CLIENT_ID = "{Gh5AaURq6puHXY5gNHhxMZoI119sIk5A}";
	@Autowired
	KakaoDao kakaoDao;
	
	@Override
	public String getAccessToken(String code) {

	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
//	          POST 요청을 위해 기본값이 false인 setDoOutput을 true로	            
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);

	            
	            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=51dc08395da36cf607b66233b4371516");
	            sb.append("&redirect_uri=http://localhost:8088/login/kakaoLogin");
	            sb.append("&code=" + code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();

//	          	요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            logger.info("response body : " + result);
	            
//	          	Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
//	            JsonParser parser = new JsonParser();
//	            JsonElement element = parser.parse(result);
	            JsonElement element = JsonParser.parseString(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	            
	            logger.info("access_token : " + access_Token);
	            logger.info("refresh_token : " + refresh_Token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	        
	        return access_Token;
	    }
	

	
	   public HashMap<String, Object> getUserInfo (String access_Token) {
		    
//		    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		   HashMap<String, Object> userInfo = new HashMap<>();
		    String reqURL = "https://kapi.kakao.com/v2/user/me";
		    try {
		        URL url = new URL(reqURL);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("POST");
		        
//		      	요청에 필요한 Header에 포함될 내용
		        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
		        
		        int responseCode = conn.getResponseCode();
		        logger.info("responseCode : " + responseCode);

		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		        String line = "";
		        String result = "";
		        
		        while ((line = br.readLine()) != null) {
		            result += line;
		        }
		        logger.info("response body : " + result);
		        
//	            JsonElement element = JsonParser.parseString(result);
//		        
//		        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//			    JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//		        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//		        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//		        String email = kakao_account.getAsJsonObject().get("email").getAsString();
		        
		        Gson gson = new Gson();
	            
		        JsonElement je = gson.fromJson(result, JsonElement.class);
		        logger.info("je :{}", je);
		        
		        String email = null;
		        		
		        try {
				
		        	email =	je.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();

		        } catch (NullPointerException e) {

					email =  "none";
				}
		        	
		        
		        

		        if(email == null) {
		        	
		        	email = "none";
		        
		        }
		        	
		        
		        String id = je.getAsJsonObject().get("id").getAsString();


		        userInfo.put("email", email);
		        userInfo.put("id", id);
		        
			      
		        
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		    return userInfo;
	   }
	   
	   

	   
//	   -------------------네이버
	   
		@Override
		public JsonObject getTokenNaver(String apiURL) {

			try {
				URL url = new URL(apiURL);
				
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				
				con.setRequestMethod("GET");
				
				int responseCode = con.getResponseCode();
				
				BufferedReader br;
				
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {  // 에러 발생
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}

				String inputLine;
				String res = "";;

				while ((inputLine = br.readLine()) != null) {
					res += inputLine;
				}

				br.close();

				if (responseCode == 200) {
					logger.debug("res : {}", res.toString());

					Gson gson = new Gson();

					JsonObject jsonObj = gson.fromJson(res, JsonObject.class);
					logger.debug("jsonObj : {}", jsonObj);

					return jsonObj;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	   
	   
	   @Override
	public String getState(String code) {
				
		// 네이버 로그인
		// CSRF 방지를 위한 상태 토큰 생성 코드
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
		
    }
		
		
	@Override
	public Map<String, Object> getStateApiUrl() {

			Map<String, Object> map = new HashMap<>();
			
			String callbackURL = "http://localhost:8888/member/login/navercallback";
		    String clientId = "2pZURGxHKiPmm6VSaqll";//애플리케이션 클라이언트 아이디값";
		    
		    String redirectURI = null;
			try {
				redirectURI = URLEncoder.encode(callbackURL, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		    
			SecureRandom random = new SecureRandom();
		    String state = new BigInteger(130, random).toString(32);
		    String apiURL = "";
		    apiURL += "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	        apiURL += "&client_id=" + clientId;
	        apiURL += "&redirect_uri=" + redirectURI;
	        apiURL += "&state=" + state;
			
	        map.put("state", state);
	        map.put("apiURL", apiURL);
			
	        logger.debug("{}", map.put("apiURL", apiURL));
	        
			return map;
		}
		
	   @Override
	public HashMap<String, Object> getNaverInfo(JsonObject token) {
		   
			
//		    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		   HashMap<String, Object> naverInfo = new HashMap<>();

			  String host = "https://openapi.naver.com/v1/nid/me";
			  UserDTO naver = new UserDTO();
	          
	          try {
	        	  
	              URL url = new URL(host);

	              HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	              
	              urlConnection.setRequestProperty("Authorization", "Bearer " + token.get("access_token"));
	              urlConnection.setRequestMethod("GET");

	              int responseCode = urlConnection.getResponseCode();
	              logger.debug("responseCode : {} ", responseCode);

	              BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	              logger.debug("br? {} :", br);
	              
	              String line = "";
	              String res = "";
	              
	              while((line=br.readLine())!=null) {
	                  res+=line;
	              }

	              logger.debug("res : {}", res);

	              Gson gson = new Gson();
	              
	              JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
	              logger.debug("jsonObject : {}", jsonObject);
	              
	              JsonObject response = jsonObject.getAsJsonObject("response");
	              logger.debug("response : {}", response);

	              String email = response.get("email").getAsString().trim();
	              String id = response.get("id").getAsString().trim();
	              String name = response.get("name").getAsString().trim();
			        
	              naverInfo.put("id", id);
	              br.close();

	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	          
	          logger.debug("naverInfo : {}", naverInfo);

	          return naverInfo;
			
		}

	@Override
	public String getApiURL(String code, String state) {
		String callbackURL = "http://localhost:8888/member/login/navercallback";
		String clientId = "2pZURGxHKiPmm6VSaqll";	//애플리케이션 클라이언트 아이디값";
		String clientSecret = "_EBtrHfzWM";		//애플리케이션 클라이언트 시크릿값";
		String redirectURI = null;

		try {
			redirectURI = URLEncoder.encode(callbackURL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = "";
		apiURL += "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		
		return apiURL;
	}








	   
	
	
	   
}
