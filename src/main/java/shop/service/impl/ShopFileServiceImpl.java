package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Item;
import dto.ItemFile;
import shop.dao.ShopFileDao;
import shop.service.face.ShopFileService;

@Service
public class ShopFileServiceImpl implements ShopFileService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private ShopFileDao shopFileDao;
	@Override
	public List<ItemFile> getTitleImgs() {
		return shopFileDao.getTitleImgs();
	}
	@Override
	public List<ItemFile> getItemFilesByItemNo(int itemNo) {
		return shopFileDao.getItemFilesByItemNo(itemNo);
	}
	
}
