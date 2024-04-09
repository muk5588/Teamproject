package menu.controller;

import grade.controller.GradeController;
import grade.dao.GradeDao;
import grade.dto.Grade;
import grade.service.GradeService;
import menu.dto.Menu;
import menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dto.UserDTO;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/menu/menuList")
    public String menuList(Model model) {
        List<Menu> list = menuService.menuList();
        model.addAttribute("list", list);
        return "menu/menuList";
    }

    @RequestMapping("/menu/update")
    public String update(int userno, Model model) {
        UserDTO dto = new UserDTO();
        dto.setUserno(userno);
        dto = menuService.update(dto);
        List<Grade> list = gradeService.gradeList();
        model.addAttribute("dto", dto);
        model.addAttribute("list",list);
        return "menu/update";
    }

    @RequestMapping("/menu/menuUpdate")
    public String menuUpdate(UserDTO dto) {
        menuService.menuUpdate(dto);
        return "redirect: /menu/menuList";
    }
}
