package report.dto;

import java.util.Date;

public class CommReport {
	private int commReportNo;
	private int commReportTypeNo;
	private int commNo;
	private int userNo;
	private String content;
	private Date commReportDate;
	private int managerNo;
	private String resultContent;
	private String result;
	private Date resultDate;
	private String commReport;
	private String commContent;
	private String nickname;
	public CommReport() {}

	public CommReport(int commReportNo, int commReportTypeNo, int commNo, int userNo, String content, Date commReportDate, int managerNo, String resultContent, String result, Date resultDate, String commReport, String commContent, String nickname) {
		this.commReportNo = commReportNo;
		this.commReportTypeNo = commReportTypeNo;
		this.commNo = commNo;
		this.userNo = userNo;
		this.content = content;
		this.commReportDate = commReportDate;
		this.managerNo = managerNo;
		this.resultContent = resultContent;
		this.result = result;
		this.resultDate = resultDate;
		this.commReport = commReport;
		this.commContent = commContent;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "CommReport{" +
				"commReportNo=" + commReportNo +
				", commReportTypeNo=" + commReportTypeNo +
				", commNo=" + commNo +
				", userNo=" + userNo +
				", content='" + content + '\'' +
				", commReportDate=" + commReportDate +
				", managerNo=" + managerNo +
				", resultContent='" + resultContent + '\'' +
				", result='" + result + '\'' +
				", resultDate=" + resultDate +
				", commReport='" + commReport + '\'' +
				", commContent='" + commContent + '\'' +
				", nickname='" + nickname + '\'' +
				'}';
	}

	public int getCommReportNo() {
		return commReportNo;
	}
	public void setCommReportNo(int commReportNo) {
		this.commReportNo = commReportNo;
	}
	public int getCommReportTypeNo() {
		return commReportTypeNo;
	}
	public void setCommReportTypeNo(int commReportTypeNo) {
		this.commReportTypeNo = commReportTypeNo;
	}
	public int getCommNo() {
		return commNo;
	}
	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommReportDate() {
		return commReportDate;
	}
	public void setCommReportDate(Date commReportDate) {
		this.commReportDate = commReportDate;
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

	public String getCommReport() {
		return commReport;
	}

	public void setCommReport(String commReport) {
		this.commReport = commReport;
	}

	public String getCommContent() {
		return commContent;
	}

	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
