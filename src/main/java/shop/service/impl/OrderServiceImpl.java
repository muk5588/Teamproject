package shop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.OrderItem;
import dto.UserOrder;
import shop.dao.OrderDao;
import shop.service.face.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private OrderDao orderDao;
	
	@Override
	public Map<String, Object> getParam(String orderDatas) {
	    if (null == orderDatas) {
	        return null;
	    }
	    
	    ObjectMapper objectMapper = new ObjectMapper();
	    Map<String, Object> resMap = new HashMap<>();
	    try {
	        resMap = objectMapper.readValue(orderDatas, new TypeReference<Map<String,Object>>(){});
	        logger.debug("resMap : {}", resMap);
	        
	        // UserOrder와 OrderItem 객체로 직접 변환
	        UserOrder userOrder = objectMapper.convertValue(resMap.get("UserOrder"), UserOrder.class);
	        List<OrderItem> orderItems = objectMapper.convertValue(resMap.get("OrderItem"), new TypeReference<List<OrderItem>>(){});
	        
	        // 변환된 객체를 다시 맵에 담아서 반환
	        Map<String, Object> result = new HashMap<>();
	        result.put("UserOrder", userOrder);
	        result.put("OrderItem", orderItems);
	        
	        return result;
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	    }
	    
	    return resMap;
	}

	
}
