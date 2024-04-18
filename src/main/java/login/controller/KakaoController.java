package login.controller;


import login.service.socialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;



@Controller
@RequestMapping("/login")
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	@Autowired
	socialService kakaoService;
		@RequestMapping("/kakaoLogin")
	public String kakaoLogin(@RequestParam("code")String code, HttpSession session) {
		
		logger.info("code: {}", code);
		String access_Token = kakaoService.getAccessToken(code);

		logger.info("controller access_token : {}" + access_Token);
		
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);

	    if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
	    
		return "redirect:/";
		
	}
	
	@GetMapping("/kakaoLogout")
	public void inde() {
		
		logger.info("로그아웃");
		
	}  
	
//	@RequestMapping("/kakaoLogout")
//	public String logout(HttpSession session) {
//		kakaoService.kakaoLogout((String)session.getAttribute("access_Token"));
//	    session.removeAttribute("access_Token");
//	    session.removeAttribute("userId");
//	    return "/login/login";
//	}
	
	
	
	
	

}


