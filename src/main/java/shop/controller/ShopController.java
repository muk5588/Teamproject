package shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Item;
import shop.service.face.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private ShopService shopService;
	
	@RequestMapping("/main")
	public void shopMain() {}
	
	@RequestMapping("/list")
	public void list() {
		List<Item> list = shopService.list();
		
	}
	
}
