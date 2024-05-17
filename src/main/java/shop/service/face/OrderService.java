package shop.service.face;

import java.util.List;
import java.util.Map;

import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import user.dto.User;
import util.Paging;
import util.ShopPaging;

public interface OrderService {

	public int[] getItemNosByorderDatas(String[] orderDatas);

	/**
	 * 장바구니에서 결제 선택한 상품의 데이터 가공
	 * @param orderNumbers - 장바구니 번호 배열
	 * @return
	 */
	public Map<String, Object> userorderProc(int[] orderNumbers);

	/**
	 * UserOrder 객체 생성 ( 기본값 현재 로그인한 회원 정보)
	 * @return
	 */
	public UserOrder makeUserOrder();

	/**
	 * 결제 정보에서 입력받은 정보를 토대로 UserOrder객체 insert
	 * @param userOrder
	 * @return
	 */
	public int insertUserOrder(UserOrder userOrder);

	/**
	 * 결제한 목록을 장바구니 -> 주문 상세로 인서트(장바구니 삭제 처리)
	 * @param orderDatas
	 * @param userOrder 
	 * @return
	 */
	public List<OrderItem> insertOrderItems(String orderDatas, UserOrder userOrder);

	/**
	 * 상품 번호로 대표 이미지 조회
	 * @param resOrderItems - 상품 번호를 담은 주문 상세 객체
	 * @return
	 */
	public List<ItemFile> gettitleImg(List<OrderItem> resOrderItems);

	/**
	 * 상품 번호와 수량으로 선택한 상품의 데이터 가공
	 * @param itemNo
	 * @param quantity
	 * @return
	 */
	public Map<String, Object> getDatasByitemNoByquantity(int itemNo, int quantity);

	/**
	 * 주문 상세 INSERT
	 * @param orderItem - 주문 상세 객체
	 * @return
	 */
	public int insertOrderItem(OrderItem orderItem);

	public OrderItem selectByOrderItem(OrderItem orderItem);

	/**
	 * 유저 객체로 회원주문 조회
	 * @param user - 유저 정보
	 * @param shopPaging 
	 * @return - 조회된 전체 행
	 */
	public List<UserOrder> selectUserOrderByUser(User user, ShopPaging shopPaging);

	/**
	 * 회원 주문 리스트로 주문 상세 조회
	 * @param orders - 회원주문 List
	 * @return - 조회된 전체 행
	 */
	public List<OrderItem> selectOrderItemsByUserOrders(List<UserOrder> orders);

	/**
	 * 주문상세 리스트로 상품 조회
	 * @param orderitems - 주문상세 리스트
	 * @return - 조회된 전체 행
	 */
	public List<Item> selectItemByUserOrderItems(List<OrderItem> orderitems);

	/**
	 * 페이징
	 * @param shopPaging
	 * @param userno 
	 * @return
	 */
	public ShopPaging getPagging(ShopPaging shopPaging, int userno);

	/**
	 * 전체 주문 목록을 조회
	 * @param shopPaging - 페이징 객체
	 * @return - 조회된 전체 행
	 */
	public List<UserOrder> selectUserOrderAll(ShopPaging shopPaging);

	/**
	 * 주문 취소 (환불 처리)
	 * @param orderNo - 취소할 주문 번호
	 * @return - 결과 0:실패 | 1:성공
	 */
	public int updateUserOrderPayCancle(int orderNo);

	

	
	
}
