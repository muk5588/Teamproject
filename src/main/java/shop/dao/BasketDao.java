package shop.dao;

import java.util.List;

import dto.Basket;

public interface BasketDao {

	public int insertBasket(Basket basket);

	public List<Basket> basketListByUserNo(int userNo);

}
