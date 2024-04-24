package login.controller;

import login.service.KakaoService;
import login.service.LoginService;
import login.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import user.dto.UserDTO;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    SocialService socialService;
    @Autowired
    KakaoService kakaoService;

    //로그인 요청
    @RequestMapping("/login/loginProc")
    public String loginproc(UserDTO dto, HttpSession session) {

//        dto = loginService.loginProc(dto);
        boolean isLogin = loginService.login(dto);

        if (isLogin) {
            //로그인 성공
            int loginno = loginService.getLoginNo(dto);


            session.setAttribute("isLogin", isLogin);
            session.setAttribute("loginno", loginno);
            UserDTO login = loginService.info(loginno);
            session.setAttribute("dto", login);

        } else {  //로그인 실패
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
        if (session.getAttribute("token") != null) {
            socialService.naverLogout(session);
        }else if(session.getAttribute("token1") != null){
            kakaoService.kakaoLogout(session);
            session.invalidate();
        }
        else {
            session.invalidate();
        }
        return "redirect:/";
    }


    @RequestMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    //    마이페이지
    @RequestMapping("/user/userDetail")
    public void mypage(@SessionAttribute("loginno") int loginno, Model model) {

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

}
