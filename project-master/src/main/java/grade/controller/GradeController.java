package grade.controller;

import grade.dto.Grade;
import grade.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService service;

    @RequestMapping("/gradeList")
    public String gradeList(Model model){
        List<Grade> list = service.gradeList();
        model.addAttribute("list",list);
        return "grade/gradeList";
    }
    @RequestMapping("/gradeInsert")
    public  String gradeInsert(){
        return "grade/gradeInsert";
    }

    @RequestMapping("/gradeUpdate")
    public  String gradeUpdate(int gradeno, Model model){
        Grade grade = new Grade();
        grade.setGradeno(gradeno);
        grade = service.gradeDetail(grade);
        model.addAttribute("grade",grade);
        return "grade/gradeUpdate";
    }
    @RequestMapping("/gradeDetail")
    public String gradeDetail(int gradeno, Model model){
        Grade grade = new Grade();
        grade.setGradeno(gradeno);
        grade = service.gradeDetail(grade);
        model.addAttribute("grade",grade);
        return "grade/gradeDetail";
    }
    /**
     * 기능부분
     * param: Grade
     * */
    @RequestMapping("/insertGrade")
    public String insertGrade(Grade grade){
        service.gradeInsert(grade);
        return "redirect: ./gradeList";
    }
    @RequestMapping("/updateGrade")
    public String updateGrade(Grade grade){
        service.gradeUpdate(grade);
        return "redirect: ./gradeList";
    }
    @RequestMapping("/deleteGrade")
    public String deleteGrade(Grade grade){
        service.gradeDelete(grade);
        return "redirect: ./gradeList";
    }

}
