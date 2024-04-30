package report.service;

import report.dto.BoardReport;
import report.dto.BoardReportType;

import java.util.List;

public interface ReportService {
    public List<BoardReportType> reportType();

    void reportBoard(BoardReport boardReport);
}
