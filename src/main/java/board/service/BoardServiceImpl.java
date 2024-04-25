package board.service;

import board.dao.BoardDao;
import board.dto.Board;
import board.dto.Good;
import board.dto.RecommendRes;
import comment.dao.CommentDao;
import comment.dto.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Paging;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardDao boardDao;
	@Autowired private CommentDao commentDao;
	@Autowired private HttpSession session;
	
	@Override
	public List<Board> list(Paging paging) {
		return boardDao.selectAll(paging);
	}
	
	@Override
	public Paging getPaging(int curPage, Paging paging) {
		
		int totalCount = boardDao.selectCntAll(paging);
		
		Paging pagingres = new Paging(totalCount, curPage);
		
		return pagingres;
	}
	
	@Override
	public Board viewByBoardNo(int boardno) {
		boardDao.hit(boardno);
		return boardDao.select(boardno);
	}

	@Override
	public int write(Board board) {
		return boardDao.write(board);
	}

	@Override
	public Board boardView(int boardNo) {
		return boardDao.selectBoardByBoardNo(boardNo);
	}

	@Override
	public int boardUpdate(Board board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public void boardDelete(Board deleteBoard) {
		boardDao.deleteBoard(deleteBoard);
	}

	@Override
	public void recommend(Board recommendBoard) {
		
		int userno = (int)session.getAttribute("userno");
		int no = recommendBoard.getBoardNo();
		Good good = new Good(userno, no);
		
		if( boardDao.isRecomm(good) > 0 ) {
			boardDao.deleteRecommend(good);
			
		}else {
			boardDao.insertRecommend(good);
			
		}

	}

	@Override
	public boolean isRecommend(HttpSession session, int boardno) {
		int res=0;
		if( null != session.getAttribute("userno")) {
			Good good = new Good((int)this.session.getAttribute("userno"), boardno);
			res = boardDao.isRecomm(good);
		}
		
		if( res > 0 ) {
			return true;
		}
		return false;
	}

	@Override
	public RecommendRes getRecommendRes(Board recommendBoard) {
		if( null != session.getAttribute("userno")) {
			Good good = new Good((int)this.session.getAttribute("userno"), recommendBoard.getBoardNo());
			return boardDao.getRecommendRes(good);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getRecommendRes(Paging paging) {
		return boardDao.selectAllRecomm(paging);
	}

	@Override
	public int viewRecommend(int boardno) {
		return boardDao.getRecommend(boardno);
	}

	@Override
	public List<Comment> commentList(Board board) {
		return commentDao.selectCommentByBoardNo(board);
	}

	@Override
	public int commentInsert(Comment comment) {
		return commentDao.commentInsert(comment);
	}

	@Override
	public void commentDelete(Comment comment) {
		commentDao.deleteComment(comment);
	}

	@Override
	public void listDelete(int boardno) {
//		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>(); 
		boardDao.listDelete(boardno);
	}



	
}
