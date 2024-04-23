package login.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

public class NaverLoginVO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final static String CLIENT_ID = "vwSp_7gB7SNQ8Di_skGL";
    private final static String REDIRECT_URI = "http://localhost:8088/login/naver/callback";
    private final static String CLIENT_SECRET = "mFSPW6AxlM";
    private String code;
    private String state;
	private String redirectURI;
	
	@Autowired private HttpServletRequest request;
	@Autowired private HttpServletResponse response;
	@Autowired private HttpSession session;
	
	public String getApiURL(String state) {
	    String clientId = CLIENT_ID;//애플리케이션 클라이언트 아이디값";
	    String redirectURI = null;
		try {
			redirectURI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("리다이렉트 URL 에러. URL인코더.인코드('콜백 URL',인코딩타입 )");
			e.printStackTrace();
		}
	    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    
	    return apiURL;
	}

	public String getstate() {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		return state;
	}
	
	
	
//    private final static String CLIENT_ID = "fKMsVvHjT20ofhw2UDgN";	//애플리케이션 클라이언트 아이디값
//    private final static String CLIENT_SECRET = "KxF0mch_Gf";
//    private final static String REDIRECT_URI = "http://localhost:8088/login/navercallback";
//    private final static String SESSION_STATE = "oauth_state";
//    /* 프로필 조회 API URL */
//    private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
//
//    /* 네이버 아이디로 인증  URL 생성  Method */
//    public String getAuthorizationUrl(HttpSession session) {
//
//        /* 세션 유효성 검증을 위하여 난수를 생성 */
//        String state = generateRandomString();
//        /* 생성한 난수 값을 session에 저장 */
//        setSession(session,state);
//
//        /* Scribe에서 제공하는 인증 URL 생성 기능을 이용하여 네아로 인증 URL 생성 */
//        OAuth20Service oauthService = new ServiceBuilder()
//                .apiKey(CLIENT_ID)
//                .apiSecret(CLIENT_SECRET)
//                .callback(REDIRECT_URI)
//                .state(state) //앞서 생성한 난수값을 인증 URL생성시 사용함
//                .build(NaverLoginApi.instance());
//
//        return oauthService.getAuthorizationUrl();
//    }
//
//    /* 네이버아이디로 Callback 처리 및  AccessToken 획득 Method */
//    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException{
//
//        /* Callback으로 전달받은 세선검증용 난수값과 세션에 저장되어있는 값이 일치하는지 확인 */
//        String sessionState = getSession(session);
//        if(StringUtils.pathEquals(sessionState, state)){
//
//            OAuth20Service oauthService = new ServiceBuilder()
//                    .apiKey(CLIENT_ID)
//                    .apiSecret(CLIENT_SECRET)
//                    .callback(REDIRECT_URI)
//                    .state(state)
//                    .build(NaverLoginApi.instance());
//
//            /* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
//            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
//            return accessToken;
//        }
//        return null;
//    }
//
//    /* 세션 유효성 검증을 위한 난수 생성기 */
//    private String generateRandomString() {
//        return UUID.randomUUID().toString();
//    }
//
//    /* http session에 데이터 저장 */
//    private void setSession(HttpSession session, String state){
//        session.setAttribute(SESSION_STATE, state);
//    }
//
//    /* http session에서 데이터 가져오기 */
//    private String getSession(HttpSession session){
//        return (String) session.getAttribute(SESSION_STATE);
//    }
//    /* Access Token을 이용하여 네이버 사용자 프로필 API를 호출 */
//    public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
//
//        OAuth20Service oauthService =new ServiceBuilder()
//                .apiKey(CLIENT_ID)
//                .apiSecret(CLIENT_SECRET)
//                .callback(REDIRECT_URI).build(NaverLoginApi.instance());
//
//        OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
//        oauthService.signRequest(oauthToken, request);
//        Response response = request.send();
//        return response.getBody();
//    }
	
	
	
	
	
}
