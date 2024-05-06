package shop.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dto.Item;
import util.Paging;

@Repository("ShopDao")
public interface ShopDao {

	public List<Item> getList();

	public int selectCntAll(Paging shopPaging);

	public Item getItemByItemNo(int itemNo);

}
