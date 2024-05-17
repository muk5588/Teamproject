package shop.dao;

import java.util.List;

import dto.Review;

public interface ReviewDao {

	public List<Review> selectByItemNo(int itemNo);

}
