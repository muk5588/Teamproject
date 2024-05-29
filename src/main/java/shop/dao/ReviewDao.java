package shop.dao;

import java.util.List;

import dto.Review;
import dto.UserOrder;

public interface ReviewDao {

	public List<Review> selectByItemNo(int itemNo);

    public int writeReview(Review review);

	public int updateReview(Review review);

	public int deleteReview(Review review);
}
