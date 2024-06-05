package shop.controller;

import java.net.http.HttpResponse;
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

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.service.face.OrderService;
import user.dto.User;
import util.ShopPaging;

@Controller
@RequestMapping("/order")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService orderService;
	@Autowired HttpSession session;
	
	@RequestMapping("/ordersheet")
	public void ordersheet(@RequestParam(value="basketNo", required = false)String[] orderDatas
			, @RequestParam(value = "quantity", required = false) String[] quantities
			, Model model
			, HttpSession session
			, @RequestParam(value="itemNo", required = false)String STitemNo
			, @RequestParam(value="quantity", required = false)String STquantity) {
		logger.debug("컨트롤러 basketNo : {}", Arrays.toString(orderDatas));
		logger.debug("컨트롤러 quantities : {}",  Arrays.toString(quantities));
		logger.debug("컨트롤러 STitemNo : {}", STitemNo);
		logger.debug("컨트롤러 STquantity : {}", STquantity);
		if(orderDatas != null ) {
			logger.debug("컨트롤러 orderDatas : {}", orderDatas);
			logger.debug("컨트롤러 quantities : {}", quantities);
			logger.debug("컨트롤러 orderDatas : {}", Arrays.toString(orderDatas));
		    List<Basket> baskets = orderService.getItemNosByorderDatas(orderDatas,quantities);
		    logger.debug("baskets : {}",baskets);
			Map<String, Object> orderMap = orderService.userorderProc(baskets);
		    logger.debug("baskets : {}",baskets);
		    logger.debug("orderMap : {}",orderMap);
		    model.addAttribute("baskets", orderMap.get("baskets"));
		    model.addAttribute("items",orderMap.get("items"));
		    model.addAttribute("imgFiles",orderMap.get("imgFiles"));
		    UserOrder userOrder = orderService.makeUserOrder();
		    logger.debug("userOrder check : {}", userOrder);
		    model.addAttribute("userOrder", userOrder);
		    model.addAttribute("orderDatas", orderDatas);
		    model.addAttribute("quantities",quantities);
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
		,@RequestParam(value="quantities", required = false)String quantities
		,UserOrder userOrder
		, Model model
		, @RequestParam(value="itemNo", required = false)String[] STitemNo
		, @RequestParam(value="quantity", required = false)String[] STquantity
		) {
		logger.debug("결제 완료 페이지");
		logger.debug("결제 완료 페이지 userOrder:{}",userOrder);
		logger.debug("결제 완료 페이지 orderDatas:{}",orderDatas);
		logger.debug("결제 완료 페이지 quantities:{}",quantities);
		logger.debug("결제 완료 페이지 STitemNo:{}",STitemNo);
		logger.debug("결제 완료 페이지 STquantity:{}",STquantity);
		User user = (User) session.getAttribute("dto1");
		if(user == null ) {
			return "redirect:/";
		}
		logger.debug("orderDatas 확인 : {}", orderDatas);
		logger.debug("userOrder 확인1 : {}", userOrder);
		
		if(orderDatas == null) {
			logger.debug("!@#@!#!@!orderDatas = null ");
			int resUserOr = orderService.insertUserOrder(userOrder);
			logger.debug("userOrder 확인2 : {}", userOrder);
			
			if(resUserOr > 0) {
				logger.debug("resUserOrresUserOr: {}", resUserOr);
				int orderNo = userOrder.getOrderNo();
				List<OrderItem> orderItems = orderService.getOrderItemsByItemNosQuantitys(STitemNo,STquantity,orderNo);
				List<Item> items= orderService.getItemsByOrderItems(orderItems);
				List<ItemFile> files = orderService.getItemFilesByItemNos(items);
				logger.debug("orderItemsorderItems: {}",orderItems);
				int insertRes = orderService.insertOrderItemByListOrderItem(orderItems);
				orderItems = orderService.getOrderItemsByUserOrder(userOrder);
				if( insertRes > 0) {
					int itemres = orderService.itemReaminReduction(orderItems);
					logger.debug("itemresitemresitemres : {}",itemres);
					int resa= orderService.deleteOverlappingBaskets(orderItems);
					logger.debug("resa : {}",resa);
				}
				logger.debug("orderItems : {}",orderItems);
				logger.debug("userOrder : {}",userOrder);
				logger.debug("items : {}",items);
				logger.debug("imgFiles : {}",files);
				model.addAttribute("orderItems", orderItems);
				model.addAttribute("userOrder", userOrder);
				model.addAttribute("items", items);
				model.addAttribute("imgFiles", files);
				return "/order/orderresult";
				
			}else {
				return "redircet:/";
			}
			
		}
		logger.debug("!@#@!#!@!orderDatas != null ");
		
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
		List<OrderItem> resOrderItems = orderService.insertOrderItems(quantities,orderDatas,userOrder );
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
		
	}//completed
	
	@RequestMapping("/orderresult")
	public void result() {}
	
	@RequestMapping("/history")
	public String history(Model model, ShopPaging shopPaging,
			@RequestParam(defaultValue ="0") int curPage,
			@RequestParam(value="search",required = false) String search) {
		String URL = "/order/history";
		model.addAttribute("URL", URL);
		logger.debug("구매기록");
		logger.debug("curPage : {}", curPage);
		User user = (User) session.getAttribute("dto1");
		//로그인 체크
		if( null == user) {
			logger.debug("구매기록 로그인 x");
			return "redirect:/login";
		}
		//페이징
		shopPaging.setCurPage(curPage);
		int userno = user.getUserno();
		if( null == search || "".equals(search)) {
			shopPaging = orderService.getPagging(shopPaging,userno);
		}else if( search != null && !"".equals(search)) {
			shopPaging.setSearch(search);
			shopPaging = orderService.getPagging(shopPaging,userno);
			shopPaging.setSearch(search);
		}
		logger.debug("Paging : {}", shopPaging);
		if (shopPaging.getTotalCount() == 0){
		model.addAttribute("paging", shopPaging);
		model.addAttribute("curPage", curPage);
		return "order/history";
		}
		model.addAttribute("paging", shopPaging);
		model.addAttribute("curPage", curPage);
		
		List<UserOrder> orders = orderService.selectUserOrderByUser(user,shopPaging);
		List<OrderItem> orderitems = orderService.selectOrderItemsByUserOrders(orders);
		List<Item> items = orderService.selectItemByUserOrderItems(orderitems);
		List<ItemFile> imgFiles = orderService.gettitleImg(orderitems);
		logger.debug("구매기록 user : {}",user);
		logger.debug("구매기록 orders : {}",orders);
		logger.debug("구매기록 orderitems : {}",orderitems);
		logger.debug("구매기록 items : {}",items);
		logger.debug("imgFiles : {}", imgFiles);
		
		model.addAttribute("orders", orders);
		model.addAttribute("orderitems", orderitems);
		model.addAttribute("items", items);
		model.addAttribute("imgFiles", imgFiles);
//		
		
		return null;
	}//history
	
	@RequestMapping("/admin/list")
	public String adminlist(Model model, ShopPaging shopPaging,
			@RequestParam(defaultValue ="0") int curPage,
			@RequestParam(value="search",required = false) String search) {
		logger.debug("관리자 주문 목록");
		String URL = "/order/admin/list";
		model.addAttribute("URL", URL);
		shopPaging.setCurPage(curPage);
		if( search != null) {
			shopPaging.setSearch(search);
		}
		logger.debug("관리자 주문 목록 shopPaging : {}", shopPaging);
		
		shopPaging = orderService.getPagging(shopPaging, 0);
		if( search != null) {
			shopPaging.setSearch(search);
		}
		logger.debug("Paging : {}", shopPaging);
		model.addAttribute("paging", shopPaging);
		model.addAttribute("curPage", curPage);
		
		List<UserOrder> userOrders = orderService.selectUserOrderAll(shopPaging);
		logger.debug("관리자 주문 목록 userOrders : {}", userOrders);
		List<OrderItem> orderitems = orderService.selectOrderItemsByUserOrders(userOrders);
		logger.debug("구매기록 orderitems : {}",orderitems);
		List<Item> items = orderService.selectItemByUserOrderItems(orderitems);
		logger.debug("구매기록 items : {}",items);
		
		model.addAttribute("userOrders", userOrders);
		model.addAttribute("orderitems", orderitems);
		model.addAttribute("items", items);
		
		
		return "/order/admin/list";
	}
	
	@RequestMapping("/admin/ordercancle")
	public void ordercancle(UserOrder userOrder) {
		logger.debug("주문 취소");
		logger.debug("주문 취소 orderNo : {}",userOrder);
		String token = orderService.getToken();
		logger.debug("token : {}", token);
		int res = 0;
		res = orderService.updateUserOrderPayCancle(userOrder,token);
		logger.debug("주문 취소 : {} ",res);
		logger.debug("주문 취소 orderNo : {}",userOrder);
		if( res == 1 ) {
			//성공
			orderService.updateUserOrderorderCancle(userOrder);
		}
		
		
	}
	
	
	
}
