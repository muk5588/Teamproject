package report.controller;

import board.dto.Board;
import board.service.BoardService;
import comment.dto.Comment;
import dto.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import report.dto.*;
import report.service.ReportService;
import user.dto.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    BoardService boardService;
    @Autowired
    ReportService reportService;


    @GetMapping("/boardReport")
    public String boardReport(Model model, int boardno,@RequestParam(value ="categoryNo",required = false)String categoryNo) {
        List<BoardReportType> reportTypeList = reportService.reportType();
        Board board = boardService.viewByBoardNo(boardno);
        if( categoryNo != null ) {
        	model.addAttribute("categoryNo", categoryNo);
        }
        model.addAttribute("board", board);
        model.addAttribute("list", reportTypeList);
        return "report/boardReport";
    }
    @PostMapping("/boardReport")
    public String boardReport(BoardReport boardReport, HttpSession session
    		,@RequestParam(value ="categoryNo",required = false)String categoryNo){
    	String URL = "redirect:/";
    	if(categoryNo == null) {
    	}else {
    		URL = "redirect:/board/list?categoryNo="+categoryNo;
    	}
        int userNo = (int)session.getAttribute("isLogin");
        boardReport.setUserNo(userNo);
        reportService.reportBoard(boardReport);
        return URL;
    }
    @GetMapping("/commentReport")
    public String commentReport(Model model, int commno,@RequestParam(value ="boardNo",required = false)String boardNo
    		,@RequestParam(value ="categoryNo",required = false)String categoryNo) {
        List<BoardReportType> commReportTypeList = reportService.commReportType();
        Comment comment = boardService.commentByBoardNo(commno);
        if( boardNo != null && categoryNo != null ) {
        	logger.debug("boardNo : {}",boardNo);
        	model.addAttribute("boardNo", boardNo);
        	model.addAttribute("categoryNo", categoryNo);
        }
        model.addAttribute("comment", comment);
        model.addAttribute("comment", comment);
        model.addAttribute("list", commReportTypeList);
        return "report/commentReport";
    }
    @PostMapping("/commentReport")
    public String commentReport(CommReport commReport, HttpSession session,@RequestParam(value ="boardNo",required = false)String boardNo
    		,@RequestParam(value ="categoryNo",required = false)String categoryNo){
    	String URL = "/";
    	if( boardNo != null && categoryNo != null) {
    		URL = "redirect:/board/view?boardNo=" + boardNo;
    		URL += "&categoryNo=" + categoryNo;
    	}
        int userNo = (int)session.getAttribute("isLogin");
        commReport.setUserNo(userNo);
        reportService.reportComm(commReport);
        return URL;
    }
    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardReport> boardlist = reportService.boardlist();
        List<CommReport> commlist = reportService.commlist();
        List<ItemReport> itemlist = reportService.itemlist();
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("commlist", commlist);
        model.addAttribute("itemlist", itemlist);
        return "report/reportlist";
    }
    @RequestMapping("deleteReport")
    public String deleteReport(int reportno) {
        reportService.deleteReport(reportno);
        return "redirect: ./list";
    }
    @RequestMapping("deleteCommReport")
    public String deleteCommReport(int reportno) {
        reportService.deleteCommReport(reportno);
        return "redirect: ./list";
    }
    
    @RequestMapping("deleteItemReport")
    public String deleteItemReport(int reportno) {
    	logger.debug("~~~~~~~~~~~~~~~~~~~~~");
    	logger.debug("~~~~~~~~~~~~~~~~~~~~~reportno: {}",reportno);
    	reportService.deleteItemReport(reportno);
    	return "redirect: ./list";
    }
    
    //상품 신고하기
    @GetMapping("/itemReport")
    public String itemReport(int itemNo, Model model) {
    	logger.debug("상품 신고하기");
    	logger.debug("itemNo : {}", itemNo);
    	List<ItemReportType> reportType = reportService.getItemReportType();
    	model.addAttribute("reportType", reportType);
    	Item item = reportService.getItemByItemNo(itemNo);
    	logger.debug("item : {}", item);
    	model.addAttribute("item", item);
    	return "report/itemReport";
    }
    
    @PostMapping("/itemReport")
    public String itemReportProc(Model model,HttpSession session, 
    		ItemReport itemReport ) {
    	logger.debug("itemReport : {}", itemReport);
    	User user = (User) session.getAttribute("dto1");
    	if( user == null) {
    		return "redirect:/login";
    	}
    	itemReport.setUserNo(user.getUserno());
    	int res = reportService.insertItemReport(itemReport);
    	
    	return "redirect:../shop/"; //자신의 신고내역으로 변경
    }
    @RequestMapping("/userByReportList")
    public String userByReportList(Model model,HttpSession session) {
        User user = (User) session.getAttribute("dto1");
        int userNo = user.getUserno();
        List<BoardReport> boardlist = reportService.userbyboardlist(userNo);
        List<CommReport> commlist = reportService.userbycommlist(userNo);
        List<ItemReport> itemlist = reportService.userbyitemlist(userNo);
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("commlist", commlist);
        model.addAttribute("itemlist", itemlist);
        return "report/reportlist";
    }
    
    
}
