package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    private LoginServiceImpl service;


    //로그인 요청
    @ResponseBody
    @RequestMapping("/login")
    public String login(String id, String pw, HttpSession session) {
        //화면에서 입력한 아이디와 비밀번호가 일치하는 회원 정보가 DB에 있는지 확인하여
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("id", id);
        map.put("pw", pw);
        MemberVo vo = service.member_login(map);

        //일치하는 회원 정보가 있다면 회원 정보를 세션에 담는다
        session.setAttribute("login_info", vo);

        return vo == null ? "false" : "true";
    }

    //로그아웃 요청
    @ResponseBody @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.removeAttribute("login_info");
    }

    //회원가입 화면 요청
    @RequestMapping("/login")
    public String member() {
        return "login/join";
    }
}
