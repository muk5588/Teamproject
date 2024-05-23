package shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Basket;
import dto.Item;
import dto.ItemFile;

public interface BasketDao {

	public int insertBasket(Basket basket);

	public List<Basket> basketListByUserNo(int userNo);

	public List<Item> itemsByBasketNos(@Param("baskets") List<Basket> baskets);

	public List<ItemFile> itemFilesByBasketNos(@Param("baskets") List<Basket> baskets);

	public List<Basket> basketListBybasketNos(@Param("basketNos")int[] no);

	public List<Item> getItemPriceByItemNos(@Param("baskets")List<Basket> baskets);

	public int deleteBybasketNo(int basketNo);

}
