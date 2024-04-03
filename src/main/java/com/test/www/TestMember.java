package com.test.www;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import login.MemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class TestMember {

    //어떤 요청에 대해 연결할 것인지 지정 @RequestMapping()
    //어노테이션이 올바르게 되었을때와 아닐때의 404에러 메시지가 다르다


    @RequestMapping("/join")
    public String join() {
        //데이터를 입력할 곳이기 때문에 전달할 데이터가 없음

        return "login/join";
    }
    //데이터 객체(VO)로 form의 데이터를 접근
	/* 기존에는 이렇게 하나 join()의 매개변수에 MemberVO vo가 있다면 하지않아도 된다.
	public String join() {
		MemberVO vo = new MemberVO();
		vo.set ~~~
	}
	*/
    @RequestMapping("/insertuser")
    public String insertUser(MemberVo vo, Model model) {
        model.addAttribute("vo",vo);
        model.addAttribute("method", "HttpServletRequest");

        return "login/info";
    }
    @RequestMapping("/login")
    public String login() {

        return "member/login";
    }

    //로그인 결과 화면
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
}
