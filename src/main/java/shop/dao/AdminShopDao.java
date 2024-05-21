package shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Item;
import dto.ItemFile;
import util.Paging;
import util.ShopPaging;

public interface AdminShopDao {

	public int selectCnt(ShopPaging paging);

	public List<Item> selectItems(ShopPaging paging);

	public List<ItemFile> selectTitleImgFile(@Param("items")List<Item> items);

	public int insertItem(Item item);

	public int fileSave(@Param("itemFiles")List<ItemFile> itemFiles);

	public int inserttitleImgFile(ItemFile filetest);

	public int updatetitleImgFile(ItemFile filetest);

	public Item selectItemByItemNo(int itemNo);

	public List<ItemFile> selectItemFileByItemNo(int itemNo);

	public int updateIByItem(Item item);

	public void deleteByItemOldFile(Item item);

	public int deleteitemByItemNo(int itemNo);

	public int deleteitemFileByItemNo(int itemNo);

	public int deleteItemFK(int itemNo);
	
}
