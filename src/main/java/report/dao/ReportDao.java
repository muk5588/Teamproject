package report.dao;

import org.springframework.stereotype.Repository;
import report.dto.BoardReport;
import report.dto.BoardReportType;

import java.util.List;
@Repository("ReportDao")
public interface ReportDao {
    public List<BoardReportType> reportType();

    public void reportBoard(BoardReport boardReport);
}
