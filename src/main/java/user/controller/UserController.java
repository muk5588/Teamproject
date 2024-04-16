package user.controller;

import grade.dto.Grade;
import grade.service.GradeService;
import login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import user.dto.EmailCheck;
import user.dto.UserDTO;
import user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

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
    /**
     * 화면부분
     * */
    //관리자페이지
    @RequestMapping("/adminPage")
    public String adminPage(Model model){
        List<UserDTO> list = service.userList();//board생성후 userAll로 변경
//        List<Board> list2  = boardService.boardList();
        List<Grade> list3 = gradeService.gradeList();
        model.addAttribute("list", list);
//        model.addAttribute("list2", list2);
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
        List<UserDTO> list = service.userList();
        model.addAttribute("list", list);
        return "user/userList";
    }

    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(int userno, Model model) {
        UserDTO dto = new UserDTO();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
//        int userno = dto.getUserno();
        model.addAttribute("dto", dto);
        return "user/userUpdate";
    }
    //유져상세페이지(관리자용)
    @RequestMapping("/detailUser")
    public String userDetail(int userno, Model model) {
        //선택한 고객 정보를 DB에 조회해와서
        UserDTO dto = new UserDTO();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
//        int userno = dto.getUserno();
        //화면에 출력할 수 있도록 Model에 담는다.
        //원래는 string타입으로 담겨야하지만 스프링에서는 자동으로 형변환이 되서 int타입으로 담긴다.

        model.addAttribute("dto", dto);
        return "user/userDetail";
    }
    @RequestMapping("/searchUser")
    public String searchid(String value,Model model) {
        model.addAttribute("value", value);
        return "user/userSearch";
    }

    /**
     * 기능부분
     * param: UserDTO
     * */
    @RequestMapping("/userInsert")
    public String userInsert(UserDTO dto) {
        service.userInsert(dto);
        return "redirect: ./userList";
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(UserDTO dto) {
        service.userUpdate(dto);
        return "redirect: ./userList";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(UserDTO dto) {
        service.userDelete(dto);
        return "redirect: ./userList";
    }
    @ResponseBody
    @RequestMapping("/passChk")
    public int passChk(UserDTO dto) throws Exception {
        int res = service.passChk(dto);
        return res;
    }
    @ResponseBody
    @RequestMapping("/idChk")
    public int idChk(UserDTO dto) throws Exception {
        int res = service.idChk(dto);
        return res;
    }
    @RequestMapping("/idckeck")
    public String idckeck(UserDTO dto) throws Exception {
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
    @PostMapping("/checkEmail")
	public @ResponseBody EmailCheck checkEmail (String email , Model model){
		int num =  service.checkEmail(email);
		return new EmailCheck(email,num);
	}
    @RequestMapping("/searchId")
    public String searchId(HttpServletRequest request, Model model, UserDTO dto,
                           @RequestParam String name,
                           @RequestParam String email) {
        try {
            dto.setName(name);
            dto.setEmail(email);
            UserDTO userid = service.findUserId(dto);
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
                               @RequestParam String id, @RequestParam String name,@RequestParam String email,
                               UserDTO dto) {
        try {
            dto.setUserid(id);
            dto.setName(name);
            dto.setEmail(email);
            int search = service.findUserpw(dto);

            if(search == 0) {
                model.addAttribute("msg", "기입된 정보가 잘못되었습니다. 다시 입력해주세요.");
            }

            String newPwd = UUID.randomUUID().toString().split("-")[4];
            dto.setUserpw(newPwd);
            service.pwdUpdate(dto);
            model.addAttribute("newPwd", newPwd);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "오류가 발생되었습니다.");
        }
        return "user/findResult";
    }
}
