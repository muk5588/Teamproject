package shop.service.impl;

import java.util.List;

import dto.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Review;
import shop.dao.ReviewDao;
import shop.service.face.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired ReviewDao reviewDao;
	
	@Override
	public List<Review> selectByItemNo(int itemNo) {
		return reviewDao.selectByItemNo(itemNo);
	}

	@Override
	public int writeReview(Review review) {

		return reviewDao.writeReview(review);
	}

	@Override
	public int updateReview(Review review) {
		return  reviewDao.updateReview(review);
	}

	@Override
	public int deleteReview(Review review) {
		return reviewDao.deleteReview(review);
	}


}
