package login.controller;

import login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dto.UserDTO;

import javax.servlet.http.HttpSession;

@Controller("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    //로그인 요청
    @RequestMapping("loginProc")
    public String loginproc(UserDTO dto, Model model) {

        dto = loginService.loginProc(dto);
        if (dto != null){
            System.out.print(dto);
            model.addAttribute("dto",dto);
            return "redirect: /";
        }else {
            return "/insertUser";
        }
    }
    //로그아웃 요청
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/login";
    }


    @RequestMapping("/login")
    public String login() {
        return "login/loginForm";
    }

}
