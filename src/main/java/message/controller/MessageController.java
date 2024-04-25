package message.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Message;
import message.service.MessageService;
import user.dto.User;

@Controller
@RequestMapping("/message")
public class MessageController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageService messageService;
	
	@RequestMapping("/")
	public String message() {
		return "/message/main";
	}
	
	@ResponseBody
	@RequestMapping("/send")
	public int sendMessage(String touser, String content, HttpSession session) {
		logger.debug("/send ajax 들어옴");
		logger.debug("touser : {}",touser);
		logger.debug("content : {}",content);
		
		int res = 0;
		if( content == null || "".equals(content)) {
//			return null;
		}
		if( touser != null && !"".equals(touser)) {
			User toUser = messageService.getUserByNickName(touser);
			int sendUser = (int) session.getAttribute("loginno");
			Message message = new Message();
			message.setSendUserNo(sendUser);
			message.setToUserNo(toUser.getUserno());
			message.setContent(content);
			
			res =messageService.insertMessage(message);
		}
		return res;
	}
	
//	@RequestMapping("list")
//	public void list() {
//		List<Message> list = messageService.getListByUserno();
//	}
//	
}
