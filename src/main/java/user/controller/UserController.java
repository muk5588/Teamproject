package user.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import board.dto.Board;
import board.service.BoardService;
import grade.dto.Grade;
import grade.service.GradeService;
import login.dto.AccessHistory;
import login.service.LoginService;
import user.dto.EmailCheck;
import user.dto.User;
import user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService service;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private BoardService boardService;
    /**
     * 화면부분
     * */
    //관리자페이지
    @RequestMapping("/adminPage")
    public String adminPage(Model model){
        List<AccessHistory> list2  = loginService.loginHistory();
        List<Grade> list3 = gradeService.gradeList();
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        return "user/adminPage";
    }
    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert() {
        return "user/userInsert";
    }

    @RequestMapping("/userList")
    //유저전체리스트 페이지
    public String userList( Model model) {
        List<User> list = service.userList();
        model.addAttribute("list", list);
        return "user/userList";
    }

    @RequestMapping("/userBlack")
    //유저블랙리스트 페이지
    public String userBlack( Model model) {
        List<User> list = service.userList();
        model.addAttribute("list", list);
        return "user/userBlack";
    }
    
    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(int userno, Model model) {
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
//        int userno = dto.getUserno();
        model.addAttribute("dto", dto);
        return "user/userUpdate";
    }
    //유져상세페이지(관리자용)
    @RequestMapping("/detailUser")
    public String userDetail(int userno, Model model, HttpSession session) {
        //선택한 고객 정보를 DB에 조회해와서
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
        model.addAttribute("dto", dto);
        User login = (User) session.getAttribute("dto1");
        model.addAttribute("dto1", login);
        model.addAttribute("userno", userno);
        List<Board> list = boardService.boardList(userno);
        model.addAttribute("list", list);
//        int userno = dto.getUserno();
        //화면에 출력할 수 있도록 Model에 담는다.
        //원래는 string타입으로 담겨야하지만 스프링에서는 자동으로 형변환이 되서 int타입으로 담긴다.


        return "user/userDetail";
    }
    @RequestMapping("/searchUser")
    public String searchid(String value,Model model) {
        model.addAttribute("value", value);
        return "user/userSearch";
    }
    @RequestMapping("/updatePass")
    public String updatePass(int userno, Model model) {
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
        model.addAttribute("dto", dto);
        return "user/updatePassword";
    }

    /**
     * 기능부분
     * param: UserDTO
     * */
    @RequestMapping("/userInsert")
    public String userInsert(User dto) {
        service.userInsert(dto);
        return "redirect: ./userDetail";
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(User dto) {
        service.userUpdate(dto);
        return "redirect: ./userDetail";
    }

	@PostMapping("/userBlack") 
    public String userBlack(User dto) {
        service.userBlack(dto);
        return "redirect: ./userBlack";
    }
    
    @RequestMapping("/deleteUser")
    public String deleteUser(User dto) {
        service.userDelete(dto);
        return "redirect: /";
    }
    
    @ResponseBody
    @RequestMapping("/passChk")
    public int passChk(User dto) throws Exception {
        int res = service.passChk(dto);
        return res;
    }
    @ResponseBody
    @RequestMapping("/idChk")
    public int idChk(User dto) throws Exception {
        int res = service.idChk(dto);
        return res;
    }
    @RequestMapping("/idckeck")
    public String idckeck(User dto) throws Exception {
        int res = service.idChk(dto);
        try {
            if (res == 1) {
                return "redirect: /user/insertUser";
            }else if (res == 0) {
                service.userInsert(dto);
            }
        }catch (Exception e) {
            throw new Exception();
        }
        return "redirect: /";
    }
    @ResponseBody
    @PostMapping("/nickChk")
    public int nickChk(User dto) {
    	int res = service.nickChk(dto);

        return res;
    	
    }
    @PostMapping("/checkEmail")
	public @ResponseBody EmailCheck checkEmail (String email , Model model){
		int num =  service.checkEmail(email);
		return new EmailCheck(email,num);
	}
    @RequestMapping("/searchId")
    public String searchId(HttpServletRequest request, Model model, User dto,
                           @RequestParam String name,
                           @RequestParam String email) {
        try {
            dto.setName(name);
            dto.setEmail(email);
            User userid = service.findUserId(dto);
            model.addAttribute("finduserid", userid);
            String value = "id";
            model.addAttribute("value", value);

        } catch (Exception e) {
            model.addAttribute("msg", "오류가 발생되었습니다.");
            e.printStackTrace();
        }
        return "user/findResult";
    }
    @RequestMapping("/searchPw")
    public String searchPw(HttpServletRequest request, Model model,
                               @RequestParam String userid2, @RequestParam String name2,@RequestParam String email2,
                               User dto) {
        try {
            dto.setUserid(userid2);
            dto.setName(name2);
            dto.setEmail(email2);
            User search = service.findUserpw(dto);

            if(search == null) {
                model.addAttribute("msg", "기입된 정보가 잘못되었습니다. 다시 입력해주세요.");
            }

            String newPwd = UUID.randomUUID().toString().split("-")[4] + "!!";
            dto.setUserpw(newPwd);
            service.updateUserpw(dto);
            model.addAttribute("newPwd", newPwd);
            String value = "pw";
            model.addAttribute("value", value);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "오류가 발생되었습니다.");
        }
        return "user/findResult";
    }
    @RequestMapping("/userPass")
    public String userPass(HttpServletRequest request, HttpSession session) {
        User dto = new User();
        dto.setName(request.getParameter("name"));
        dto.setEmail(request.getParameter("email"));
        dto.setUserid(request.getParameter("userid"));
        dto.setUserpw(request.getParameter("userpw"));
        service.updateUserpw(dto);
        session.invalidate();
        return "redirect: /";
    }
    @RequestMapping("/userLog")
    public String userLog(Model model){
        List<AccessHistory> list = loginService.history();
        model.addAttribute("list",list);
        return "user/userLog";
    }
    
}
