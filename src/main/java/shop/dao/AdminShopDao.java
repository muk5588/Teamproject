package shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.Item;
import dto.ItemFile;
import util.Paging;

public interface AdminShopDao {

	public int selectCnt(Paging paging);

	public List<Item> selectItems(Paging paging);

	public List<ItemFile> selectTitleImgFile(@Param("items")List<Item> items);

}
