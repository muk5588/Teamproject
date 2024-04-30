package report.controller;

import board.dto.Board;
import board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import report.dto.BoardReportType;
import report.service.ReportService;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    BoardService boardService;
    @Autowired
    ReportService reportService;
    @RequestMapping("/boardReport")
    public String boardReport(Model model, int boardno) {
        List<BoardReportType> reportTypeList = reportService.reportType();
        Board board = boardService.viewByBoardNo(boardno);
        model.addAttribute("board", board);
        model.addAttribute("list", reportTypeList);
        return "report/boardReport";
    }

}
