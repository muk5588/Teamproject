package login.controller;

import com.github.scribejava.core.model.OAuth2AccessToken;
import login.dto.NaverLoginVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class SocialController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private NaverLoginVO naverLoginVO;
    private String apiResult = null;
    @Autowired
    private void setnaverLoginVO(NaverLoginVO naverLoginVO) {
        this.naverLoginVO = naverLoginVO;
    }
//    @RequestMapping(value = "/naverLogin", method = { RequestMethod.GET, RequestMethod.POST })
//    public String login(Model model, HttpSession session) {
//
//        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginVO클래스의 getAuthorizationUrl메소드 호출 */
//        String naverAuthUrl = naverLoginVO.getAuthorizationUrl(session);
//
//        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
//        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
//        System.out.println("네이버:" + naverAuthUrl);
//
//        //네이버 
//        model.addAttribute("url", naverAuthUrl);
//
//        /* 생성한 인증 URL을 View로 전달 */
//        return "login/naverlogin";
//    }
//
//    //네이버 로그인 성공시 callback호출 메소드
//    @RequestMapping(value = "/login/navercallback", method = { RequestMethod.GET, RequestMethod.POST })
//    public String callback(Model model, @RequestParam(value = "code") String code, @RequestParam(value = "state") String state, HttpSession session)
//            throws IOException {
//        System.out.println("여기는 callback");
//        OAuth2AccessToken oauthToken;
//        oauthToken = naverLoginVO.getAccessToken(session, code, state);
//        //로그인 사용자 정보를 읽어온다.
//        apiResult = naverLoginVO.getUserProfile(oauthToken);
//        model.addAttribute("result", apiResult);
//
//        /* 네이버 로그인 성공 페이지 View 호출 */
//        return "login/naverSuccess";
//    }

	@RequestMapping("/naver/login")
	public void main(HttpSession session, Model model) {
		String state = naverLoginVO.getstate();
		
		String apiURL = naverLoginVO.getApiURL(state);
		
	    session.setAttribute("state", state);
	    logger.info("state~~~ {}",state);
	    logger.info("apiu~~~ {}",apiURL);
	    
	    model.addAttribute("apiURL", apiURL);
	}
	
	@RequestMapping("/naver/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response
			) {
		
		   String clientId = "vwSp_7gB7SNQ8Di_skGL";//애플리케이션 클라이언트 아이디값";
		    String clientSecret = "mFSPW6AxlM";//애플리케이션 클라이언트 시크릿값";
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    String redirectURI = null;
		    
			try {
				redirectURI = URLEncoder.encode("http://localhost:8088/social/naver/callback", "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				
				e1.printStackTrace();
			}
		    String apiURL;
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
		    System.out.println("apiURL="+apiURL);
		    try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      con.setDoOutput(true);
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
//		      OutputStream out = con.getOutputStream();
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      logger.info("code : {} ", code);
		      logger.info("state : {} ", state);
		      logger.info("redirectURI : {} ", redirectURI);
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
//		        logger.info("res :: {} ", res.toString());
		      }
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		    return "login/naver/success";
	}

}
