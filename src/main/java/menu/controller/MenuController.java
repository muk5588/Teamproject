package menu.controller;

import menu.dto.Menu;
import menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("/menu/menuList")
    public String menuList(Model model){
        List<Menu> list = menuService.menuList();
        model.addAttribute("list", list);
        return "menu/menuList";
    }
}
