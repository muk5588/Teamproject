package shop.dao;

import java.util.List;

import dto.Item;
import dto.ItemFile;

public interface ShopFileDao {

	public List<ItemFile> getTitleImgs();

	public List<ItemFile> getItemFilesByItemNo(int itemNo);
	
}
