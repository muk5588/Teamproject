package shop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.dao.OrderDao;
import shop.service.face.OrderService;
import user.dto.User;
@Service
public class OrderServiceImpl implements OrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private OrderDao orderDao;
	@Autowired HttpSession session;
	@Autowired HttpServletRequest req;
	@Override
	public int[] getItemNosByorderDatas(String[] orderDatas) {
		int[] orderNumbers = new int[orderDatas.length];
		for (int i = 0; i < orderDatas.length; i++) {
	        orderNumbers[i] = Integer.parseInt(orderDatas[i]);
	    }
	    logger.debug("서비스 orderNumbers : {}", Arrays.toString(orderNumbers));
		return orderNumbers;
	}
	
	@Override
	public Map<String, Object> userorderProc(int[] orderNumbers) {
		logger.debug("Proc : {} ", orderNumbers);
		User user = (User) session.getAttribute("dto1");
		if(null == user) {
			return null;
		}
		if(user.getUserno() <=0 ) {
			return null;
		}
		List<Basket> basketsTemp = orderDao.basketListBybasketNos(orderNumbers);
		logger.debug("basketsTemp : {}", basketsTemp);
		
		//없을 경우 null
		if(basketsTemp == null) {
			return null;
		}
		
		List<Basket> baskets = new ArrayList<Basket>();
		
		//상품의 수량 합산을 위한 Map 객체 생성
		Map<Integer, Integer> itemQuantityMap = new HashMap<>();
		
		//같은 물건의 경우 합산 처리
		for(Basket b : basketsTemp) {
			int itemNo = b.getItemNo();
			int quantity = b.getQuantity();
			
		    // 이미 해당 ITEMNO가 맵에 있는 경우 기존 값과 더함
		    if (itemQuantityMap.containsKey(itemNo)) {
		        int currentQuantity = itemQuantityMap.get(itemNo);
		        itemQuantityMap.put(itemNo, currentQuantity + quantity);
		    } else {
		        // 해당 ITEMNO가 맵에 없는 경우 새로운 키로 추가
		        itemQuantityMap.put(itemNo, quantity);
		    }			
		}
		//결과 Map itemno : quantity 쌍을  List<Basket>에 담기
		for (Map.Entry<Integer, Integer> entry : itemQuantityMap.entrySet()) {
		    int itemNo = entry.getKey();
		    int totalQuantity = entry.getValue();
		    
		    Basket basket = new Basket();
		    basket.setItemNo(itemNo);
		    basket.setQuantity(totalQuantity);
		    
		    baskets.add(basket);
		}
		//장바구니에 담긴 상품 정보 조회
		logger.debug("baskets : {}", baskets);
		List<Item> items = orderDao.getItemByItemNos(baskets);
		logger.debug("items : {}", items);

		//상품의 대표 이미지 조회
	    List<ItemFile> imgFiles = orderDao.getTitleImgs(items);

		
		Map<String, Object>  returnMap = new HashMap<>();
		
		returnMap.put("baskets", baskets);
		returnMap.put("items", items);
		returnMap.put("imgFiles", imgFiles);
		
		return returnMap;
	}

	@Override
	public UserOrder makeUserOrder() {
		User user = (User) session.getAttribute("dto1");
		
		UserOrder userOrder = new UserOrder();
		userOrder.setUserName(user.getName());
		userOrder.setUserNo(user.getUserno());
		userOrder.setPostCode(user.getPostcode());
		userOrder.setAddress(user.getAddress());
		userOrder.setDetailAddress(user.getDetailAddress());
		userOrder.setExtraAddress(user.getExtraAddress());
		userOrder.setPhone(user.getPhone());
		
		return userOrder;
	}

	@Override
	public int insertUserOrder(UserOrder userOrder) {
		return orderDao.insertUserOrder(userOrder);
	}

	@Override
	public int insertOrderItems(String orderDatas, UserOrder userOrder) {
//		String[] orderDataStrings = orderDatas.split(",");
//		int[] orderDataInts = new int[orderDataStrings.length];
//		for (int i = 0; i < orderDataStrings.length; i++) {
//		    orderDataInts[i] = Integer.parseInt(orderDataStrings[i].trim()); // trim() 메서드를 사용하여 앞뒤 공백을 제거합니다.
//		}
//		
//		List<Basket> basketsTemp = orderDao.basketListBybasketNos(orderDataInts);
//		//상품의 수량 합산을 위한 Map 객체 생성
//		Map<Integer, Integer> itemQuantityMap = new HashMap<>();
//		List<Basket> baskets = new ArrayList<Basket>();
//		
//		//같은 물건의 경우 합산 처리
//		for(Basket b : basketsTemp) {
//			int itemNo = b.getItemNo();
//			int quantity = b.getQuantity();
//			
//		    // 이미 해당 ITEMNO가 맵에 있는 경우 기존 값과 더함
//		    if (itemQuantityMap.containsKey(itemNo)) {
//		        int currentQuantity = itemQuantityMap.get(itemNo);
//		        itemQuantityMap.put(itemNo, currentQuantity + quantity);
//		    } else {
//		        // 해당 ITEMNO가 맵에 없는 경우 새로운 키로 추가
//		        itemQuantityMap.put(itemNo, quantity);
//		    }			
//		}
//		//결과 Map itemno : quantity 쌍을  List<Basket>에 담기
//		for (Map.Entry<Integer, Integer> entry : itemQuantityMap.entrySet()) {
//		    int itemNo = entry.getKey();
//		    int totalQuantity = entry.getValue();
//		    
//		    Basket basket = new Basket();
//		    basket.setItemNo(itemNo);
//		    basket.setQuantity(totalQuantity);
//		    
//		    baskets.add(basket);
//		}
//		
//		logger.debug("baskets : {}", baskets);
//		List<Item> items = orderDao.getItemByItemNos(baskets);
//		List<OrderItem> userOrderDetail = new ArrayList<OrderItem>();
//		for(Basket b : baskets) {
//			for(Item i : items) {
//				if(b.getItemNo() == i.getItemNo()) {
//					OrderItem order = new OrderItem();
//					order.setOrderNo(userOrder.getOrderNo());
//					order.setItemNo(i.getItemNo());
//					order.setItemName(i.getItemName());
//					order.setOrderQuantity(b.getQuantity()); 
//					order.setPrice(i.getPrice());
//					logger.debug("order : {}", order);
//					userOrderDetail.add(order);
//				}
//			}
//		}
//		
//		model.addAttribute("orderItems", userOrderDetail);
//		
//		return orderDao.insertOrderItems(userOrderDetail);
		return 0;
	}
	

	
}
