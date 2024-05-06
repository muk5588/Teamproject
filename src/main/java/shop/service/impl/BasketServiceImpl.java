package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Basket;
import shop.dao.BasketDao;
import shop.service.face.BasketService;

@Service
public class BasketServiceImpl implements BasketService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired private BasketDao basketDao;
	
	@Override
	public int insertBasket(Basket basket) {
		return basketDao.insertBasket(basket);
	}

	@Override
	public List<Basket> basketListByUserNo(int userNo) {
		return basketDao.basketListByUserNo(userNo);
	}
	
	
	
}
