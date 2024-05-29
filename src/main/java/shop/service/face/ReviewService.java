package shop.service.face;

import java.util.List;

import dto.Review;
import dto.UserOrder;

public interface ReviewService {

	/**
	 * 상품 번호로 리뷰 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 행(List)
	 */
	public List<Review> selectByItemNo(int itemNo);


	public int writeReview(Review review);


	public int updateReview(Review review);

	public int deleteReview(Review review);
}
