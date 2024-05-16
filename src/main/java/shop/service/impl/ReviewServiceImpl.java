package shop.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.dao.ReviewDao;
import shop.service.face.ReviewService;
import vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ReviewDao reviewDao;
	
	@Override
	public List<ReviewVO> selectByItemNo(int itemNo) {
		return reviewDao.selectByItemNo(itemNo);
	}
	
	
	
}
