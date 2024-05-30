package shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import shop.service.face.BasketService;
import user.dto.User;

@Controller
@RequestMapping("/basket")
public class BasketController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired private BasketService basketService;
	
	@RequestMapping("/")
	public void basket() {
		logger.debug("장바구니/ ");
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public int basketInsert(
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
	public String userBakset(HttpSession session, Model model) {
		logger.debug("userbasket");
		User user = (User) session.getAttribute("dto1");
		logger.debug("user : {}",user);
		if( user == null) {
			return "redirect:/login";
		}
		int userNo = user.getUserno();
		logger.debug("user : {}",user);
		//장바구니 정보
		List<Basket> baskets = basketService.basketListByUserNo(userNo);
		logger.debug("baskets : {}",baskets);
		//장바구니 정보가 없을 경우 return
		if(baskets == null || baskets.isEmpty()) {
			return "/user/userbasket";
		}
		//상품 정보
		List<Item> items = basketService.itemsByBasketNos(baskets);
		logger.debug("items : {}",items);
		//상품 이미지
		List<ItemFile> itemFiles = basketService.itemFilesByBasketNos(baskets);
		logger.debug("itemFiles : {}",itemFiles);
		
		
		model.addAttribute("itemFiles", itemFiles);
		model.addAttribute("baskets", baskets);
		model.addAttribute("items", items);
		
		return "/user/userbasket";
	}
	
	@ResponseBody
	@PostMapping("/buyBasket")
	public List<Basket> buyBasket(@RequestBody(required = false) List<Basket> baskets
			, Model model) {
		if( baskets == null || baskets.isEmpty()) {
			return null;
		}
		logger.debug("Ajax buyBasket : {}", baskets);
//		Map<String, Object> orderMap = basketService.userorderProc(no);
//		orderMap.put("basketNos", no);
//		logger.debug("orderMap : {}",orderMap);
//		model.addAttribute("orderMap", orderMap);
        return baskets;
//        return null;
	}
	
	@ResponseBody
	@RequestMapping("/deleteBasket")
	public int deleteBasket(int basketNo) {
		logger.debug("basketNo : {}",basketNo);
		int res = 0;
		res = basketService.deleteBybasketNo(basketNo);
		return res;
	}
	
	
}
