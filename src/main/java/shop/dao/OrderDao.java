package shop.dao;

import dto.OrderItem;
import dto.UserOrder;

public interface OrderDao {

	public int insertUserorder(UserOrder userOrder);

	public int insertOrderItem(OrderItem order);
	
	
}
