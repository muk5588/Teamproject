package shop.service.face;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import dto.Item;
import dto.ItemFile;
import util.Paging;
import util.ShopPaging;

public interface AdminShopService {

	/**
	 * 페이징 (search => null || '검색어') 
	 * @param curPage - 현재 페이지 번호
	 * @param paging - 페이징 객체 
	 * @return
	 */
	public ShopPaging getPaging(int curPage, ShopPaging paging);
	
	/**
	 * 상품 조회 ( 페이징 처리)
	 * @param paging
	 * @return
	 */
	public List<Item> selectItems(ShopPaging paging);

	/**
	 * List<Item> 의 대표 이미지 파일 조회
	 * @param items - List<Item>
	 * @return
	 */
	public List<ItemFile> selectTitleImgFile(List<Item> items);

	/**
	 * Ajax 로 전달 받은 이미지 파일을 저장 처리 
	 * @return - 저장된 파일 객체( ItemFile)
	 */
	public ItemFile fileTempSave();

	/**
	 * 상품 정보 등록
	 * @param item - 상품 객체
	 * @return
	 */
	public int insertItem(Item item);

	/**
	 * itemComm 정규식 으로 원본, 저장된 파일명 추출 후 Insert
	 * itemNo ( FK ) 
	 * @param item - Item 객체
	 * @return
	 */
	public int fileSave(Item item);

	/**
	 * 상품 대표 이미지 파일 설정
	 * @param item - 상품 객체
	 * @param file - 파일 객체
	 * @return
	 */
	public int updatetitleImg(Item item, MultipartFile file);

	/**
	 * 상품 번호로 상품 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 상품 행
	 */
	public Item selectItemByItemNo(int itemNo);

	/**
	 * 상품 번호로 상품 파일 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 상품 파일 행List
	 */
	public List<ItemFile> selectItemFileByItemNo(int itemNo);

	/**
	 * 상품객체 UPDATE
	 * @param item - 상품 객체
	 * @return 
	 */
	public int updateIByItem(Item item);

	/**
	 * 삭제.
	 * @param itemNo
	 * @return
	 */
	public int deleteByItemNo(int itemNo);


	
	
	
	
	

}
