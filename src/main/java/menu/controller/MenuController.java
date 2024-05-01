package menu.controller;

import board.dto.Category;
import grade.dto.Grade;
import grade.service.GradeService;
import menu.dto.Menu;
import menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import user.dto.User;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuService menuService;
    @Autowired
    private GradeService gradeService;
//    @Autowired  Board기능 개발후 업데이트
//    private

    @RequestMapping("/menuList")
    public String menuList(Model model) {
        List<Menu> list = menuService.menuList();
        List<Category> list2 = menuService.categoryList();
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);
        return "menu/menuList";
    }

    @RequestMapping("/update")
    public String update(int userno, Model model) {
        User dto = new User();
        dto.setUserno(userno);
        dto = menuService.update(dto);
        List<Grade> list = gradeService.gradeList();
        model.addAttribute("dto", dto);
        model.addAttribute("list", list);
        return "menu/update";
    }

    @RequestMapping("/menuUpdate")
    public String menuUpdate(User dto) {
        menuService.menuUpdate(dto);
        return "redirect: /menu/menuList";
    }

    @RequestMapping("/updateBoard")
    public String updateBorad(int categoryno, Model model) {
        Category category = new Category();
        category.setCategoryNo(categoryno);
        category = menuService.updateBorad(category);
        List<Grade> list = gradeService.gradeList();
        model.addAttribute("category", category);
        model.addAttribute("list", list);
        return "menu/updateBoard";
    }

    @RequestMapping("/categoryUpdate")
    public String menuUpdateBoard(Category category) {
        menuService.categoryUpdate(category);
        return "redirect: /menu/menuList";
    }
}
