package user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dto.UserDTO;
import user.service.UserService;
import user.service.UserServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert() {
        return "user/userInsert";
    }

    @RequestMapping("/userList")
    //유저리스트 페이지
    public String userList(HttpSession session, Model model) {
        List<UserDTO> list = service.userList();
        model.addAttribute("list", list);
        return "user/userList";
    }

    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(UserDTO userid, Model model) {
        model.addAttribute("dto", service.userDetail(userid));
        return "user/updateUser";
    }

    @RequestMapping("/detailUser")
    public String detail(UserDTO userid, Model model) {
        //선택한 고객 정보를 DB에 조회해와서
        UserDTO dto = service.userDetail(userid);
        //화면에 출력할 수 있도록 Model에 담는다.
        //원래는 string타입으로 담겨야하지만 스프링에서는 자동으로 형변환이 되서 int타입으로 담긴다.

        model.addAttribute("dto", dto);
        return "user/userDetail";
    }

    //기능
    @RequestMapping("user/userInsert")
    public String userInsert(UserDTO dto) {
        service.userInsert(dto);
        return "user/userList";
    }

    @RequestMapping("/user/userUpdate")
    public String userUpdate(UserDTO dto) {
        service.userUpdate(dto);
        return "redirect:detail?id=" + dto.getUserid();
    }

    @RequestMapping("/user/deleteUser")
    public String deleteUser(int userno) {
        service.userDelete(userno);
        return "user/userList";
    }
}
