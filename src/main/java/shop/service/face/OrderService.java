package shop.service.face;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import dto.Basket;
import dto.Item;
import dto.ItemFile;
import dto.OrderItem;
import dto.UserOrder;
import user.dto.User;
import util.ShopPaging;

public interface OrderService {

	public List<Basket> getItemNosByorderDatas(String[] orderDatas, String[] quantities);

	/**
	 * 장바구니에서 결제 선택한 상품의 데이터 가공
	 * @param baskets - 장바구니 번호 배열
	 * @return
	 */
	public Map<String, Object> userorderProc(List<Basket> baskets);

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
	 * @param orderDatas2 
	 * @param userOrder 
	 * @return
	 */
	public List<OrderItem> insertOrderItems(String quantities, String orderDatas, UserOrder userOrder);

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
	 * @param userOrder - 취소할 주문 객체
	 * @param token - 토큰
	 * @return - 결과 0:실패 | 1:성공
	 */
	public int updateUserOrderPayCancle(UserOrder userOrder, String token);

	/**
	 * 포트원 토큰 가져오기
	 * @return - 반환 받은 JSON 객체
	 */
	public String getToken();

	/**
	 * 포트원 결과에 따라 행 업데이트
	 * @param userOrder
	 */
	public void updateUserOrderorderCancle(UserOrder userOrder);


	/**
	 * 
	 * @param sTitemNo
	 * @param sTquantity
	 * @param orderNo
	 * @return
	 */
	public List<OrderItem> getOrderItemsByItemNosQuantitys(String[] sTitemNo, String[] sTquantity, int orderNo);

	/**
	 * 결제하는 상품이 장바구니에 존재하는 경우 체크 후 존재할 경우 삭제
	 * @param orderItems - 결제한 주문목록 객체 List
	 * @return - 0 : 없음 | 1이상 : 삭제된 장바구니 데이터 행 
	 */
	public int deleteOverlappingBaskets(List<OrderItem> orderItems);

	/**
	 * List<OrderItem> INSERT
	 * @param orderItems - 주문상품 리스트
	 * @return - 삽입된 데이터 행
	 */
	public int insertOrderItemByListOrderItem(List<OrderItem> orderItems);

	/**
	 * 주문목록으로 상품 리스트 조회
	 * @param orderItems - 주문목록 리스트
	 * @return - 조회된 전체 행
	 */
	public List<Item> getItemsByOrderItems(List<OrderItem> orderItems);

	/**
	 * 상품 리스트 로 대표이미지파일 조회
	 * @param items - 상품 리스트
	 * @return - 조회된 전체 행
	 */
	public List<ItemFile> getItemFilesByItemNos(List<Item> items);

	public List<OrderItem> getOrderItemsByUserOrder(UserOrder userOrder);

	public int itemReaminReduction(List<OrderItem> orderItems);


	
	
}
