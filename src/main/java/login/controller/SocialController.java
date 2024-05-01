package login.controller;

import com.google.gson.JsonObject;
import login.service.KakaoService;
import login.service.LoginService;
import login.service.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import user.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/login")
public class SocialController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired SocialService socialService;
	@Autowired KakaoService kakaoService;
	@Autowired LoginService loginService;

	
//    @Autowired
    private String apiResult = null;
//    @Autowired
//    private void setnaverLoginVO(NaverLoginVO naverLoginVO) {
//        this.naverLoginVO = naverLoginVO;
//    }
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

    //1.네이버 로그인
	@RequestMapping("/naver/login")
	public ModelAndView main(HttpSession session, Model model) {
//	public void main(HttpSession session, Model model) {
		String state = socialService.getstate();
		
		String apiURL = socialService.getApiURL(state);
		
	    session.setAttribute("state", state);
	    logger.info("state~~~ {}",state);
	    logger.info("apiu~~~ {}",apiURL);
	    
	    model.addAttribute("apiURL", apiURL);
	    return new ModelAndView("redirect:"+apiURL);
	}
	
	@RequestMapping("/naver/callback")
	public String callback(HttpServletRequest request, HttpServletResponse response, HttpSession session
			) {
		    String code = request.getParameter("code");
		    String state = request.getParameter("state");
		    logger.info("code : {} ", request.getParameter("code"));
		    logger.info("state : {} ", request.getParameter("state"));
		    
		    String apiURL = socialService.getApiURL(code,state);
		    logger.info("apiURL : {} ", apiURL);
		    
		    JsonObject token = socialService.getToken(apiURL);
//		    logger.info("-----------------error : {} ----------------", token.get("error"));
//		    logger.info("error_description : {} ", token.get("error_description"));
//		    logger.info("expires_in : {} ", token.get("expires_in"));
//		    logger.info("access_token : {} ", token.get("access_token"));
//		    logger.info("refresh_token : {} ", token.get("refresh_token"));
//		    logger.info("-------------------token_type : {} ----------------", token.get("token_type"));
		    session.setAttribute("token",token.get("access_token").toString() );
		    HashMap<String, Object> info = socialService.getUserInfo(token);
			logger.info("info : {} ", info);
		    String socid = socialService.getSosid(info);
			if(socid!=null) {
				User dto = socialService.socialLogin(socid);
				socialService.insertAccessHistory(dto);
				int isLogin = dto.getUserno();
				session.setAttribute("isLogin", isLogin);
				session.setAttribute("dto1", dto);
				return "redirect: /";
			}else{
				session.setAttribute("sosid", info);
				return "login/naver/naverjoin";
			}
	}

	@RequestMapping("/naver/naver/logoutCallback")
	public void naverLogout(HttpSession session) {
		socialService.naverLogout(session);
	}
	@GetMapping("/naverjoin")
	public String naverJoin(Model model) {
		return "naver/naverjoin";
	}
	@PostMapping("/naverjoin")
	public String naverJoinPost(User dto) {
		String id = UUID.randomUUID().toString();
		String pw = UUID.randomUUID().toString();
		dto.setUserid(id);
		dto.setUserpw(pw);
		socialService.socialJoin(dto);
		return "redirect: ./";
	}
	
	//--------------------------------------------------------------------------------------------
	//2.카카오 로그인 처리
	@RequestMapping("/kakao/login")
	public String kakaoLogin() {
		String state = kakaoService.getState();
	    String apiURL = kakaoService.getURL(state);
		
	    return "redirect:" + apiURL;
	}
	
	@RequestMapping("/kakaoLogin")
//	@RequestMapping("/kakao/redirect")
	public String kakaoRedirect(HttpServletRequest request, HttpSession session) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		logger.info("code : {} ", request.getParameter("code"));
		logger.info("state : {} ", request.getParameter("state"));
		HashMap<String, Object> map = new HashMap<>();
		map.put("code", code);
		map.put("state", state);
		
		JsonObject token = kakaoService.getToken(map);
		logger.info("토큰값 : {}", token.toString());

		HashMap<String, Object> userInfo = kakaoService.getUserInfo(token);
		logger.info("AFTER getUserInfo - userInfo : {}", userInfo);
		session.setAttribute("token1", token.get("access_token").toString());
		String socid = socialService.getKakaoid(userInfo);
		if(socid!=null) {
			User dto = socialService.socialLogin(socid);

			socialService.insertAccessHistory(dto);
			session.setAttribute("dto1", dto);
			int isLogin = dto.getUserno();
			session.setAttribute("isLogin", isLogin);
			return "redirect: /";
		}else{
			session.setAttribute("sosid", userInfo);
			return "login/naver/naverjoin";
		}
	}//카카오 리다이렉트
//	//511805987241-tigiok5tle9vo2uttu80i7r1voe65kle.apps.googleusercontent.com
//	//RA3VsWUWETduFax9fD8jr1Cg6yAI
//	// api 키: AIzaSyDQK7F8ggaZtyQBZ-8keLi6hfVtkz3YvEg
//	@RequestMapping("/google/login")
////	public ModelAndView google(HttpSession session, Model model) {
//	public void google(HttpSession session, Model model) {
//		String state = socialService.getstate();
//
//		String apiURL = socialService.googleURL(state);
//
//		session.setAttribute("state", state);
//		logger.info("state~~~ {}",state);
//		logger.info("apiu~~~ {}",apiURL);
//
//		model.addAttribute("apiURL", apiURL);
////		return new ModelAndView("redirect:"+apiURL);
//	}
//	@RequestMapping("/google/callback")
//	public String googlecallback(HttpServletRequest request, HttpServletResponse response, HttpSession session
//	) {
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		logger.info("code : {} ", request.getParameter("code"));
//		logger.info("state : {} ", request.getParameter("state"));
//
//		String apiURL = socialService.getGoogleURL(code,state);
//		logger.info("apiURL : {} ", apiURL);
//
//		JsonObject token = socialService.getGoogleToken(apiURL);
////		    logger.info("-----------------error : {} ----------------", token.get("error"));
////		    logger.info("error_description : {} ", token.get("error_description"));
////		    logger.info("expires_in : {} ", token.get("expires_in"));
////		    logger.info("access_token : {} ", token.get("access_token"));
////		    logger.info("refresh_token : {} ", token.get("refresh_token"));
////		    logger.info("-------------------token_type : {} ----------------", token.get("token_type"));
//
//		HashMap<String, Object> info = socialService.getUserInfo(token);
//		logger.info("info : {} ", info);
//		String socid = socialService.getSosid(info);
//		if(socid!=null) {
//			UserDTO dto = socialService.socialLogin(socid);
//			boolean isLogin = true;
//			session.setAttribute("isLogin", isLogin);
//			session.setAttribute("dto", dto);
//			return "redirect: /";
//		}else{
//			session.setAttribute("sosid", info);
//			return "login/naver/naverjoin";
//		}
//	}
	
}