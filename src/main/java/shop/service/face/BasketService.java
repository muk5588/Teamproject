package shop.service.face;

import java.util.List;

import dto.Basket;

public interface BasketService {

	/**
	 * 장바구니 객체 DB에 insert
	 * @param basket - 장바구니 정보 객체
	 * @return - 삽입된 데이터 행
	 */
	public int insertBasket(Basket basket);

	/**
	 * 유저 번호로 장바구니 전체 조회
	 * @param userNo - 유저 번호
	 * @return - 조회된 전체 행
	 */
	public List<Basket> basketListByUserNo(int userNo);

}
