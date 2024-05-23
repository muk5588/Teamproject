package shop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.dao.BasketDao;
import shop.dao.OrderDao;
import shop.service.face.BasketService;
import user.dto.User;

@Service
public class BasketServiceImpl implements BasketService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired private BasketDao basketDao;
	@Autowired private OrderDao userorderDao;
	@Autowired private HttpSession session;
	
	@Override
	public int insertBasket(Basket basket) {
		return basketDao.insertBasket(basket);
	}

	@Override
	public List<Basket> basketListByUserNo(int userNo) {
		return basketDao.basketListByUserNo(userNo);
	}

	@Override
	public List<Item> itemsByBasketNos(List<Basket> baskets) {
		return basketDao.itemsByBasketNos(baskets);
	}

	@Override
	public List<ItemFile> itemFilesByBasketNos(List<Basket> baskets) {
		return basketDao.itemFilesByBasketNos(baskets);
	}

	@Override
	public Map<String, Object> userorderProc(int[] no) {
		logger.debug("Proc : {} ", no);
		User user = (User) session.getAttribute("dto1");
		if(null == user) {
			return null;
		}
		if(user.getUserno() <=0 ) {
			return null;
		}
		List<Basket> basketsTemp = basketDao.basketListBybasketNos(no);
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
		
		logger.debug("baskets : {}", baskets);
		List<Item> items = basketDao.getItemPriceByItemNos(baskets);
		logger.debug("items : {}", items);
		//회원 주문 객체 생성
		UserOrder userOrder = new UserOrder();
		userOrder.setUserNo(user.getUserno());
		userOrder.setPhone(user.getPhone());
		//총 가격 기본 0 설정
		userOrder.setTotalPrice(0);
		userOrder.setUserName(user.getName());
		userOrder.setAddress(user.getAddress());
		userOrder.setDetailAddress(user.getDetailAddress());
		userOrder.setExtraAddress(user.getExtraAddress());
		userOrder.setPostCode(user.getPostcode());
		//결제금액 0 설정
		userOrder.setParaMount(0);
		logger.debug("userOrder : {}", userOrder);
		//주문상세 정보를 담기 위한 회원주문 객체 Insert
		int res = userorderDao.insertUserorder(userOrder);
		
		//insert 결과 없을 경우 null 반환
		if(res <= 0 ) {
			return null;
		}
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("UserOrder", userOrder);
		List<OrderItem> userOrderDetail = new ArrayList<OrderItem>();
		for(Basket b : baskets) {
			for(Item i : items) {
				if(b.getItemNo() == i.getItemNo()) {
					OrderItem order = new OrderItem();
					order.setOrderNo(userOrder.getOrderNo());
					order.setItemNo(i.getItemNo());
					order.setItemName(i.getItemName());
					order.setOrderQuantity(b.getQuantity()); 
					order.setPrice(( b.getQuantity() * i.getPrice()));
					logger.debug("order : {}", order);
					userOrderDetail.add(order);
				}
			}
		}
		
		returnMap.put("OrderItem",userOrderDetail);
		return returnMap;
		
		
		
		
	}//userorderProc(int[] )

	@Override
	public int deleteBybasketNo(int basketNo) {
		return basketDao.deleteBybasketNo(basketNo);
	}
	
	
	
}
