package shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dto.Item;
import util.ShopPaging;

@Repository("ShopDao")
public interface ShopDao {

	public List<Item> getList(ShopPaging shopPaging);

	public int selectCntAll(ShopPaging shopPaging);

	public Item getItemByItemNo(int itemNo);

}
