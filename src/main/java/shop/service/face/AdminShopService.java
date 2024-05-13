package shop.service.face;

import java.util.List;

import dto.Item;
import dto.ItemFile;
import util.Paging;

public interface AdminShopService {

	/**
	 * 페이징 (search => null || '검색어') 
	 * @param curPage - 현재 페이지 번호
	 * @param paging - 페이징 객체 
	 * @return
	 */
	public Paging getPaging(int curPage, Paging paging);
	
	/**
	 * 상품 조회 ( 페이징 처리)
	 * @param paging
	 * @return
	 */
	public List<Item> selectItems(Paging paging);

	/**
	 * List<Item> 의 대표 이미지 파일 조회
	 * @param items - List<Item>
	 * @return
	 */
	public List<ItemFile> selectTitleImgFile(List<Item> items);


}
