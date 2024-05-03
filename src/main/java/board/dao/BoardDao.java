package board.dao;

import board.dto.Board;
import board.dto.Category;
import board.dto.Good;
import board.dto.RecommendRes;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import user.dto.User;
import util.Paging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("BoardDao")
public interface BoardDao {

	/**
	 * 게시판의 전체 게시글을 DB에서 조회
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return 게시글 전체 리스트
	 */
	public List<Board> selectAll(Paging paging);

	/**
	 * 전체 개수 조회
	 * @param paging 
	 * @return
	 */
	public int selectCntAll(Paging paging);

	public Board select(int boardno);

	public void hit(int boardno);

	public int write(Board board);

	public Board selectBoardByBoardNo(int boardNo);

	public int updateBoard(Board board);

	/**
	 * 게시글을 삭제 한다
	 * @param deleteBoard - 삭제하려는 게시글 번호를 가진 DTO 객체
	 */
	public void deleteBoard(Board deleteBoard);

	/**
	 * 추천
	 * @param conn
	 * @param Good
	 */
	public void insertRecommend(Good good);

	public int isRecomm(Good good);

	public void deleteRecommend(Good good);

	public RecommendRes getRecommendRes(Good good);

	public List<Map<String, Object>> selectAllRecomm(Paging paging);

	public int getRecommend(int boardno);

	public int selectCntTitleBySearch(String search);

	public List<Board> selectBySearch(Paging paging);

	public int listDeleteByBoardNo(@Param("arr")ArrayList<Integer> boardno);


    public List<Category> categoryList();

	public List<Board> boardList(int userno);

	public int selectLogCntAll(Paging paging);

    public int selectAdminCntAll(Paging paging);

    public List<Board> userByBoardList(Paging paging);

	public List<Board> userrecommList(int userno);

	public void deleteComment(@Param("arr")ArrayList<Integer> boardno);

	public void deleteGood(@Param("arr")ArrayList<Integer> boardno);
}
