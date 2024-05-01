package report.controller;

import board.dto.Board;
import board.service.BoardService;
import comment.dto.Comment;
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
        model.addAttribute("board", comment);
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
}
