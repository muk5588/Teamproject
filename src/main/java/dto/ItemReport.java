package dto;

import java.util.Date;

public class ItemReport {
	private int reportNo;
	private int itemNo;
	private int userNo;
	private int itemReportNo;
	private Date reportDate;
	private String reportContent;
	private Date resultDate;
	private String result;
	private String reportType;
	private String itemName;
	private String nickName;
	public ItemReport() {}
	public ItemReport(int reportNo, int itemNo, int userNo, int itemReportNo, Date reportDate, String reportContent,
			Date resultDate, String result, String reportType, String itemName, String nickName) {
		super();
		this.reportNo = reportNo;
		this.itemNo = itemNo;
		this.userNo = userNo;
		this.itemReportNo = itemReportNo;
		this.reportDate = reportDate;
		this.reportContent = reportContent;
		this.resultDate = resultDate;
		this.result = result;
		this.reportType = reportType;
		this.itemName = itemName;
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "ItemReport [reportNo=" + reportNo + ", itemNo=" + itemNo + ", userNo=" + userNo + ", itemReportNo="
				+ itemReportNo + ", reportDate=" + reportDate + ", reportContent=" + reportContent + ", resultDate="
				+ resultDate + ", result=" + result + ", reportType=" + reportType + ", itemName=" + itemName
				+ ", nickName=" + nickName + "]";
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getItemReportNo() {
		return itemReportNo;
	}
	public void setItemReportNo(int itemReportNo) {
		this.itemReportNo = itemReportNo;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Date getResultDate() {
		return resultDate;
	}
	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
