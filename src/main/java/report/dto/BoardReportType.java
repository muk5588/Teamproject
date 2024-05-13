package report.dto;

import org.apache.ibatis.type.Alias;

public class BoardReportType {
	
	private int reportTypeNo;
	private String reportType;
	public BoardReportType() {}
	public BoardReportType(int reportTypeNo, String reportType) {
		super();
		this.reportTypeNo = reportTypeNo;
		this.reportType = reportType;
	}
	@Override
	public String toString() {
		return "BoardReportType [reportTypeNo=" + reportTypeNo + ", reportType=" + reportType + "]";
	}
	public int getReportTypeNo() {
		return reportTypeNo;
	}
	public void setReportTypeNo(int reportTypeNo) {
		this.reportTypeNo = reportTypeNo;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
