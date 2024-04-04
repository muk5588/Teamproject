package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.Service.UserService;
import user.Service.UserServiceImpl;
import user.dto.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert(UserDTO dto, Model model) {
        model.addAttribute("dto",dto);
        return "user/userInsert";
    }
    @RequestMapping("/userList")
    //유저리스트 페이지
    public String userList(HttpSession session, Model model){
        session.setAttribute("list","list");
        System.out.print("ssss");
        List<UserDTO> list = service.userList();
        model.addAttribute("list",list);
        System.out.print("sssss");
        return  "user/userList";
    }
    //수정 페이지
    @RequestMapping("updateUser")
    public String userUpdate(int userid,Model model){
        model.addAttribute("dto",service.userDetail(userid));
        return "user/updateUser";
    }

    @RequestMapping("/detailUser")
    public String detail(int userid, Model model) {
        //선택한 고객 정보를 DB에 조회해와서
        UserDTO dto = service.userDetail(userid);
        model.addAttribute("dto", dto);
        return "user/userDetail";
    }
    //기능
    @RequestMapping("/user/userInsert")
    public String userInsert(UserDTO dto){
        service.userInsert(dto);
        return "redirect:userList";
    }
    @RequestMapping("/user/userUpdate")
    public String userUpdate(UserDTO dto){
        service.userUpdate(dto);
        return "redirect:detail?id="+dto.getUserid();
    }
    @RequestMapping("/user/deleteUser")
    public String deleteUser(int userid){
        service.userDelete(userid);
        return "redirect:userList";
    }
}
