package report.controller;

import board.dto.Board;
import board.service.BoardService;
import comment.dto.Comment;
import dto.Item;
import dto.ItemReportType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import report.dto.BoardReport;
import report.dto.BoardReportType;
import report.dto.CommReport;
import report.service.ReportService;

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
    public String boardReport(Model model, int boardno) {
        List<BoardReportType> reportTypeList = reportService.reportType();
        Board board = boardService.viewByBoardNo(boardno);
        model.addAttribute("board", board);
        model.addAttribute("list", reportTypeList);
        return "report/boardReport";
    }
    @PostMapping("/boardReport")
    public String boardReport(BoardReport boardReport, HttpSession session){
        int userNo = (int)session.getAttribute("isLogin");
        boardReport.setUserNo(userNo);
        reportService.reportBoard(boardReport);
        return "redirect:../board/list";//추후에 자신의 신고내역으로 변경
    }
    @GetMapping("/commentReport")
    public String commentReport(Model model, int commno) {
        List<BoardReportType> commReportTypeList = reportService.commReportType();
        Comment comment = boardService.commentByBoardNo(commno);
        model.addAttribute("comment", comment);
        model.addAttribute("list", commReportTypeList);
        return "report/commentReport";
    }
    @PostMapping("/commentReport")
    public String commentReport(CommReport commReport, HttpSession session){
        int userNo = (int)session.getAttribute("isLogin");
        commReport.setUserNo(userNo);
        reportService.reportComm(commReport);
        return "redirect:../board/list";//추후에 자신의 신고내역으로 변경
    }
    @RequestMapping("/list")
    public String list(Model model) {
        List<BoardReport> boardlist = reportService.boardlist();
        List<CommReport> commlist = reportService.commlist();
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("commlist", commlist);
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
    
    //상품 신고하기
    @RequestMapping("/itemreport")
    public void itemReport(int itemNo) {
    	logger.debug("상품 신고하기");
    	logger.debug("itemNo : {}", itemNo);
    	ItemReportType reportType = reportService.getItemReportType();
    	Item item = reportService.getItemByItemNo(itemNo);
    	
    }
    
    
    
}
