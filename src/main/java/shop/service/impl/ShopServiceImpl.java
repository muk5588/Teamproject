package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Item;
import shop.dao.ShopDao;
import shop.service.face.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ShopDao shopDao;
	
	@Override
	public List<Item> list() {
		return null;
	}
	
}
