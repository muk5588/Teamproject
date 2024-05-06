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

@Service
public class ShopServiceImpl implements ShopService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ShopDao shopDao;
	
	@Override
	public Paging getPagging(Paging shopPaging) {
		
		int totalCount = shopDao.selectCntAll(shopPaging);
		
		Paging pagingres = new Paging(totalCount, shopPaging.getCurPage());
		return pagingres;
	}
	
	@Override
	public List<Item> list() {
		return shopDao.getList();
	}

	@Override
	public Item getItemByItemNo(int itemNo) {
		return shopDao.getItemByItemNo(itemNo);
	}
	
}
