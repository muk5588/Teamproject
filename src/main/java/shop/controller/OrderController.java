package shop.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dto.OrderItem;
import dto.UserOrder;
import shop.service.face.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService orderService;
	
	@RequestMapping("/ordersheet")
	private void ordersheet(@RequestParam("res")String orderDatas
			, Model model) {
		logger.debug("orderDatas : {}", orderDatas);
		Map<String, Object> param = orderService.getParam(orderDatas);
		logger.debug("param : {}",param);
		List<OrderItem> orderItems = (List<OrderItem>) param.get("OrderItem");
		logger.debug("orderItems : {}",orderItems);
		UserOrder userOrder = (UserOrder) param.get("UserOrder");
		logger.debug("userOrder : {}",userOrder);
		
		model.addAttribute("orderItems", orderItems);
		model.addAttribute("userOrder", userOrder);
	}
	
	
	
	
	
}
