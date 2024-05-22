package inquiry.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Inquiry;
import inquiry.service.InquiryService;
import user.dto.User;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {    
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired 
    private InquiryService inquiryService;
    
    @RequestMapping("/")
    public String inquiry() {
        return "redirect:/inquiry/list";
    }
    
    @ResponseBody
    @RequestMapping("/send")
    public int sendInquiry(String touser, String inquiryDetail, HttpSession session) {
        logger.debug("/send ajax 들어옴");
        logger.debug("touser : {}",touser);
        logger.debug("content : {}",inquiryDetail);
        
        int res = 0;
        if (inquiryDetail != null && !"".equals(inquiryDetail)) {
            User user = (User) session.getAttribute("dto1");
            int sendUser = user.getUserno();
            Inquiry inquiry = new Inquiry();
            inquiry.setUserNo(sendUser);
            inquiry.setInquiryDetail(inquiryDetail);
            inquiry.setManagerNo(1); // 관리자 번호 1으로 설정
            res = inquiryService.insertInquiry(inquiry);
        }
        
        if (res > 0) {
            list(session);
        }
        
        return res;
    }
    
    @RequestMapping("/list")
    public void list(HttpSession session) {
        User user = (User) session.getAttribute("dto1");
        int sendUser = user.getUserno();
        List<Inquiry> list = inquiryService.getListBySendUser(sendUser);
        session.setAttribute("list", list);
    }

    @RequestMapping("/sendForm")
    public void sendForm() {}

    @ResponseBody
    @RequestMapping("/delete")
    public int delete(@RequestParam("inquiryNo") List<Integer> inquiryNo) {
        logger.debug("inquiryNo : {}", inquiryNo);
        int deleteCount = 0;
        for (Integer no : inquiryNo) {
            deleteCount += inquiryService.deleteByInquiryNo(no);
        }
        return deleteCount;
    }
    
    @ResponseBody
    @RequestMapping("/answerProc")
    public int answerProc(@RequestParam("inquiryNo") int inquiryNo, 
                          @RequestParam("answer") String answer) {
        logger.debug("inquiryNo : {}", inquiryNo);
        logger.debug("answer : {}", answer);

        String complete = "Y"; // Complete 값을 'Y'로 설정하여 문의에 대한 답변이 완료되었음을 표시
        Date answerDate = new Date(); // 현재 시간을 답변 일자로 설정

        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(inquiryNo);
        inquiry.setAnswer(answer);
        inquiry.setComplete(complete);
        inquiry.setAnswerDate(answerDate); // 답변일자 설정

        int result = inquiryService.updateInquiry(inquiry);

        return result;
    }
    
    @RequestMapping("/inquirylist")
    public void inquirylist(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("dto1");
        int sendUser = user.getUserno();
        List<Inquiry> list = inquiryService.getListBySendUser(sendUser);
        for (Inquiry m : list) {
            logger.debug("m : {}", m);
        }
        logger.debug("list : {} ", list);

        request.setAttribute("list", list);
    }

    @RequestMapping("/adminList")
    public String adminList(HttpSession session) {
        List<Inquiry> list = inquiryService.getListByManagerNo(1); 
        session.setAttribute("adminList", list);
        return "/inquiry/adminList"; 
    }

    @GetMapping("/adminList")
    public String adminList(Model model) {
        List<Inquiry> inquiries = inquiryService.getAllInquiries();
        model.addAttribute("list", inquiries);
        return "inquiry/adminList";
    }
    
}
