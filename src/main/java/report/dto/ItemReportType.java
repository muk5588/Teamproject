package report.dto;

public class ItemReportType {
	private int itemReportNo;
	private String itemReport;
	public ItemReportType() {}
	public int getItemReportNo() {
		return itemReportNo;
	}
	public void setItemReportNo(int itemReportNo) {
		this.itemReportNo = itemReportNo;
	}
	public String getItemReport() {
		return itemReport;
	}
	public void setItemReport(String itemReport) {
		this.itemReport = itemReport;
	}
	@Override
	public String toString() {
		return "ItemReportType [itemReportNo=" + itemReportNo + ", itemReport=" + itemReport + "]";
	}
	public ItemReportType(int itemReportNo, String itemReport) {
		super();
		this.itemReportNo = itemReportNo;
		this.itemReport = itemReport;
	}
}
