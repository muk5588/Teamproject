package dto;

import java.util.Date;

public class ReviewReport {
	private int reportNo;
	private int userNo;
	private int reviewNo;
	private String reportContent;
	private Date reportDate;
	private int managerNo;
	private String resultContent;
	private String result;
	private Date resultDate;
	public ReviewReport() {}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	public String getResultContent() {
		return resultContent;
	}
	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	@Override
	public String toString() {
		return "ReviewReport [reportNo=" + reportNo + ", userNo=" + userNo + ", reviewNo=" + reviewNo
				+ ", reportContent=" + reportContent + ", reportDate=" + reportDate + ", managerNo=" + managerNo
				+ ", resultContent=" + resultContent + ", result=" + result + ", resultDate=" + resultDate + "]";
	}
	public ReviewReport(int reportNo, int userNo, int reviewNo, String reportContent, Date reportDate, int managerNo,
			String resultContent, String result, Date resultDate) {
		super();
		this.reportNo = reportNo;
		this.userNo = userNo;
		this.reviewNo = reviewNo;
		this.reportContent = reportContent;
		this.reportDate = reportDate;
		this.managerNo = managerNo;
		this.resultContent = resultContent;
		this.result = result;
		this.resultDate = resultDate;
	}
}
