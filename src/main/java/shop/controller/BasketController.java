package shop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.Basket;
import shop.service.face.BasketService;
import user.dto.User;

@Controller
@RequestMapping("/basket")
public class BasketController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private BasketService basketService;
	
	@RequestMapping("/")
	private void basket() {
		logger.debug("장바구니 / ");
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	private int basketInsert(
			@RequestParam("itemNo") int itemNo
			, @RequestParam("quantity") int quantity
			, HttpSession session
			) {
		logger.debug("/basket/insert");
		int res = 0;
		User user = (User) session.getAttribute("dto1");
		logger.debug("user : {}", user);
		if( null != user ) {
			Basket basket = new Basket();
			basket.setItemNo(itemNo);
			basket.setQuantity(quantity);
			basket.setUserNo(user.getUserno());
			logger.debug("basket : {}", basket);
			res = basketService.insertBasket(basket);
			logger.debug("res : {}", res);
		}else {
			logger.debug("res : {}", res);
			return res;
		}
		
		return res; 
	}
	
	@RequestMapping("/userbasket")
	private void userBakset(HttpSession session, Model model) {
		logger.debug("userbasket");
		User user = (User) session.getAttribute("dto1");
		logger.debug("user : {}",user);
		if( user == null) {
			
		}else {
			int userNo = user.getUserno();
			logger.debug("user : {}",user);
			List<Basket> baskets = basketService.basketListByUserNo(userNo);
			logger.debug("baskets : {}",baskets);
		}
		
	}
	
	
}
