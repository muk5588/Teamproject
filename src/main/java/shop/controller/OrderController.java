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

import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.service.face.OrderService;
import user.dto.User;
import vo.ItemVO;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService orderService;
	@Autowired HttpSession session;
	
	@RequestMapping("/ordersheet")
	public void ordersheet(@RequestParam(value="res", required = false)String[] orderDatas
			, Model model
			, HttpSession session
			, @RequestParam(value="itemNo", required = false)String STitemNo
			, @RequestParam(value="quantity", required = false)String STquantity) {
		logger.debug("컨트롤러 itemNo : {}", STitemNo);
		logger.debug("컨트롤러 quantity : {}", STquantity);
		logger.debug("컨트롤러 orderDatas : {}", Arrays.toString(orderDatas));
		if(orderDatas != null && STitemNo == null && STquantity == null) {
		    int[] orderNumbers = orderService.getItemNosByorderDatas(orderDatas);
			Map<String, Object> orderMap = orderService.userorderProc(orderNumbers);
		    
		    model.addAttribute("baskets", orderMap.get("baskets"));
		    model.addAttribute("items",orderMap.get("items"));
		    model.addAttribute("imgFiles",orderMap.get("imgFiles"));
		    UserOrder userOrder = orderService.makeUserOrder();
		    logger.debug("userOrder check : {}", userOrder);
		    model.addAttribute("userOrder", userOrder);
		    model.addAttribute("orderDatas", orderDatas);
		}else if(orderDatas == null && STquantity != null && STitemNo != null) {
			int itemNo , quantity;
			itemNo = Integer.parseInt(STitemNo);
			quantity = Integer.parseInt(STquantity);
			logger.debug("else If 부분으로 빠졌음!!!!!!!!!!");
			Map<String, Object> orderMap = orderService.getDatasByitemNoByquantity(itemNo,quantity);
			logger.debug("orderMap : getDatasByitemNoByquantity check : {}", orderMap);
			model.addAttribute("quantity", quantity);
			UserOrder userOrder = orderService.makeUserOrder();
			if(userOrder != null) {
				logger.debug("userOrder check : {}", userOrder);
				model.addAttribute("userOrder",userOrder);
			}
			
			model.addAttribute("items",orderMap.get("items"));
			model.addAttribute("imgFiles",orderMap.get("imgFiles"));
			
		}
	    
		
	}
	
	@PostMapping("/completed")
	public String orderCompleted(
		HttpServletRequest req
		,@RequestParam(value="orderDatas", required = false)String orderDatas
		,UserOrder userOrder
		, Model model
		, @RequestParam(value="itemNo", required = false)String STitemNo
		, @RequestParam(value="quantity", required = false)String STquantity
		) {
		logger.debug("결제 완료 페이지");
		logger.debug("결제 완료 페이지 STitemNo:{}",STitemNo);
		logger.debug("결제 완료 페이지 STquantity:{}",STquantity);
		User user = (User) session.getAttribute("dto1");
		if(user == null ) {
			return "redirect:/";
		}
		logger.debug("userOrder 확인1 : {}", userOrder);
		
		//상품 단일 구매의 경우
		if(orderDatas == null) {
			int resUserOr = orderService.insertUserOrder(userOrder);
			
			if(resUserOr > 0) {
				logger.debug("resUserOrresUserOr: {}", resUserOr);
				OrderItem orderItem = new OrderItem();
				int itemNo,quantity;
				itemNo = Integer.parseInt(STitemNo);
				quantity = Integer.parseInt(STquantity);
				orderItem.setItemNo(itemNo);
				orderItem.setOrderQuantity(quantity);
				orderItem.setOrderNo(userOrder.getOrderNo());
				int resa= orderService.insertOrderItem(orderItem);
				logger.debug("orderItem : {}",orderItem);
				if(resa > 0) {
					orderItem = orderService.selectByOrderItem(orderItem);
					logger.debug("orderItem : {}",orderItem);
				}
				model.addAttribute("userOrder", userOrder);
				model.addAttribute("orderItem", orderItem);
				logger.debug("orderItem : {}",orderItem);
				return "/order/orderresult";
				
			}else {
				return "redircet:/";
			}
			
		}
		
//		logger.debug("orderItemNos 확인 : {}", orderItemNos);
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
		List<OrderItem> resOrderItems = orderService.insertOrderItems(orderDatas,userOrder );
		logger.debug("resOrderItems : {}", resOrderItems);
		
		if( null == resOrderItems ) {
			return "redircet:/";
			//insertOrderItems 실패시 코드 작성
		}
		
		
		model.addAttribute("userOrder", userOrder);
		logger.debug("userOrder : {}", userOrder);
		model.addAttribute("orderItems", resOrderItems);
		logger.debug("orderItems : {}", resOrderItems);
		
		List<ItemFile> imgFiles = orderService.gettitleImg(resOrderItems);
		logger.debug("imgFiles : {}", imgFiles);
		model.addAttribute("imgFiles", imgFiles);
		
		return "/order/orderresult";
		
	}
	
	@RequestMapping("/orderresult")
	public void result() {}
	
	@RequestMapping("/history")
	public String history(Model model) {
		logger.debug("구매기록");
		User user = (User) session.getAttribute("dto1");
		logger.debug("구매기록 user : {}",user);
		if( null == user) {
			logger.debug("구매기록 로그인 x");
			return "redircet/login";
		}
		List<UserOrder> orders = orderService.selectUserOrderByUser(user);
		logger.debug("구매기록 orders : {}",orders);
		List<OrderItem> orderitems = orderService.selectOrderItemsByUserOrders(orders);
		logger.debug("구매기록 orderitems : {}",orderitems);
		List<ItemVO> items = orderService.selectItemByUserOrderItems(orderitems);
		logger.debug("구매기록 items : {}",items);
		
//		model.addAttribute("orders", orders);
//		model.addAttribute("orderitems", orderitems);
//		model.addAttribute("items", items);
//		
		return null;
	}
	
	
	
}
