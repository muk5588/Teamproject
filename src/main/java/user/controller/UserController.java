package user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dto.UserDTO;
import user.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService service;
    /**
     * 화면부분
     * */
    //관리자페이지
    @RequestMapping("/adminPage")
    public String adminPage(Model model){
        List<UserDTO> list = service.userList();//board생성후 userAll로 변경
        model.addAttribute("list", list);

        return ".user/adminPage";
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
    //상세페이지(마이페이지로 사용가능)
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

    /**
     * 기능부분
     * param: UserDTO
     * */
    @RequestMapping("/user/userInsert")
    public String userInsert(UserDTO dto) {
        service.userInsert(dto);
        return "redirect: /userList";
    }

    @RequestMapping("/user/userUpdate")
    public String userUpdate(UserDTO dto) {
        service.userUpdate(dto);
        return "redirect: /userList";
    }

    @RequestMapping("/user/deleteUser")
    public String deleteUser(UserDTO dto) {
        service.userDelete(dto);
        return "redirect: /userList";
    }
}
