package report.dto;

public class CommReportType {
	private int commReportTypeNo;
	private String commReport;
	public CommReportType() {}
	public CommReportType(int commReportTypeNo, String commReport) {
		super();
		this.commReportTypeNo = commReportTypeNo;
		this.commReport = commReport;
	}
	@Override
	public String toString() {
		return "CommReportType [commReportTypeNo=" + commReportTypeNo + ", commReport=" + commReport + "]";
	}
	public int getCommReportTypeNo() {
		return commReportTypeNo;
	}
	public void setCommReportTypeNo(int commReportTypeNo) {
		this.commReportTypeNo = commReportTypeNo;
	}
	public String getCommReport() {
		return commReport;
	}
	public void setCommReport(String commReport) {
		this.commReport = commReport;
	}
	
}
