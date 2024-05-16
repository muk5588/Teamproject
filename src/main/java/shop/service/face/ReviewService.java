package shop.service.face;

import java.util.List;

import dto.Review;
import vo.ReviewVO;

public interface ReviewService {

	/**
	 * 상품 번호로 리뷰 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 행(List)
	 */
	public List<ReviewVO> selectByItemNo(int itemNo);

	
}
