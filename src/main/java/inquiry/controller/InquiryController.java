package inquiry.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import inquiry.dto.Inquiry;
import inquiry.service.InquiryService;
import user.dto.User;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private InquiryService inquiryService;
	
	@RequestMapping("/")
	public String message() {
		return "redirect:/inquiry/list";
	}
    
	@ResponseBody
	@RequestMapping("/send")
	public int sendMessage(String touser, String inquiryDetail, HttpSession session) {
		logger.debug("/send ajax 들어옴");
		logger.debug("touser : {}",touser);
		logger.debug("content : {}",inquiryDetail);
		//
		int res = 0;
		if( inquiryDetail == null || "".equals(inquiryDetail)) {
//			return null;
		}
		if( touser != null && !"".equals(touser)) {
			User toUser = inquiryService.getUserByNickName(touser);
			User user = (User) session.getAttribute("dto1");
			int sendUser = user.getUserno();
			Inquiry inquiry = new Inquiry();
			inquiry.setUserNo(sendUser);
			inquiry.setManagerNo(toUser.getUserno());
			inquiry.setInquiryDetail(inquiryDetail);
			
			res = inquiryService.insertInquiry(inquiry);
		}
		return res;
	}
	
	
	@RequestMapping("/list")
	public void list(HttpSession session, HttpServletRequest res) {
		int userNo = (int) session.getAttribute("isLogin");
		List<Inquiry> list = inquiryService.getListByUserno(userNo);
		for(Inquiry m : list) {
			logger.debug("m : {}", m);
		}
		logger.debug("list : {} ", list);
		
		res.setAttribute("list", list);
	}
	@RequestMapping("/sendForm")
	public void sendForm() {}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(
			@RequestParam("inquiryNo") String[] param
			) {
		ArrayList<Integer> inquiryNo = new ArrayList<Integer>();
		int temp = 0;
		for(int i = 0; param.length >i; i++) {
			temp = Integer.parseInt(param[i]);
			inquiryNo.add(temp);
		}
		logger.debug("messageNo : {}", inquiryNo);
		int res = inquiryService.deleteByInquiryNo(inquiryNo);
		
		return res;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/answerProc")
	public int answerProc(@RequestParam("inquiryNo")int inquiryNo , @RequestParam("check")boolean check) {
		logger.debug("inquiryNo : {}", inquiryNo);
		logger.debug("check : {}", check);
		String complete = "N";
		if( check ) {
			complete = "Y";
		}else if( check ) {
			complete = "N";
		}
		Inquiry answerInquiry= new Inquiry();
		answerInquiry.setAnswer(complete);
		answerInquiry.setInquiryNo(inquiryNo);
		int res = inquiryService.answerUpdateByAnswer(answerInquiry);
		return res;
	}
	
	@RequestMapping("/inquirylist")
	public void inquirylist(HttpSession session, HttpServletRequest res) {
		User user = (User) session.getAttribute("dto1");
		int sendUser = user.getUserno();
		List<Inquiry> list = inquiryService.getListBySendUser(sendUser);
		for(Inquiry m : list) {
			logger.debug("m : {}", m);
		}
		logger.debug("list : {} ", list);
		
		res.setAttribute("list", list);
	}
	
}