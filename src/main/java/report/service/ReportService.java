package report.service;

import dto.Item;
import report.dto.ItemReport;
import report.dto.ItemReportType;
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

    /**
     * 상품 신고 분류 전체 조회
     * @return - 조회된 전체 행
     */
	public List<ItemReportType> getItemReportType();

	/**
	 * 상품 번호로 상품 조회
	 * @param itemNo - 상품 번호
	 * @return - 조회된 상품 행
	 */
	public Item getItemByItemNo(int itemNo);

	/**
	 * 상품 신고 .
	 * @param itemReport - 상품 신고 객체
	 * @return - INSERT 결과
	 */
	public int insertItemReport(ItemReport itemReport);

	public List<ItemReport> itemlist();

	public void deleteItemReport(int reportno);
	/**
	 * 신고된글 안보이게 처리*/

	public List<BoardReport> reportboardlist();

	public List<CommReport> reportcommlist();

	public List<ItemReport> reportitemlist();
}
