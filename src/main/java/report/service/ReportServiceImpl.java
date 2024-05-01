package report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.dao.ReportDao;
import report.dto.BoardReport;
import report.dto.BoardReportType;
import report.dto.CommReport;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService{
@Autowired
ReportDao reportDao;
    @Override
    public List<BoardReportType> reportType() {
        return reportDao.reportType();
    }

    @Override
    public void reportBoard(BoardReport boardReport) {
        reportDao.reportBoard(boardReport);
    }

    @Override
    public List<BoardReportType> commReportType() {
        return reportDao.commReportType();
    }

    @Override
    public void reportComm(CommReport commReport) {
        reportDao.reportComm(commReport);
    }
}
