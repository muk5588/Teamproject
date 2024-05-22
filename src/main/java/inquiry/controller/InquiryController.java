package inquiry.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Inquiry;
import inquiry.service.InquiryService;
import user.dto.User;
import util.Paging;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {    

    // Logger 선언
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // InquiryService 의존성 주입
    @Autowired 
    private InquiryService inquiryService;
    
    // Ajax로 문의를 보내는 메서드
    @ResponseBody
    @RequestMapping("/send")
    public int sendInquiry(String touser, String inquiryDetail, HttpSession session) {
        // 로그 출력
        logger.debug("/send ajax 들어옴");
        logger.debug("touser : {}",touser);
        logger.debug("content : {}",inquiryDetail);
        
        // 초기값 설정
        int res = 0;
        
        // 문의 내용이 비어있지 않은 경우
        if (inquiryDetail != null && !"".equals(inquiryDetail)) {
            // 세션에서 사용자 정보 가져오기
            User user = (User) session.getAttribute("dto1");
            int sendUser = user.getUserno();
            
            // Inquiry 객체 생성 및 정보 설정
            Inquiry inquiry = new Inquiry();
            inquiry.setUserNo(sendUser);
            inquiry.setInquiryDetail(inquiryDetail);
            inquiry.setManagerNo(1); // 관리자 번호 1으로 설정
            
            // InquiryService를 통해 문의 등록
            res = inquiryService.insertInquiry(inquiry);
        }
        
        // 문의 등록 성공시 세션에 문의 목록 갱신
        if (res > 0) {
            list(session);
        }
        
        return res;
    }
    
    // 문의 목록을 세션에 저장하는 메서드
    @RequestMapping("/list")
    public void list(HttpSession session) {
        User user = (User) session.getAttribute("dto1");
        int sendUser = user.getUserno();
        List<Inquiry> list = inquiryService.getListBySendUser(sendUser);
        session.setAttribute("list", list);
    }

    // 문의 보내기 페이지로 이동하는 메서드
    @RequestMapping("/sendForm")
    public void sendForm() {}

    // 문의 삭제하는 메서드
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
    
    // 답변 처리하는 메서드
    @ResponseBody
    @RequestMapping("/answerProc")
    public int answerProc(@RequestParam("inquiryNo") int inquiryNo, 
                          @RequestParam("answer") String answer) {
        logger.debug("inquiryNo : {}", inquiryNo);
        logger.debug("answer : {}", answer);

        // 답변 처리를 완료했음을 표시하는 값 설정
        String complete = "Y"; 
        
        // 현재 시간을 답변 일자로 설정
        Date answerDate = new Date(); 

        // Inquiry 객체 생성 및 정보 설정
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryNo(inquiryNo);
        inquiry.setAnswer(answer);
        inquiry.setComplete(complete);
        inquiry.setAnswerDate(answerDate); // 답변일자 설정

        // 답변 처리 결과 반환
        int result = inquiryService.updateInquiry(inquiry);

        return result;
    }
    
    // 사용자의 문의 목록을 요청하는 메서드
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

    // 관리자용 문의 목록을 요청하는 메서드
//    @RequestMapping("/adminList")
//    public String adminList(HttpSession session, Model model,@RequestParam(defaultValue ="0") int curPage
//    		,@RequestParam(name="search", required = false)String search
//    		) {
//    	String URL = "/inquiry/adminList";
//    	Paging paging = new Paging();
//    	if( search != null && !"".equals(search)) {
//    		paging.setSearch(search);
//    	}
//    	paging = inquiryService.getPaging(curPage,paging);
//    	if( search != null && !"".equals(search)) {
//    		paging.setSearch(search);
//    	}
//        List<Inquiry> inquiries = inquiryService.getAllInquiries(paging);
//        model.addAttribute("paging", paging);
//        model.addAttribute("URL", URL);
//        session.setAttribute("adminList", inquiries);
//        model.addAttribute("list", inquiries);
//        return "/inquiry/adminList"; 
//    }
    
    @RequestMapping("/adminList")
    public String adminList(HttpSession session, Model model, @RequestParam(defaultValue = "0") int curPage,
            @RequestParam(name = "search", required = false) String search) {
        String URL = "/inquiry/adminList";
        Paging paging = new Paging();
        if (search != null && !"".equals(search)) {
            paging.setSearch(search);
        }
        paging = inquiryService.getPaging(curPage, paging);
        if (search != null && !"".equals(search)) {
            paging.setSearch(search);
        }
        List<Inquiry> inquiries = inquiryService.getAllInquiries(paging);
        model.addAttribute("paging", paging);
        model.addAttribute("URL", URL);
        session.setAttribute("adminList", inquiries);
        model.addAttribute("list", inquiries);
        return "/inquiry/adminList";
    }
}
