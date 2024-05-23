package shop.service.face;

import java.util.List;
import java.util.Map;

import dto.Basket;
import dto.Item;
import dto.ItemFile;

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

	/**
	 * 장바구니 번호의 상품번호로 상품 조회
	 * @param baskets - 장바구니 List
	 * @return - 조회된 전체 상품 List
	 */
	public List<Item> itemsByBasketNos(List<Basket> baskets);

	/**
	 * 장바구니 번호의 상품번호로 상품 대표 이미지 조회
	 * @param baskets - 장바구니 List객체
	 * @return - 조회된 전체 대표 IMG파일 정보
	 */
	public List<ItemFile> itemFilesByBasketNos(List<Basket> baskets);

	/**
	 * Ajax 통신으로 받은 장바구니 정보를 회원 주문 정보로 가공 처리
	 * @param no - 전달받은 장바구니 번호 배열[]
	 * @return - UserOrder, ORDERITEM 의 정보를 담은 Map
	 */
	public Map<String, Object> userorderProc(int[] no);

	/**
	 * basketNo 를 이용해 장바구니 삭제
	 * @param basketNo - 장바구니 번호
	 * @return - delete 결과 0 실패 | 1 성공
	 */
	public int deleteBybasketNo(int basketNo);

}
