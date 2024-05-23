package report.dao;

import org.springframework.stereotype.Repository;

import dto.Item;
import report.dto.ItemReport;
import report.dto.ItemReportType;
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

    public void deleteReport(int reportno);

    public void deleteCommReport(int reportno);

	public List<ItemReportType> getItemReportType();

	public Item getItemByItemNo(int itemNo);

	public int insertItemReport(ItemReport itemReport);

	public List<ItemReport> itemlist();

	public void deleteItemReport(int reportno);

    public List<BoardReport> reportboardlist();

    public List<CommReport> reportcommlist();

    public List<ItemReport> reportitemlist();
}
