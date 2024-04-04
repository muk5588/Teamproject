package login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {



    //로그인 요청
    @RequestMapping("/login_result")
    public String login_result(String id, String pwd) {
        //아이디, 비번 일치시 home 화면으로 연결,
        //				일치하지 않는 경우 로그인 화면으로
        //DB에서 읽어온 데이터가 hong, 1234라고 가정
        if(id.equals("hong") && pwd.equals("1234")) {
//			return "home"; //forward : 요청 url이 유지되나 응답에 해당하는 url이 서로 상이한 경우 변하지 않는다.
            return "redirect:/";	//home.jsp의 요청 url은 '/'
        } else {
//			return "member/login"; //forward
            return "redirect:login";	//login.jsp의 요청 url
        }
    }
    //로그아웃 요청
    @ResponseBody @RequestMapping("/login/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("login_info");
    }


    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }

}
