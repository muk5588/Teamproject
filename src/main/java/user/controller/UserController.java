package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.Service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private UserService userService = new UserService();
    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert() {
        return "user/join";
    }
    @RequestMapping("/userList")
    //유저리스트 페이지
    public String userList(){
        return  "user/userListForm";
    }
    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(){
        return "user/update";
    }
    //기능
    @RequestMapping("/user/getUserListCount.do")
    public String getUsrListCount(HttpServletRequest request, Model model)throws Exception{
        //userService.getUsrListCount(request, model);
        model.addAttribute("code",userService.getUserListCount(request, model));
        return "jsonView";
    }
    @RequestMapping("/user/getUserList")
    public String getuserList(HttpServletRequest request, Model model)throws Exception{
        userService.userList(request, model);
        return "jsonView";
    }
    @RequestMapping("/user/getBoardList")
    public String getBoardList(HttpServletRequest request , Model model)throws Exception{
        userService.getBoardList(request,model);
        return "jsonView";
    }

    /*사용자 등록 */
    @RequestMapping("/user/insertUser")
    public String insertUser(HttpServletRequest request, Model model) throws Exception {
        userService.insertUser(request, model);
        return "jsonView";
    }
    /*사용자 수정*/
    @RequestMapping("/user/updateUser")
    public String updateUser(HttpServletRequest request, Model model) throws Exception {
        userService.updateUser(request, model);
        return "jsonView";
    }
    /*사용자 삭제 */
    @RequestMapping("/user/deleteUser")
    public String deleteUser(HttpServletRequest request, Model model)throws Exception{
        userService.deleteUser(request, model);
        return "jsonView";
    }
}
