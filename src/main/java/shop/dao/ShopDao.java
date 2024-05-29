package shop.dao;

import dto.Item;
import org.springframework.stereotype.Repository;
import util.ShopPaging;

import java.util.List;
import java.util.Map;

@Repository("ShopDao")
public interface ShopDao {

	public List<Item> getList(ShopPaging shopPaging);

	public int selectCntAll(ShopPaging shopPaging);

	public Item getItemByItemNo(int itemNo);

	public int countMyOrderByItemNo(Map<Object, String> map);
}
