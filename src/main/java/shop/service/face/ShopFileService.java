package shop.service.face;

import java.util.List;

import dto.Item;
import dto.ItemFile;

public interface ShopFileService {
	
	/**
	 * 상품 대표 이미지 파일 정보 조회
	 * @return - 전체 상품 대표 이미지 파일 정보 
	 */
	public List<ItemFile> getTitleImgs();

	/**
	 * 상품 번호로 상품 파일 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 전체 파일 정보
	 */
	public List<ItemFile> getItemFilesByItemNo(int itemNo);

}
