package shop.dao;

import java.util.List;

import vo.ReviewVO;

public interface ReviewDao {

	public List<ReviewVO> selectByItemNo(int itemNo);

}
