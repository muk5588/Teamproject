package message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/send")
	public void sendMessage(String touser, String content) {
		logger.debug("/send ajax 들어옴");
		logger.debug("touser : {}",touser);
		logger.debug("content : {}",content);
		
		User toUser = messageService.getUserByNickName(touser);
		
	}
	
}
