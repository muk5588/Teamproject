package shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.service.ShopService;
import shop.service.ShopServiceImpl;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ShopService shopService;
	
	@RequestMapping("/main")
	public void shopMain() {
		
	}
	
	
	
}
