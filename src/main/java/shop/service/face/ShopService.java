package shop.service.face;

import java.util.List;

import dto.Item;
import dto.Review;
import util.ShopPaging;

public interface ShopService {

	/**
	 * 페이징 처리
	 * @param shopPaging - 페이징 객체
	 * @return - 페이징 객체
	 */
	public ShopPaging getPagging(ShopPaging shopPaging);

	public List<Item> list(ShopPaging shopPaging);

	/**
	 * 상품 번호로 상품 정보 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 상품 정보 행
	 */
	public Item getItemByItemNo(int itemNo);

}
