package report.service;

import report.dto.BoardReport;
import report.dto.BoardReportType;
import report.dto.CommReport;

import java.util.List;

public interface ReportService {
    public List<BoardReportType> reportType();

    public void reportBoard(BoardReport boardReport);

    public List<BoardReportType> commReportType();

    public void reportComm(CommReport commReport);

    public List<BoardReport> boardlist();

    public List<CommReport> commlist();

    public void deleteReport(int reportno);

    public void deleteCommReport(int reportno);
}
