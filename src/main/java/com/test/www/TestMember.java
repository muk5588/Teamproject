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
        model.addAttribute("vo", vo);
        model.addAttribute("method", "데이터 객체");

        return "login/info";
    }

}
