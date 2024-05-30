package shop.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import shop.dao.OrderDao;
import shop.service.face.OrderService;
import user.dto.User;
import util.ShopPaging;
@Service
public class OrderServiceImpl implements OrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private OrderDao orderDao;
	@Autowired HttpSession session;
	@Autowired HttpServletRequest req;
	
	private String imp_key = "1267261348683316";
	private String imp_secret = "raGfKIOia3gSi4pnjct8TNHvFrsW6JOyc11ckSRfM87vhJI0EulIZ44aSSXnu6a042r9j70mFsNa6P9v";
	
	@Override
	public List<Basket> getItemNosByorderDatas(String[] orderDatas, String[] quantities) {
        List<Basket> basketList = new ArrayList<>();
        if (orderDatas != null && quantities != null) {
            for (int i = 0; i < Math.min(orderDatas.length, quantities.length); i++) {
            	Basket tempBasket = new Basket();
                int basketNo = Integer.parseInt(orderDatas[i]);
                logger.debug("basketNo : {}",basketNo);
                int quantity = Integer.parseInt(quantities[i]);
                logger.debug("quantity : {}",quantity);
                tempBasket.setBasketNo(basketNo);
                tempBasket.setQuantity(quantity);
                basketList.add(tempBasket);
            }
        }
        List<Basket> tempList = orderDao.selectBasketByBaskets(basketList);
	    logger.debug("서비스 basketList : {}", basketList);
	    for (int i = 0; i < basketList.size(); i++) {
	        for (Basket basket : tempList) {
	            if (basket.getBasketNo() == tempList.get(i).getBasketNo()) {
	                basket.setQuantity(basketList.get(i).getQuantity());
	             // basketNo가 같으면 quantity 덮어씌우고 break
	                break;
	            }
	        }
	    }
		return tempList;
	}
	
	@Override
