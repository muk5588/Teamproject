package message.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Message;
import message.service.MessageService;
import user.dto.User;
import util.Paging;

@Controller
@RequestMapping("/message")
public class MessageController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageService messageService;
	
	@RequestMapping("/")
	public String message() {
		return "redirect:/message/list";
	}
	
	@ResponseBody
	@RequestMapping("/send")
	public int sendMessage(String touser, String content, HttpSession session) {
		logger.debug("/send ajax 들어옴");
		logger.debug("touser : {}",touser);
		logger.debug("content : {}",content);
		//
		int res = 0;
		if( content == null || "".equals(content)) {
//			return null;
		}
		if( touser != null && !"".equals(touser)) {
			User toUser = messageService.getUserByNickName(touser);
			User user = (User) session.getAttribute("dto1");
			int sendUser = user.getUserno();
			Message message = new Message();
			message.setSendUserNo(sendUser);
			message.setToUserNo(toUser.getUserno());
			message.setContent(content);
			
			res =messageService.insertMessage(message);
		}
		return res;
	}
	
	@RequestMapping("/list")
	public String list(HttpSession session, HttpServletRequest res,
			@RequestParam(name="curPage", defaultValue = "0")int curPage
			,@RequestParam(name = "search", required = false)String search) {
		User user = (User) session.getAttribute("dto1");
		if( user == null) {
			logger.debug("로그인 상태가 아님!");
		}
		int userNo = user.getUserno();
		String URL = "/message/list";
		res.setAttribute("URL", URL);
		Paging paging = new Paging();
		if( search != null) {
			paging.setSearch(search);
		}
		paging = messageService.messagePaging(paging,curPage,userNo);
		if( search != null) {
			paging.setSearch(search);
		}
		List<Message> list = messageService.getListByUserno(userNo,paging);
		for(Message m : list) {
			logger.debug("m : {}", m);
		}
		logger.debug("list : {} ", list);
		logger.debug("paging : {}", paging);
		
		res.setAttribute("paging", paging);
		res.setAttribute("list", list);
		return "/message/list";
	}
	@RequestMapping("/sendForm")
	public void sendForm() {}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(
			@RequestParam("messageNo") String[] param
			) {
		ArrayList<Integer> messageNo = new ArrayList<Integer>();
		int temp = 0;
		for(int i = 0; param.length >i; i++) {
			temp = Integer.parseInt(param[i]);
			messageNo.add(temp);
		}
		logger.debug("messageNo : {}", messageNo);
		int res = messageService.deleteByMessageNo(messageNo);
		
		return res;
		
	}
	
	@ResponseBody
	@RequestMapping("/saveProc")
	public int saveProc(@RequestParam("messageNo")int messageNo , @RequestParam("check")boolean check) {
		logger.debug("messageNo : {}", messageNo);
		logger.debug("check : {}", check);
		String save = "N";
		if( check ) {
			save = "Y";
		}else if( check ) {
			save = "N";
		}
		Message saveMessage = new Message();
		saveMessage.setSave(save);
		saveMessage.setMessageNo(messageNo);
		int res = messageService.saveUpdateBySave(saveMessage);
		return res;
	}
	
	@RequestMapping("/sendlist")
	public void sendlist(HttpSession session, HttpServletRequest res
			,@RequestParam(name="curPage", defaultValue = "0")int curPage
			,@RequestParam(name = "search", required = false)String search) {
		String URL = "/message/sendlist";
		res.setAttribute("URL", URL);
		Paging paging = new Paging();
		User user = (User) session.getAttribute("dto1");
		if( user == null) {
			logger.debug("로그인 상태가 아님!");
		}
		int userNo = user.getUserno();
		if( search != null) {
			paging.setSearch(search);
		}
		paging = messageService.messageSendUserPaging(paging,curPage,userNo);
		if( search != null) {
			paging.setSearch(search);
		}
		int sendUser = user.getUserno();
		List<Message> list = messageService.getListBySendUser(sendUser,paging);
		for(Message m : list) {
			logger.debug("m : {}", m);
		}
		logger.debug("list : {} ", list);
		logger.debug("paging : {} ", paging);
		res.setAttribute("paging", paging);
		res.setAttribute("list", list);
	}
	
	@ResponseBody
	@RequestMapping("/readProc")
	public int readProc(@Param("messageNo")int messageNo) {
		logger.debug("읽음 체크");
		logger.debug("읽음 체크 : messageNo : {}", messageNo);
		int res = 0; 
		res = messageService.readChk(messageNo);
		return res;
	}
	
}
