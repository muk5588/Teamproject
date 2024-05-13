package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Item;
import dto.ItemFile;
import shop.dao.AdminShopDao;
import shop.service.face.AdminShopService;
import util.Paging;

@Service
public class AdminShopSerivceImpl implements AdminShopService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired AdminShopDao adminShopDao;
	
	@Override
	public Paging getPaging(int curPage, Paging paging) {
		
		int totalCount = adminShopDao.selectCnt(paging);
		logger.info("totalCount : {}",totalCount);
		Paging pagingres = new Paging(totalCount, curPage);
		
		return pagingres;
	}

	@Override
	public List<Item> selectItems(Paging paging) {
		return adminShopDao.selectItems(paging);
	}

	@Override
	public List<ItemFile> selectTitleImgFile(List<Item> items) {
		return adminShopDao.selectTitleImgFile(items);
	}

	
	
}
