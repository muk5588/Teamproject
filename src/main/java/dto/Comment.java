package dto;

import java.util.Date;

public class Comment {
	private int commNo;
	private int boardNo;
	private int userNo;
	private String commContent;
	private Date commDate;
	private Date commUpdateDate;
	public Comment() {}
	public Comment(int commNo, int boardNo, int userNo, String commContent, Date commDate, Date commUpdateDate) {
		super();
		this.commNo = commNo;
		this.boardNo = boardNo;
		this.userNo = userNo;
		this.commContent = commContent;
		this.commDate = commDate;
		this.commUpdateDate = commUpdateDate;
	}
	@Override
	public String toString() {
		return "Comment [commNo=" + commNo + ", boardNo=" + boardNo + ", userNo=" + userNo + ", commContent="
				+ commContent + ", commDate=" + commDate + ", commUpdateDate=" + commUpdateDate + "]";
	}
	public int getCommNo() {
		return commNo;
	}
	public void setCommNo(int commNo) {
		this.commNo = commNo;
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
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}
	public Date getCommDate() {
		return commDate;
	}
	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
	public Date getCommUpdateDate() {
		return commUpdateDate;
	}
	public void setCommUpdateDate(Date commUpdateDate) {
		this.commUpdateDate = commUpdateDate;
	}
	
}
