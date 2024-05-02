 package board.controller;

 import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
 import java.util.*;

 import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import board.dto.Board;
import board.dto.BoardFile;
import board.dto.Category;
import board.dto.RecommendRes;
import board.service.BoardService;
import board.service.FileService;
import comment.dto.Comment;
import user.dto.User;
import util.Paging;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardService boardService;
	@Autowired private FileService fileService;
	@Autowired private ServletContext servletContext;
	
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
	@GetMapping("/category")
	public void category(Model model){
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

		List<Comment> comment = boardService.commentList(board);
		model.addAttribute("comment", comment);
		model.addAttribute("recomm", recomm);
		model.addAttribute("curPage", curPage);
		model.addAttribute("board", board);
	}
	
	@GetMapping("/write")
	public void write(Model model, HttpSession session) {
		List<Category> categorylist = boardService.categoryList();
		model.addAttribute("categorylist", categorylist);
	}
	
	@PostMapping("/write")
	public String writeProc(
			HttpSession session
			, Board board
			, @RequestParam("categoryNo") int categoryNo
			, @RequestAttribute(required = false)MultipartFile file
			) {
		logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		User user = (User) session.getAttribute("dto1");
		logger.info("board : {}", board);
		logger.info("categoryNo : {}", categoryNo);
		board.setCategoryNo(categoryNo);
		board.setUserNo(user.getUserno());
		board.setNickName(user.getNickname());
		int res = boardService.write(board);

		String content = board.getContent();
		logger.info("content 확인 : {}", content);
		List<String> originNames = fileService.extractOriginName(content);
		logger.info("originNames 확인 : {}", originNames);
		List<String> storedNames = fileService.extractStoredName(content, originNames);
		logger.info("storedNames 확인 : {}", storedNames);
		if (originNames != null && storedNames != null && originNames.size() == storedNames.size()) {
			ArrayList<BoardFile> files = new ArrayList<>();
		    for (int i = 0; i < originNames.size(); i++) {
		        String originName = originNames.get(i);
		        String storedName = storedNames.get(i);
		        if (originName != null && storedName != null) {
		            BoardFile bf = new BoardFile();
		            bf.setBoardNo(board.getBoardNo());
		            bf.setOriginName(originName);
		            bf.setStoredName(storedName);
		            files.add(bf);
		        }
		    }
		    fileService.setFile(files);
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
	
	@ResponseBody
	@PostMapping("/fileupload")
	public void fileupload(HttpServletResponse response
			, HttpServletRequest request
//			, MultipartFile file
//			, MultipartHttpServletRequest multiRequest 
//			, Board board
			) {
		logger.debug("/fileupload&&&&&&&&&&&&&&&&&&&&&&&&");
//		logger.debug("file : {}", file);
		BoardFile file =fileService.fileTempSave(request,response); 
		logger.debug("!@$#!@#!@#!@#!@#!files : {}", file);
		
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
		Comment comment	= new Comment();
		deleteBoard.setBoardNo(boardno);
		comment.setBoardNo(boardno);
		boardService.commentDeleteAll(comment);
		boardService.boardDelete(deleteBoard);
		
		return "redirect:./list";
	}
	
	@RequestMapping("/recommend")
	public @ResponseBody RecommendRes recommend(
			Board recommendBoard
			, HttpSession session
			) {
		logger.info("추천 확인 {}, {} ", recommendBoard, session.getAttribute("isLogin"));
		
		boardService.recommend(recommendBoard);

		RecommendRes res = boardService.getRecommendRes(recommendBoard);
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/listDelete")
	public int listDelete(
//		public void listDelete( 
			@RequestParam("boardNo[]") int[] no) {
		ArrayList<Integer> boardno = new ArrayList<Integer>();
		for(int i = 0; i < no.length; i++) {
//			boardService.listDelete(boardno[i]);
			boardno.add(no[i]);
		}
		fileService.listDeleteByBoardNo(boardno);
		int res = boardService.listDeleteByBoardNo(boardno);
		logger.debug("삭제 완료");
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/boardFileChk")
	private void boardImageChk(int boardno) {
		logger.debug("파일 체크 ");
		logger.debug("boardno : {}", boardno);
//		int res = fileService.getFileCnt(boardno);
//		if(res <= 0) {
////			return null;
//		}else if(res == 1) {
//			BoardFile file = fileService.getFileByBoardNo(boardno);
//		}else if(res > 1) {
////			List<BoardFile> file = fileService.getFileByBoardNo(boardno);
//		}
		
	}
	@RequestMapping("/userbyboardlist")
	public String userByBoardList(
			Model model,
			@RequestParam(defaultValue ="0") int curPage
			,@RequestParam(value="search",required = false) String search
			,@RequestParam(value="searchKind", required = false ) String searchKind
			,int userno
			) {
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
		paging.setUserno(userno);
		List<Board> list = boardService.userByBoardList(paging);


		logger.info("list : {}", list);




		List<Map<String, Object>> recommList = boardService.getRecommendRes(paging);
		logger.debug("recommList : {}", recommList);
		for(Map<String, Object> M : recommList) {
			logger.debug("M : {}", M.toString());
		}
		model.addAttribute("totalrecomm", recommList);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		return "board/list";
	}
	
}
