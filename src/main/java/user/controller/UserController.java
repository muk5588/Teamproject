package user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.Service.UserServiceImpl;
import user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private UserServiceImpl service;
    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert() {
        return "user/userInsert";
    }
    @RequestMapping("/userList")
    //유저리스트 페이지
    public String userList(){
        return  "user/userList";
    }
    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(){
        return "user/userUpdate";
    }
    @RequestMapping("/detailUser")
    public String detail(int id, Model model) {
        //선택한 고객 정보를 DB에 조회해와서
        UserDTO dto = service.userDetail(id);
        //화면에 출력할 수 있도록 Model에 담는다.
        //원래는 string타입으로 담겨야하지만 스프링에서는 자동으로 형변환이 되서 int타입으로 담긴다.

        model.addAttribute("dto", dto);
        return "user/userDetail";
    }
    //기능

}