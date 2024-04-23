package QandA.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import QandA.service.QandAService;

@Controller
@RequestMapping("/qanda")
public class QandAController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private QandAService qandaService;
	
	@RequestMapping("/list")
	public void shopMain() {
		
	}
	
	
	
	
}