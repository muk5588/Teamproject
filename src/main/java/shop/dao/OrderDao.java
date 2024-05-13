package shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;

public interface OrderDao {

	public int insertUserorder(UserOrder userOrder);

	public int insertOrderItem(OrderItem order);

	public List<Basket> basketListBybasketNos(@Param("basketNos")int[] orderNumbers);

	public List<Item> getItemByItemNos(@Param("baskets")List<Basket> baskets);

	public List<ItemFile> getTitleImgs(@Param("items")List<Item> items);

	public int insertUserOrder(UserOrder userOrder);

	public int insertOrderItems(@Param("orderItems")List<OrderItem> userOrderDetail);

	public List<Basket> getBasketsByBasketNos(@Param("basketNos")int[] basketNumbers);

	public int deleteBasketsByBasketNos(@Param("basketNos")int[] basketNumbers);

	public List<ItemFile> gettitleImg(@Param("orderItems")List<OrderItem> resOrderItems);

	
}
