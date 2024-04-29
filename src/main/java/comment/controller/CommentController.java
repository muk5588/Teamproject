package comment.controller;

import board.dto.Board;
import board.service.BoardService;
import comment.dto.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import user.dto.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired private BoardService boardService;
	
	@RequestMapping("/insert")
	public @ResponseBody Comment commentInsert(
			Comment comment ,HttpSession session
			) {
		logger.debug("#################들옴");
		logger.debug("comment : {}", comment);
		User user = (User) session.getAttribute("dto1");
		comment.setUserNo(user.getUserno());
		int res = boardService.commentInsert(comment);
		logger.info("Ajax{}",res);
		
		return comment;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Comment commentDelete(
			Comment comment
			) {
		logger.info("Ajax delete");
		logger.info("value : {}", comment.toString());
		boardService.commentDelete(comment);
		
		return comment;
	}
	
	
	@RequestMapping("/refresh")
	public @ResponseBody List<Comment> commentRefresh(
			int boardno
			){
		Board board = new Board();
		board.setBoardNo(boardno);
		logger.info("Ajax refresh : {}" , board.toString());
		List<Comment> list = boardService.commentList(board);
		
		return list;
	}

	
	
	
}
