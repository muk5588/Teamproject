package shop.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.service.face.OrderService;
import user.dto.User;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService orderService;
	@Autowired HttpSession session;
	
	@RequestMapping("/ordersheet")
	private void ordersheet(@RequestParam("res")String[] orderDatas
			, Model model
			, HttpSession session) {
		logger.debug("컨트롤러 orderDatas : {}", Arrays.toString(orderDatas));
		
	    int[] orderNumbers = orderService.getItemNosByorderDatas(orderDatas);
		Map<String, Object> orderMap = orderService.userorderProc(orderNumbers);
	    
	    model.addAttribute("baskets", orderMap.get("baskets"));
	    model.addAttribute("items",orderMap.get("items"));
	    model.addAttribute("imgFiles",orderMap.get("imgFiles"));
	    UserOrder userOrder = orderService.makeUserOrder();
	    logger.debug("userOrder check : {}", userOrder);
	    model.addAttribute("userOrder", userOrder);
	    model.addAttribute("orderDatas", orderDatas);
	    
	}
	
	@PostMapping("/completed")
	private String orderCompleted(
		HttpServletRequest req
		,String orderDatas
		,UserOrder userOrder
		, Model model
		) {
		logger.debug("결제 완료 페이지");
		User user = (User) session.getAttribute("dto1");
//		logger.debug("orderItemNos 확인 : {}", orderItemNos);
		logger.debug("userOrder 확인1 : {}", userOrder);
		logger.debug("orderDatas 확인 toString: {}", orderDatas.toString());
		logger.debug("orderDatas 확인 : {}", orderDatas);
		userOrder.setUserNo(user.getUserno());
		int resUserOrder = orderService.insertUserOrder(userOrder);
		logger.debug("resUserOrder 확인 : {}", resUserOrder);
		
		if(!(resUserOrder > 0)) {
			return "redircet:/";
			//insertUserOrder 실패시 코드 작성
		}
		
		logger.debug("userOrder 확인2 : {}", userOrder);
		int resOrderItems = orderService.insertOrderItems(orderDatas,userOrder );
		logger.debug("resOrderItems : {}", resOrderItems);
		
		if(!(resOrderItems > 0)) {
			return "redircet:/";
			//insertOrderItems 실패시 코드 작성
		}
		model.addAttribute("userOrder", userOrder);
		
		return "redircet:./orderresult";
		
	}
	
	@RequestMapping("/orderresult")
	private void result() {
		
	}
	
}
