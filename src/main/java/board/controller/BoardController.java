package board.controller;

import board.dto.Board;
import board.dto.RecommendRes;
import board.service.BoardService;
import board.service.FileService;
import comment.dto.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import user.dto.User;
import util.Paging;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardService boardService;
	@Autowired private FileService fileService;
	
	@GetMapping("/list")
	public void list(
			Model model, 
			@RequestParam(defaultValue ="0") int curPage
			,@RequestParam(value="search",required = false) String search
			,@RequestParam(value="searchKind", required = false ) String searchKind) {
		logger.info("/board/list [GET]");
		logger.info("/board/list search : {}", search);
		logger.info("/board/list searchKind : {}", searchKind);
		
		// 페이징 계산
		Paging paging = new Paging();
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		if(null !=  search && !"".equals(search)) {
			paging = boardService.getPaging(curPage,paging);
		}else {
			paging = boardService.getPaging(curPage,paging);
		}
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		logger.info("{}", paging);
		
		List<Board> list = boardService.list(paging);
		
		List<Map<String, Object>> recommList = boardService.getRecommendRes(paging);
		logger.debug("recommList : {}", recommList);
		for(Map<String, Object> M : recommList) {
			logger.debug("M : {}", M.toString());
		}
		model.addAttribute("totalrecomm", recommList);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
	}
	
	
	@GetMapping("/view")
	public void view(
			@RequestParam("boardNo") int boardno
			, Board board
			, Model model
			, HttpSession session
			, @RequestParam(value="curPage", defaultValue="0") int curPage
			) {
		board =  boardService.viewByBoardNo(boardno);
		int recomm = boardService.viewRecommend(boardno);
		
		if( null != session.getAttribute("userno")) {
			boolean isRecommend = boardService.isRecommend(session,boardno);
			model.addAttribute("recommend", isRecommend);
		}
		
		List<Comment> comment = boardService.commentList(board);
		
		model.addAttribute("comment", comment);
		model.addAttribute("recomm", recomm);
		model.addAttribute("curPage", curPage);
		model.addAttribute("board", board);
	}
	
	@GetMapping("/write")
	public void write() {}
	
	@PostMapping("/write")
	public String writeProc(
			HttpSession session
			,String title
			,String content
			, MultipartFile file
			) {
		logger.info("title : {}", title);
		logger.info("content : {}", content);
		logger.info("file : {}",file);
		
		Board board = new Board();
		board.setContent(content);
		board.setTitle(content);
		if( null != session.getAttribute("userno") ) {
			User user = (User) session.getAttribute("userno");
			board.setBoardNo(user.getUserno());
			board.setNickName(user.getNickname());
			int res = boardService.write(board);
		}else {
			return "redirect:/login";
		}
		
		logger.info("board 값 확인 : {}", board);
		
		if( null == file ) {
			logger.debug("첨부 파일 없음");
		}else if( file.getSize() <= 0 ){
			logger.debug("파일의 크기가 0");
		}else {
			fileService.filesave(board,file);
			
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/fileChk")
	public void fileChk() {
		
	}
	
	@GetMapping("/update")
	public void update(
			int boardNo
			, Model model
			) {
		logger.info("{}",boardNo);
		
		Board board = boardService.boardView(boardNo);
		
		model.addAttribute("board", board);
	}
	
	@PostMapping("/update")
	public String updateProc(
			Board board
			) {
		logger.info("{}", board);
		
		int res = boardService.boardUpdate(board);
		
		if ( res > 0) {
			return "redirect:./list";
		}
		return "./list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("boardNo") int boardno) {
		logger.debug("delete 변수 : {}",boardno);
		
		Board deleteBoard = new Board();
		deleteBoard.setBoardNo(boardno);
		
		boardService.boardDelete(deleteBoard);
		
		return "redirect:./list";
	}
	
	@RequestMapping("/recommend")
	public @ResponseBody RecommendRes recommend(
			Board recommendBoard
			, HttpSession session
			) {
		logger.info("추천 확인 {}, {} ", recommendBoard, session.getAttribute("userno"));
		
		boardService.recommend(recommendBoard);
		
		RecommendRes res = boardService.getRecommendRes(recommendBoard);
		return res;
	}
	
	@RequestMapping("/listDelete")
	public @ResponseBody board.dto.delete listDelete(
//		public void listDelete( 
			@RequestParam("boardNo[]") int[] boardno) {
		for(int i = 0; i < boardno.length; i++) {
			boardService.listDelete(boardno[i]);
		}
		logger.info("삭제 완료");
		board.dto.delete res = new board.dto.delete();
		res.setResult("true");
		return res;
	}
	
	
}