//	public Map<String, Object> userorderProc(int[] orderNumbers) {
	public Map<String, Object> userorderProc(List<Basket> baskets) {
	    logger.debug("Proc baskets : {}", baskets);
	    User user = (User) session.getAttribute("dto1");
	    if (null == user || user.getUserno() <= 0) {
	        return null;
	    }
	    
	    // 수량 합산을 위한 Map 객체 생성
	    Map<Integer, Integer> itemQuantityMap = new HashMap<>();
	    // 같은 물건의 경우 수량 합산 처리
	    for (Basket basket : baskets) {
	        int itemNo = basket.getItemNo();
	        int quantity = basket.getQuantity();
	        
	        // 이미 해당 ITEMNO가 맵에 있는 경우 기존 값과 더함
	        if (itemQuantityMap.containsKey(itemNo)) {
	            int currentQuantity = itemQuantityMap.get(itemNo);
	            itemQuantityMap.put(itemNo, currentQuantity + quantity);
	        } else {
	            // 해당 ITEMNO가 맵에 없는 경우 새로운 키로 추가
	            itemQuantityMap.put(itemNo, quantity);
	        }			
	    }
	    logger.debug("itemQuantityMap : {}",itemQuantityMap);
	    List<Basket> mergedBaskets = new ArrayList<>();
	    
	    // 결과 Map itemno : quantity 쌍을 List<Basket>에 담기
	    for (Map.Entry<Integer, Integer> entry : itemQuantityMap.entrySet()) {
	        int itemNo = entry.getKey();
	        int totalQuantity = entry.getValue();
	        
	        Basket basket = new Basket();
	        basket.setItemNo(itemNo);
	        basket.setQuantity(totalQuantity);
	        
	        mergedBaskets.add(basket);
	    }
	    
	    // 장바구니에 담긴 상품 정보 조회
	    logger.debug("mergedBaskets : {}", mergedBaskets);
	    List<Item> items = orderDao.getItemByItemNos(mergedBaskets);
	    logger.debug("items : {}", items);

	    // 상품의 대표 이미지 조회
	    List<ItemFile> imgFiles = orderDao.getTitleImgs(items);
	    
	    Map<String, Object> returnMap = new HashMap<>();
	    
	    returnMap.put("baskets", mergedBaskets);
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
			            orderDao.itemReaminReduction(orderItem);
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
	public List<UserOrder> selectUserOrderByUser(User user, ShopPaging shopPaging) {
		return orderDao.selectUserOrderByUser(user, shopPaging);
	}

	@Override
	public List<OrderItem> selectOrderItemsByUserOrders(List<UserOrder> orders) {
		return orderDao.selectOrderItemsByUserOrders(orders);
	}

	@Override
	public List<Item> selectItemByUserOrderItems(List<OrderItem> orderitems) {
		return orderDao.selectItemByUserOrderItems(orderitems);
	}

	@Override
	public ShopPaging getPagging(ShopPaging shopPaging, int userno) {
		
		int totalCount = orderDao.selectCntByUserNo(shopPaging, userno);
		logger.debug("$$$$totalCount : {}",totalCount);
		ShopPaging pagingres = new ShopPaging(totalCount, shopPaging.getCurPage());
		return pagingres;
	}

	@Override
	public List<UserOrder> selectUserOrderAll(ShopPaging shopPaging) {
		return orderDao.selectUserOrderAll(shopPaging);
	}

	@Override
	public int updateUserOrderPayCancle(UserOrder userOrder, String token) {
	    userOrder = orderDao.selectUserOrderByOrderNo(userOrder.getOrderNo());
	    
	    // IAMPORT API에 전달할 요청 본문 생성
	    String requestBody = "{\"imp_uid\":\"" + userOrder.getImpUid() + "\",\"amount\":\"" + userOrder.getParaMount() + "\"}";
	    
	    // IAMPORT API에 전달할 URL 생성
	    String url = "https://api.iamport.kr/payments/cancel";
	    
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(url))
	            .header("Content-Type", "application/json")
	            .header("Authorization", "Bearer " + token) // 헤더에 인증 토큰 포함
	            .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
	            .build();
	    
	    try {
	        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	        logger.debug("response : {}", response.body());
	        Gson gson = new Gson();
	        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
	        int code = jsonResponse.get("code").getAsInt();
	        logger.debug("code: {}", code); // 추출한 code 값을 디버그 메시지에 출력
	        // 응답 코드에 따라 처리
	        if (response.statusCode() == 200) {
	        	
	        	if( code == 0) {
		            // 처리 성공
		            return 1;
	            }else {
	            	return 0;
	            }
	        } else {
	            // 처리 실패
	            logger.error("Failed to cancel payment: {}", response.body());
	            return 0;
	        }
	    } catch (Exception e) {
	        logger.error("Exception occurred while cancelling payment: {}", e.getMessage());
	        return 0;
	    }
	}


	@Override
	public String getToken() {
		String param = "";
		
		param += "imp_key:" +imp_key;
		param += ",imp_secret:" +imp_secret;
		logger.debug("param : {}", param);
		HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.iamport.kr/users/getToken"))
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString("{\"imp_key\":\"1267261348683316\",\"imp_secret\":\"raGfKIOia3gSi4pnjct8TNHvFrsW6JOyc11ckSRfM87vhJI0EulIZ44aSSXnu6a042r9j70mFsNa6P9v\"}"))			    .build();
			HttpResponse<String> response = null;
			try {
				response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
				logger.debug("response.body()response.body():{}" ,response.body());

		        // JSON 파싱
		        JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response.body());
		        if ((long) jsonResponse.get("code") == 0) {
		            JSONObject responseBody = (JSONObject) jsonResponse.get("response");
		            return (String) responseBody.get("access_token");
		        } else {
		            logger.error("Failed to get access token: {}", jsonResponse.get("message"));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return null;
		}

	@Override
	public void updateUserOrderorderCancle(UserOrder userOrder) {
		orderDao.updateUserOrderorderCancle(userOrder);
	}

	@Override
	public List<OrderItem> getOrderItemsByItemNosQuantitys(String[] sTitemNo, String[] sTquantity, int orderNo) {
		List<OrderItem> orderItems = new ArrayList<>();
		List<Item> items = orderDao.getItemsBySTitemNo(sTitemNo);
		logger.debug("getOrderItemsByItemNosQuantitys : items :{} ",items);
		int itemNo = 0;
        if (sTitemNo != null && sTquantity != null) {
            for (int i = 0; i < Math.min(sTitemNo.length, sTquantity.length); i++) {
            	itemNo = Integer.parseInt(sTitemNo[i]);
            	for(Item it : items) {
            		if(itemNo == it.getItemNo()) {
		            	OrderItem orderItem = new OrderItem();
		                logger.debug("basketNo : {}",itemNo);
		                int orderQuantity = Integer.parseInt(sTquantity[i]);
		                logger.debug("quantity : {}",orderQuantity);
		                orderItem.setItemNo(itemNo);
		                orderItem.setItemName(it.getItemName());
		                orderItem.setOrderQuantity(orderQuantity);
		                orderItem.setOrderNo(orderNo);
		                orderItem.setPrice(it.getPrice());
		                orderItems.add(orderItem);
            		}
            	}
            }
        }
		return orderItems;
	}

	@Override
	public int deleteOverlappingBaskets(List<OrderItem> orderItems) {
	    User user = (User) session.getAttribute("dto1");
	    int res=0;
	    int userNo = user.getUserno();
	    List<Basket> baskets = orderDao.getBasketsByUserNo(userNo);
	    List<Basket> overlappingBaskets = new ArrayList<>();
	    if (orderItems != null && baskets != null) {
	        for (OrderItem orderItem : orderItems) {
	            for (Basket basket : baskets) {
	                if (orderItem.getItemNo() == basket.getItemNo()) {
	                    overlappingBaskets.add(basket);
	                    break; 
	                }
	            }
	        }
	    }
	    logger.debug("Overlapping baskets: {}", overlappingBaskets);
	    if( !overlappingBaskets.isEmpty() ) {
	    	res = orderDao.deleteOverlappingBaskets(overlappingBaskets);
	    }
	    return res;
	}

	@Override
	public int insertOrderItemByListOrderItem(List<OrderItem> orderItems) {
		return orderDao.insertOrderItemByListOrderItem(orderItems);
	}

	@Override
	public List<Item> getItemsByOrderItems(List<OrderItem> orderItems) {
		return orderDao.getItemsByOrderItems(orderItems);
	}

	@Override
	public List<ItemFile> getItemFilesByItemNos(List<Item> items) {
		return orderDao.getItemFilesByItemNos(items);
	}

	@Override
	public List<OrderItem> getOrderItemsByUserOrder(UserOrder userOrder) {
		return orderDao.getOrderItemsByUserOrder(userOrder);
	}

	@Override
	public int itemReaminReduction(List<OrderItem> orderItems) {
		int res = 0;
		for(OrderItem o : orderItems) {
			orderDao.itemReaminReduction(o);
			res++;
		}
		return res;
	}






	

	
}
