package report.service;

import dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import report.dao.ReportDao;
import report.dto.*;

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

    @Override
    public List<BoardReport> boardlist() {
        return reportDao.boardlist();
    }

    @Override
    public List<CommReport> commlist() {
        return reportDao.commlist();
    }

    @Override
    public void deleteReport(int reportno) {
        reportDao.deleteReport(reportno);
    }

    @Override
    public void deleteCommReport(int reportno) {
        reportDao.deleteCommReport(reportno);
    }

	@Override
	public List<ItemReportType> getItemReportType() {
		return reportDao.getItemReportType();
	}

	@Override
	public Item getItemByItemNo(int itemNo) {
		return reportDao.getItemByItemNo(itemNo);
	}

	@Override
	public int insertItemReport(ItemReport itemReport) {
		return reportDao.insertItemReport(itemReport);
	}

	@Override
	public List<ItemReport> itemlist() {
		return reportDao.itemlist();
	}

	@Override
	public void deleteItemReport(int reportno) {
		reportDao.deleteItemReport(reportno);
	}

    @Override
    public List<BoardReport> reportboardlist() {
        return reportDao.reportboardlist();
    }

    @Override
    public List<CommReport> reportcommlist() {
        return reportDao.reportcommlist();
    }

    @Override
    public List<ItemReport> reportitemlist() {
        return reportDao.reportitemlist();
    }

    @Override
    public List<BoardReport> userbyboardlist(int userNo) {
        return reportDao.userbyboardlist(userNo);
    }

    @Override
    public List<CommReport> userbycommlist(int userNo) {
        return reportDao.userbycommlist(userNo);    }

    @Override
    public List<ItemReport> userbyitemlist(int userNo) {
        return reportDao.userbyitemlist(userNo);
    }


}
