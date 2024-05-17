package report.dto;

import java.util.Date;

public class BoardReport {
	private int boardReportNo;
	private int boardNo;
	private int userNo;
	private int reportTypeNo;
	private String reportContent;
	private Date reportDate;
	private int managerNo;
	private String resultContent;
	private String result;
	private Date resultDate;
	private String reportType;
	private String content;
	private String nickname;
	public BoardReport() {}

	public BoardReport(int boardReportNo, int boardNo, int userNo, int reportTypeNo, String reportContent, Date reportDate, int managerNo, String resultContent, String result, Date resultDate, String resultType, String content, String nickname) {
		this.boardReportNo = boardReportNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.reportTypeNo = reportTypeNo;
		this.reportContent = reportContent;
		this.reportDate = reportDate;
		this.managerNo = managerNo;
		this.resultContent = resultContent;
		this.result = result;
		this.resultDate = resultDate;
		this.reportType = reportType;
		this.content = content;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "BoardReport{" +
				"boardReportNo=" + boardReportNo +
				", boardNo=" + boardNo +
				", userNo=" + userNo +
				", reportTypeNo=" + reportTypeNo +
				", reportContent='" + reportContent + '\'' +
				", reportDate=" + reportDate +
				", managerNo=" + managerNo +
				", resultContent='" + resultContent + '\'' +
				", result='" + result + '\'' +
				", resultDate=" + resultDate +
				", reportType='" + reportType + '\'' +
				", content='" + content + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}

	public int getBoardReportNo() {
		return boardReportNo;
	}
	public void setBoardReportNo(int boardReportNo) {
		this.boardReportNo = boardReportNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getReportTypeNo() {
		return reportTypeNo;
	}
	public void setReportTypeNo(int reportTypeNo) {
		this.reportTypeNo = reportTypeNo;
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

	public String getreportType() {
		return reportType;
	}

	public void setreportType(String reportType) {
		this.reportType = reportType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
