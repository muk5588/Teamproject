package login.controller;

import board.dto.Board;
import board.service.BoardService;
import login.service.KakaoService;
import login.service.LoginService;
import login.service.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import user.dto.User;
import util.Paging;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LoginService loginService;
    @Autowired
    SocialService socialService;
    @Autowired
    KakaoService kakaoService;
    @Autowired
    BoardService boardService;

    //로그인 요청
    @RequestMapping("/login/loginProc")
    public String loginproc(User dto, HttpSession session) {

//        dto = loginService.loginProc(dto);
        //userno
        int isLogin = loginService.login(dto);
        logger.info("isLogin : {}", isLogin);
        if (isLogin > 0) {  //로그인 성공
            //유저 정보
            dto.setUserno(isLogin);
            User login = loginService.info(dto);
            logger.info("login : {}", login);

            loginService.insertAccessHistory(login);
            
            session.setAttribute("isLogin", isLogin);
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
    	logger.info("s#############ession DATA  : {} ", session.getAttribute("token"));
    	boolean res;
        if (session.getAttribute("token") != null) {
        	logger.info("네이버 로그아웃쪽 컨트롤러");
        	res = socialService.naverLogout(session);
        }else if(session.getAttribute("token1") != null){
        	logger.info("카카오 로그아웃쪽 컨트롤러");
        	res = kakaoService.kakaoLogout(session);
            session.invalidate();
        }
        else {
            session.invalidate();
        }
        
        if( res = true ) {
        	return "redirect:/";
        }
        return "/error";
    }


    @RequestMapping("/login")
    public String login() {
        return "login/loginForm";
    }

    //    마이페이지
    @RequestMapping("/user/userDetail")
    public void mypage(@SessionAttribute(value = "dto", required = false) User login, Model model) {
        int userno = login.getUserno();
        List<Board> list = boardService.boardList(userno);
        Paging paging = new Paging();
        List<Map<String, Object>> recommList = boardService.getRecommendRes(paging);
        model.addAttribute("dto", login);
        model.addAttribute("list", list);
        model.addAttribute("totalrecomm", recommList);
    }

    //유저 수정하기
//    @RequestMapping("/user/userUpdate")
//    public void update(@SessionAttribute("loginno") int loginno, Model model){
//        UserDTO login = loginService.info(loginno);
//
//        model.addAttribute("dto", login);
//    }

}
