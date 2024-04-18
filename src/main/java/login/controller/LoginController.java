package login.controller;

import com.google.gson.JsonObject;
import login.service.LoginService;
import login.service.socialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private socialService socialService;
    //로그인 요청
    @RequestMapping("/login/loginProc")
    public String loginproc(UserDTO dto, HttpSession session) {

//        dto = loginService.loginProc(dto);
        boolean isLogin = loginService.login(dto);

        if(isLogin){
            //로그인 성공
            int loginno = loginService.getLoginNo(dto);
            

            session.setAttribute("isLogin", isLogin);
            session.setAttribute("loginno", loginno);
            UserDTO login = loginService.info(loginno);
            session.setAttribute("dto", login);

        }else{  //로그인 실패
            session.invalidate();
            return "/login/loginFail";
        }
//        if (dto != null){
//            System.out.print(dto);
//            model.addAttribute("dto",dto);
//            return "redirect: /";
//        }else {
//            return "/login/loginFail";
//        }
        return "redirect:/";
    }
    //로그아웃 요청
    @RequestMapping("/login/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login() {
        return "login/loginForm";
    }
//    마이페이지
    @RequestMapping("/user/userDetail")
    public void mypage(@SessionAttribute("loginno") int loginno, Model model){

        UserDTO login = loginService.info(loginno);

        model.addAttribute("dto", login);
    }

    //유저 수정하기
//    @RequestMapping("/user/userUpdate")
//    public void update(@SessionAttribute("loginno") int loginno, Model model){
//        UserDTO login = loginService.info(loginno);
//
//        model.addAttribute("dto", login);
//    }
    @GetMapping("/kakaoLogin")
    public String kakaoLogin(
            @RequestParam("code")String code
            , HttpSession session
            , UserDTO member
            , String sosId
    ) {

        String access_Token = socialService.getAccessToken(code);

//		logger.info("Membercontroller access_token : {}" + access_Token);
        HashMap<String, Object> userInfo = socialService.getUserInfo(access_Token);

        member.setSuserno((String)userInfo.get("id"));

        sosId = (String)userInfo.get("id");
        UserDTO sMember = loginService.selectSuser(sosId);

        boolean kakao = loginService.selectKakao(member);


        // 소셜no가 있으면 메인화면으로 이동
        if( kakao ) {

            session.setAttribute("login", true);
            session.setAttribute("userId", userInfo.get("id"));
            session.setAttribute("access_Token", access_Token);
            session.setAttribute("userno", sMember.getUserno());

            return "./main";


            // 소셜 no가 없으면 회원가입 창으로 이동
        } else {

            session.setAttribute("userId", userInfo.get("id"));
            session.setAttribute("access_Token", access_Token);

        }

////	클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//	    if (userInfo.get("email") != null) {
//	    	session.setAttribute("login", true);
//	        session.setAttribute("userId", userInfo.get("id"));
//	        session.setAttribute("access_Token", access_Token);
//	    }

        return "redirect:./login/socialjoin";

    }


    // 네이버
    @RequestMapping("/naverLogin")
    public ModelAndView naverLogin(
            HttpSession session ) {


        Map<String, Object> map = socialService.getStateApiUrl();

        String apiURL = (String) map.get("apiURL");
        String state = (String) map.get("state");

        // 세션 또는 별도의 저장 공간에 상태 토큰을 저장
        session.setAttribute("state", state);


        // 외부 URL로 retrun
        return new ModelAndView("redirect:" + apiURL);

    }



    @GetMapping("/login/navercallback")
    public String naverCallback(

            HttpServletRequest request, HttpSession session, Model model
            , UserDTO member, String naverId) {

        String code = request.getParameter("code");
        String state = (String)session.getAttribute("state");

//		session.invalidate();

        String apiURL = socialService.getApiURL(code, state);
        JsonObject Token = socialService.getTokenNaver(apiURL);

        HashMap<String, Object> naverInfo = socialService.getNaverInfo(Token);


        member.setSuserno((String)naverInfo.get("id"));
        naverId = (String)naverInfo.get("id");
        UserDTO sMember = loginService.selectSuser(naverId);

        boolean social = loginService.selectKakao(member);



        // 소셜no가 있으면 메인화면으로 이동
        if( social ) {

            session.setAttribute("login", true);
            session.setAttribute("naverId", naverId);

            session.setAttribute("userno", sMember.getUserno());

            return "./main";


            // 소셜 no가 없으면 회원가입 창으로 이동
        } else {

            session.setAttribute("naverId", naverId);


        }

        return "redirect:./socialjoin";



//		if ( social ) {
//			logger.debug("회원 가입내역 없음");
//
//			session.setAttribute("naverId", naverId);
//
//
//		    return "redirect:./socialjoin";
//
//		} else {
//
//			logger.debug("회원 가입내역 존재");
//			session.setAttribute("login", true);
//			session.setAttribute("naverId", naverId);
//
//			return "./main";
//		}


    }
}
