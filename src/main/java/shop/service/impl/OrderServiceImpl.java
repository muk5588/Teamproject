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

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.dao.OrderDao;
import shop.service.face.OrderService;
import user.dto.User;
import vo.ItemVO;
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
		if(user == null) {
			return null;
		}
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
	public List<OrderItem> insertOrderItems(String orderDatas, UserOrder userOrder) {
		  // 장바구니 번호 배열로 변환
	    String[] basketNos = orderDatas.split(",");
	    int[] basketNumbers = new int[basketNos.length];
	    for (int i = 0; i < basketNos.length; i++) {
	        basketNumbers[i] = Integer.parseInt(basketNos[i].trim());
	    }
	    logger.debug("basketNumbers : {}", basketNumbers);
	    
	    // 장바구니 번호로 장바구니 목록 조회
	    List<Basket> baskets = orderDao.getBasketsByBasketNos(basketNumbers);
	    logger.debug("baskets : getBasketsByBasketNos: {}", baskets);
	    
	    //장바구니 목록의 상품 번호 목록 조회
	    List<Item> items = orderDao.getItemByItemNos(baskets);
	    
	    // 주문 항목 목록
	    List<OrderItem> orderItems = new ArrayList<>();
	    
	    // 장바구니 목록을 돌면서 주문 항목 생성
	    for (Basket basket : baskets) {
	        // 이미 추가된 상품인지 확인하기 위한 플래그
	        boolean alreadyAdded = false;
	        
	        // 이미 추가된 주문 항목인지 확인하고, 있을 경우 수량을 증가시킴
	        for (OrderItem orderItem : orderItems) {
	            if (orderItem.getItemNo() == basket.getItemNo()) {
	                orderItem.setOrderQuantity(orderItem.getOrderQuantity() + basket.getQuantity());
	                alreadyAdded = true;
	                logger.debug("orderItem 검사 : {}",orderItem);
	                break;
	            }
	        }
	        
	        // 이미 추가된 상품이 아니라면 OrderItem 객체 생성
	        if (!alreadyAdded) {
	        	for(Item i : items) {
	        		if( basket.getItemNo() == i.getItemNo()) {
			            OrderItem orderItem = new OrderItem();
			            orderItem.setOrderNo(userOrder.getOrderNo());
			            orderItem.setItemNo(basket.getItemNo());
			            orderItem.setItemName(i.getItemName());
			            orderItem.setOrderQuantity(basket.getQuantity());
			            orderItem.setPrice(i.getPrice()); 
			            logger.debug("for 내부 orderItem : {}",orderItem);
			            orderItems.add(orderItem);
	        		}
	        	}
	        }
	    }
	    logger.debug("orderItems : {}",orderItems);
	    int res = 0;
	    // 주문 항목을 데이터베이스에 삽입
	    res = orderDao.insertOrderItems(orderItems);
	    
	    //주문 항목 추가 성공 시 장바구니 목록 삭제
	    int deleteBaskets = orderDao.deleteBasketsByBasketNos(basketNumbers);
	    logger.debug("장바구니 목록 삭제 : {}", deleteBaskets);
	    
	    return orderItems;
	}

	@Override
	public List<ItemFile> gettitleImg(List<OrderItem> resOrderItems) {
		return orderDao.gettitleImg(resOrderItems);
	}

	@Override
	public Map<String, Object> getDatasByitemNoByquantity(int itemNo, int quantity) {
		Map<String, Object> map = new HashMap<String, Object>();
		Item item = orderDao.getItemByItemNo(itemNo);
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		ItemFile itF = orderDao.getitemTitleImg(itemNo);
		List<ItemFile> imgFiles = new ArrayList<ItemFile>();
		imgFiles.add(itF);
		map.put("items", items);
		map.put("imgFiles", imgFiles);
		return map;
	}

	@Override
	public int insertOrderItem(OrderItem orderItem) {
		return orderDao.insertOrderItemByItemNoByquantity(orderItem);
	}

	@Override
	public OrderItem selectByOrderItem(OrderItem orderItem) {
		return orderDao.selectByOrderItem(orderItem);
	}

	@Override
	public List<UserOrder> selectUserOrderByUser(User user) {
		return orderDao.selectUserOrderByUser(user);
	}

	@Override
	public List<OrderItem> selectOrderItemsByUserOrders(List<UserOrder> orders) {
		return orderDao.selectOrderItemsByUserOrders(orders);
	}

	@Override
	public List<ItemVO> selectItemByUserOrderItems(List<OrderItem> orderitems) {
		return orderDao.selectItemByUserOrderItems(orderitems);
	}


	

	
}
