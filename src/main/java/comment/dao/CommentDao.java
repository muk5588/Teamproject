package comment.dao;

import board.dto.Board;
import comment.dto.Comment;

import java.util.List;

public interface CommentDao {

	public List<Comment> selectCommentByBoardNo(Board board);

	public int commentInsert(Comment comment);

	public void deleteComment(Comment comment);

	public void deleteCommentAll(Comment comment);

	public Comment commentByBoardNo(int commno);
}
