 package board.controller;

 import board.dto.*;
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
 import vo.GoodVO;

 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;

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
	    @RequestParam(defaultValue ="0") int curPage,
	    @RequestParam(value="search",required = false) String search,
	    @RequestParam(value="searchKind", required = false) String searchKind,
	    @RequestParam(value="categoryNo", required = false) Integer categoryNo) {

	    logger.info("/board/list [GET]");
	    logger.info("/board/list search : {}", search);
	    logger.info("/board/list searchKind : {}", searchKind);
	    logger.info("/board/list categoryNo : {}", categoryNo);
		
	    // 페이징 계산
	    Paging paging = new Paging();
	    paging.setSearch(search);
	    paging.setSearchKind(searchKind);
	    
	    if (categoryNo != null) {
	        paging.setCategoryNo(categoryNo);
	    }
	    
	    if (null !=  search && !"".equals(search)) {
	        paging = boardService.getPaging(curPage, paging);
	    } 
	    else {
	        paging = boardService.getPaging(curPage, paging);
	    }
	    paging.setSearch(search);
	    paging.setSearchKind(searchKind);
	    
	    List<Board> list = null;
	    List<Map<String, Object>> recommList = null;
		String name = null;
	    logger.info("paging : {}",paging);
	    
	    if (categoryNo != null) {
	    	paging.setCategoryNo(categoryNo);
	        list = boardService.listByCategory(paging);
	        recommList = boardService.getRecommendRes(paging);
			name = boardService.getCategoryName(categoryNo);
	    } else {
	        list = boardService.list(paging);
	        recommList = boardService.getRecommendRes(paging);
			name = "전체";
	    }

//	    logger.debug("list : {}", list);
//	    logger.debug("recommList : {}", recommList);
	    for(Board M : list) {
//			logger.debug("!!@!@!@M : {}", M); 
	    }
	    for(Map<String, Object> M : recommList) {
//	        logger.debug("M : {}", M.toString());
	    }
	    model.addAttribute("totalrecomm", recommList);
	    model.addAttribute("curPage", curPage);
	    model.addAttribute("paging", paging);
	    model.addAttribute("list", list);
		model.addAttribute("name", name);
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
			, @RequestParam(value ="categoryNo", required = false, defaultValue = "0")int categoryNo 
			) {

		board =  boardService.viewByBoardNo(boardno);
		User user = (User)session.getAttribute("dto1");
		int recomm = 0;
		if( null == user) {
			recomm = boardService.viewRecommend(boardno);
		}else {
			Good paramGood = new Good(user.getUserno(), boardno);
			GoodVO good = boardService.getRecommendVO(paramGood);
			model.addAttribute("isRecomm", good.getIsRecomm());
			logger.info("isRecomm : {}", good.getIsRecomm());
			recomm = good.getTotalRecomm();
		}
		List<Comment> comment = boardService.commentList(board);
		model.addAttribute("comment", comment);
		model.addAttribute("recomm", recomm);
		logger.info("recomm : {}", recomm);
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
		String categoryTitle =  "[";
		categoryTitle += boardService.getCategoryName(categoryNo);
		categoryTitle += "]";
		categoryTitle += board.getTitle();
		board.setTitle(categoryTitle);
		board.setCategoryNo(categoryNo);
		board.setUserNo(user.getUserno());
		board.setNickName(user.getNickname());
		int res = boardService.write(board);

		String content = board.getContent();
		logger.info("content Ȯ�� : {}", content);
		List<String> originNames = fileService.extractOriginName(content);
		logger.info("originNames Ȯ�� : {}", originNames);
		List<String> storedNames = fileService.extractStoredName(content, originNames);
		logger.info("storedNames Ȯ�� : {}", storedNames);
		if (originNames != null && storedNames != null && originNames.size() == storedNames.size() && !originNames.isEmpty() && !storedNames.isEmpty()) {
			ArrayList<BoardFile> files = new ArrayList<>();
			logger.info("�̹��� ���� ó���� :%%%%%%%%%%%%%%%%%%%%%%%%%%" );
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
        
		logger.info("board �� Ȯ�� : {}", board);
		
		if( null == file ) {
			logger.debug("÷�� ���� ����");
		}else if( file.getSize() <= 0 ){
			logger.debug("������ ũ�Ⱑ 0");
		}else { 
//			for( )
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
	
	@ResponseBody
	@RequestMapping("/fileChk")
	public List<BoardFile> fileChk(@RequestParam("boardno")int boardNo) {
		List<BoardFile> files = fileService.getFileList(boardNo);
		logger.info("fileChk : {}", files);
		return files;
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
		logger.debug("delete ���� : {}",boardno);
		
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
		logger.info("��õ Ȯ�� {}, {} ", recommendBoard, session.getAttribute("isLogin"));
		
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
		boardService.deleteComment(boardno);
		boardService.deleteGood(boardno);
		fileService.listDeleteByBoardNo(boardno);
		int res = boardService.listDeleteByBoardNo(boardno);
		logger.debug("���� �Ϸ�");
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/boardFileChk")
	private void boardImageChk(int boardno) {
		logger.debug("���� üũ ");
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
			,@RequestParam(value="search",required = false) String search,
			@RequestParam(value="searchKind", required = false) String searchKind
			,@SessionAttribute(value = "dto1", required = false) User login
			) {
		// ����¡ ���

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
		paging.setUserno(login.getUserno());
		logger.info("{}", paging);
		List<Board> list = boardService.userByBoardList(paging);
		logger.debug("list : {}", list);
		int usrno = login.getUserno();
		List<Map<String, Object>> recommList = null;
		recommList = boardService.getuserRecommendRes(paging);
		logger.debug("recommList : {}", recommList);
		for(Map<String, Object> M : recommList) {
			logger.debug("M : {}", M.toString());
		}

		model.addAttribute("totalrecomm", recommList);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);
		model.addAttribute("usrno",usrno);
		return "board/userbyboardlist";
	}

	@RequestMapping("/userbyrecommlist")
	public void userbyRecommList(@SessionAttribute(value = "dto1", required = false) User login,
								 @RequestParam(defaultValue ="0") int curPage, Model model
								,@RequestParam(value="search",required = false) String search
								,@RequestParam(value="searchKind", required = false ) String searchKind){


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
		paging.setUserno(login.getUserno());
		List<Board> list2 = boardService.userbyrecommList(paging);

		model.addAttribute("list2", list2);
		model.addAttribute("paging", paging);
	}
	
	@RequestMapping("/fileDown")
	public String fileDown(int fileNo, Model model) {
		BoardFile file = fileService.getFileByFileNo(fileNo);
		logger.info("���� �ٿ�ε� : {}", file);
		
		model.addAttribute("downFile", file);
		
		logger.info("���� �ٿ�ε� : {}", file);
		return "downView";
	}
	
	//한국어 테스트
	
	
}
