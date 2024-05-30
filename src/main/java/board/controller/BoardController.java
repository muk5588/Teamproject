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
 import report.dto.BoardReport;
 import report.dto.CommReport;
 import report.service.ReportService;
 import user.dto.User;
 import util.Paging;
 import vo.GoodVO;

 import javax.servlet.ServletContext;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 import java.util.*;

 @Controller
@RequestMapping("/board")
public class BoardController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BoardService boardService;
	@Autowired private FileService fileService;
	@Autowired private ServletContext servletContext;
	@Autowired private ReportService reportService;
	
	@GetMapping("/list")
	public String list(
	    Model model,
	    @RequestParam(defaultValue ="0") int curPage,
	    @RequestParam(value="search",required = false) String search,
	    @RequestParam(value="searchKind", required = false) String searchKind,
	    @RequestParam(value="categoryNo", required = false) Integer categoryNo) {

	    logger.info("/board/list [GET]");
	    logger.info("/board/list search : {}", search);
	    logger.info("/board/list searchKind : {}", searchKind);
	    logger.info("/board/list categoryNo : {}", categoryNo);
		String URL = "/board/list";
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
	    logger.info("$$$%%%paging : {}",paging);
	    
	    //게시글 0개 조회 되면
	    if( paging == null) {
	    	paging = new Paging();
	    	paging.setSearch(search);
	    	paging.setSearchKind(searchKind);
	    	if (categoryNo != null) {
	    		paging.setCategoryNo(categoryNo);
	    	}
	    	model.addAttribute("curPage", curPage);
	    	model.addAttribute("paging", paging);
	    	return URL;
	    	
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
	        recommList = boardService.getuserRecommendRes(list);
			name = boardService.getCategoryName(categoryNo);
			int categoryno = boardService.category(categoryNo);
			model.addAttribute("gradeNo", categoryno);
	    } else {
	        list = boardService.list(paging);
	        recommList = boardService.getuserRecommendRes(list);
			name = "전체";
	    }
//		List<BoardReport> reportlist = reportService.reportboardlist();
		Iterator<Board> iterator = list.iterator();

//		while (iterator.hasNext()) {
//			Board board = iterator.next();
//			for (BoardReport report : reportlist) {
//				if (report.getBoardNo() == board.getBoardNo()) {
//					iterator.remove();
//					break; // 현재 보드가 삭제되었으므로 내부 루프 종료
//				}
//			}
//		}


//	    logger.debug("list : {}", list);
//	    logger.debug("recommList : {}", recommList);
	    for(Board M : list) {
//			logger.debug("!!@!@!@M : {}", M); 
	    }
	    for(Map<String, Object> M : recommList) {
//	        logger.debug("M : {}", M.toString());
	    }
	    model.addAttribute("URL", URL);
	    model.addAttribute("totalrecomm", recommList);
	    model.addAttribute("curPage", curPage);
	    model.addAttribute("paging", paging);
	    model.addAttribute("list", list);
		model.addAttribute("name", name);

		return URL;
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
			, @RequestParam(value ="usrno", required = false, defaultValue = "0")int usrno
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
//		신고된 댓글 블러 처리
		List<CommReport> reportlist = reportService.reportcommlist();
		Iterator<Comment> iterator = comment.iterator();
		while (iterator.hasNext()) {
			Comment comment2 = iterator.next();
			for (CommReport report : reportlist) {
				if (report.getCommNo() == comment2.getCommNo()) {
					iterator.remove();
					break;
				}
			}
		}
		model.addAttribute("comment", comment);
		model.addAttribute("recomm", recomm);
		logger.info("recomm : {}", recomm);
		model.addAttribute("curPage", curPage);
		model.addAttribute("board", board);
		model.addAttribute("usrno",usrno);
	}
	
	@GetMapping("/write")
	public void write(Model model, HttpSession session,@RequestParam(value = "categoryNo")String categoryNo) {
		List<Category> categorylist = boardService.categoryList();
		if( categoryNo != null) {
			int categoryNoInt = Integer.parseInt(categoryNo);
			model.addAttribute("categoryNoInt", categoryNoInt);
			logger.debug("categoryNo : {}",categoryNo);
			logger.debug("categoryNoInt : {}",categoryNoInt);
		}
		logger.debug("categorylist : {}",categorylist);
		model.addAttribute("categorylist", categorylist);
	}
	
	@PostMapping("/write")
	public String writeProc(
			HttpSession session
			, Board board
			, @RequestParam(value="categoryNo")int categoryNo
			, @RequestAttribute(required = false)MultipartFile file
			) {
		String URL = "redirect:/board/list";
		String category = String.valueOf(categoryNo);
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
		URL += "?categoryNo=" +category;
		return URL;
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
		List<Category> categorylist = boardService.categoryList();
		Board board = boardService.boardView(boardNo);
		model.addAttribute("categoryNoInt", board.getCategoryNo());
		model.addAttribute("categorylist", categorylist);
		model.addAttribute("board", board);
	}
	
	@PostMapping("/update")
	public String updateProc(
			Board board
			) {
		logger.info("{}", board);
		String URL = "";
		board.setUpdateDate(new Date());
		int res = boardService.boardUpdate(board);
		
		if ( res > 0) {
			URL ="redirect:/board/view?categoryNo="+ board.getCategoryNo()+"&boardNo="+ board.getBoardNo()+"&curPage=0";
			return URL;
		}
		return "./list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("boardNo") int boardno,@RequestParam(value = "categoryNo")String categoryNo) {
		logger.debug("delete ���� : {}",boardno);
		
		Board deleteBoard = new Board();
		Comment comment	= new Comment();
		deleteBoard.setBoardNo(boardno);
		comment.setBoardNo(boardno);
		boardService.commentDeleteAll(comment);
		boardService.boardDelete(deleteBoard);
		
		return "redirect:./list?categoryNo=" +categoryNo;
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
			Model model,HttpSession session,
			@RequestParam(defaultValue ="0") int curPage
			,@RequestParam(value="search",required = false) String search,
			@RequestParam(value="searchKind", required = false) String searchKind
			) {
		// ����¡ ���
		String URL = "/board/userbyboardlist";
		model.addAttribute("URL", URL);
		logger.debug("userbyboardlist ");
		logger.debug("userbyboardlist ");
		User login = (User) session.getAttribute("dto1");
		if( login == null) {
			return "redirect:/login";
		}
		Paging paging = new Paging();
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		if(null !=  search && !"".equals(search)) {
			paging = boardService.getPagingByUserNo(curPage,paging,login);
		}else {
			paging = boardService.getPagingByUserNo(curPage,paging,login);
		}
		//페이징 결과 없을 경우 (cnt 0개 )
		if( paging == null ) {
			paging = new Paging();
			paging.setSearch(search);
			paging.setSearchKind(searchKind);
			model.addAttribute("curPage", curPage);
			model.addAttribute("paging", paging);
			model.addAttribute("param", login);
			return "board/userbyboardlist";
		}
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		paging.setUserno(login.getUserno());
		logger.info("{}", paging);
		List<Board> list = boardService.userByBoardList(paging);
		logger.debug("list : {}", list);
		//조회 결과 없을 경우
		if( list == null ) {
			model.addAttribute("curPage", curPage);
			model.addAttribute("paging", paging);
			model.addAttribute("param", login);
			logger.debug("paramparamparamparam: {}", login);
			return "board/userbyboardlist";
		}

		List<Map<String, Object>> recommList = null;
		recommList = boardService.getuserRecommendRes(list);
		logger.debug("recommList : {}", recommList);
		for(Map<String, Object> M : recommList) {
			logger.debug("M : {}", M.toString());
		}
		logger.debug("paramparamparamparam: {}", login);

		model.addAttribute("totalrecomm", recommList);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("list", list);

		return "board/userbyboardlist";
	}

	@RequestMapping("/userbyrecommlist")
	public String userbyRecommList(@SessionAttribute(value = "dto1", required = false) User login,
								 @RequestParam(defaultValue ="0") int curPage, Model model
								,@RequestParam(value="search",required = false) String search
								,@RequestParam(value="searchKind", required = false ) String searchKind){

		String URL = "/board/userbyrecommlist";
		model.addAttribute("URL", URL);
		Paging paging = new Paging();
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		if(null !=  search && !"".equals(search)) {
			paging = boardService.getPagingByUserNoGood(curPage,paging,login);
		}else {
			paging = boardService.getPagingByUserNoGood(curPage,paging,login);
		}
		//게시글 없는 경우
		if( paging == null ) {
			paging = new Paging();
			paging.setSearch(search);
			paging.setSearchKind(searchKind);
			paging.setUserno(login.getUserno());
		}
		paging.setSearch(search);
		paging.setSearchKind(searchKind);
		paging.setUserno(login.getUserno());
		List<Board> list2 = boardService.userbyrecommList(paging);
		if( list2 == null ) {
			model.addAttribute("curPage", curPage);
			model.addAttribute("paging", paging);
			model.addAttribute("param", login);
			logger.debug("paramparamparamparam: {}", login);
			return "board/userbyboardlist";
		}
		model.addAttribute("list2", list2);
		model.addAttribute("paging", paging);
		model.addAttribute("curPage", curPage);
	return "board/userbyrecommlist";
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
