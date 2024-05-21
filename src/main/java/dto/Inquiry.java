package dto;

import java.util.Date;

public class Inquiry {
	
	private int inquiryNo;
	private int userNo;
	private String inquiryDetail;
	private Date inquiryDate;
	private int managerNo;
	private String answer;
	private String complete = "N";
	private Date answerDate;
	public Inquiry() {}
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getInquiryDetail() {
		return inquiryDetail;
	}
	public void setInquiryDetail(String inquiryDetail) {
		this.inquiryDetail = inquiryDetail;
	}
	public Date getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(Date inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getComplete() {
		return complete;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	@Override
	public String toString() {
		return "Inquiry [inquiryNo=" + inquiryNo + ", userNo=" + userNo + ", inquiryDetail=" + inquiryDetail
				+ ", inquiryDate=" + inquiryDate + ", managerNo=" + managerNo + ", answer=" + answer + ", complete="
				+ complete + ", answerDate=" + answerDate + "]";
	}
	public Inquiry(int inquiryNo, int userNo, String inquiryDetail, Date inquiryDate, int managerNo, String answer,
			String complete, Date answerDate) {
		super();
		this.inquiryNo = inquiryNo;
		this.userNo = userNo;
		this.inquiryDetail = inquiryDetail;
		this.inquiryDate = inquiryDate;
		this.managerNo = managerNo;
		this.answer = answer;
		this.complete = complete;
		this.answerDate = answerDate;
	}
}
