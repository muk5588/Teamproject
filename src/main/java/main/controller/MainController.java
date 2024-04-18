package main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/main")
	public void main() {}
	
	
	
	
}
