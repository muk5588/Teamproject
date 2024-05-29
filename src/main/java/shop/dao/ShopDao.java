package shop.dao;

import java.util.List;

import dto.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.Item;
import user.dto.User;
import util.Paging;
import util.ShopPaging;

@Repository("ShopDao")
public interface ShopDao {

	public List<Item> getList(ShopPaging shopPaging);

	public int selectCntAll(ShopPaging shopPaging);

	public Item getItemByItemNo(int itemNo);


	public int countMyOrderByItemNo(int itemNo);
}
