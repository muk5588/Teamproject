package report.dao;

import org.springframework.stereotype.Repository;
import report.dto.BoardReport;
import report.dto.BoardReportType;
import report.dto.CommReport;

import java.util.List;
@Repository("ReportDao")
public interface ReportDao {
    public List<BoardReportType> reportType();

    public void reportBoard(BoardReport boardReport);

    public List<BoardReportType> commReportType();

    public void reportComm(CommReport commReport);

    public List<BoardReport> boardlist();

    public List<CommReport> commlist();
}
