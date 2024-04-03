package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dao.UserDao;
import user.service.Userservice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserController {
@Autowired
Userservice service;
@Autowired
UserDao dao;
    @RequestMapping("/usr/usr/usrListForm.do")
    public String usrListForm(HttpServletRequest request, Model model,Map<String, Object> map)throws Exception{

        model.addAttribute("ROLELIST",dao.getUserList(map));
        map.put("SORT", "ROLENAME");
        model.addAttribute("ROLELIST1",dao.getUserRoleList(map));
        return "/usr/usrListForm.usr-tiles";
    }
    @RequestMapping("/user/getuserListCount")
    public String getuserListCount(HttpServletRequest request, Model model)throws Exception{
        //userService.getuserListCount(request, model);
        model.addAttribute("code",service.getuserListCount(request, model));
        return "jsonView";
    }
    @RequestMapping("/user/getUserList")
    public String getuserList(HttpServletRequest request, Model model, Map<String, Object> map)throws Exception{
        service.getuserList(request, model);
        return "jsonView";
    }
    @RequestMapping("/user/saveUser")
    public String saveUser(HttpServletRequest request, Model model) throws Exception {
        service.saveUser(request, model);
        return "jsonView";
    }
    @RequestMapping("/user/deleteUser")
    public String deleteUser(HttpServletRequest request, Model model)throws Exception{
        service.deleteUser(request, model);
        return "jsonView";
    }

}
