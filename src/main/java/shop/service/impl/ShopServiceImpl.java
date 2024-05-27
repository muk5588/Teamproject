package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Item;
import shop.dao.ShopDao;
import shop.service.face.ShopService;
import util.Paging;
import util.ShopPaging;

@Service
public class ShopServiceImpl implements ShopService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ShopDao shopDao;
	
	@Override
	public ShopPaging getPagging(ShopPaging shopPaging) {
		
		int totalCount = shopDao.selectCntAll(shopPaging);
		
		ShopPaging pagingres = new ShopPaging(totalCount, shopPaging.getCurPage());
		return pagingres;
	}
	
	@Override
	public List<Item> list(ShopPaging shopPaging) {
		return shopDao.getList(shopPaging);
	}

	@Override
	public Item getItemByItemNo(int itemNo) {
		return shopDao.getItemByItemNo(itemNo);
	}
	
}
