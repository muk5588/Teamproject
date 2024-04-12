package menu.controller;

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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private GradeService gradeService;
//    @Autowired  Board기능 개발후 업데이트
//    private

    @RequestMapping("/menuList")
    public String menuList(Model model) {
        List<Menu> list = menuService.menuList();
        model.addAttribute("list", list);
//        List<>
        return "menu/menuList";
    }

    @RequestMapping("/update")
    public String update(int userno, Model model) {
        UserDTO dto = new UserDTO();
        dto.setUserno(userno);
        dto = menuService.update(dto);
        List<Grade> list = gradeService.gradeList();
        model.addAttribute("dto", dto);
        model.addAttribute("list",list);
        return "menu/update";
    }

    @RequestMapping("/menuUpdate")
    public String menuUpdate(UserDTO dto) {
        menuService.menuUpdate(dto);
        return "redirect: /menu/menuList";
    }
    @RequestMapping("/updateBorad")
    public String updateBorad(int boardno, Model model) {
//        List<Board> list =
        return "redirect: /menu/updateBoard";
    }
//    @RequestMapping("/menuUpdate")
//    public String menuUpdateBoard() {
////        menuService.updateBoardMenu();
//
//        return "redirect: /menu/menuList";
//    }
}
