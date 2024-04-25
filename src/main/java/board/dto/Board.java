package board.dto;

import java.util.Date;

public class Board {
	private int boardNo;
	private int categoryNo;
	private int userNo;
	private String nickName;
	private String title;
	private String content;
	private Date createDate;
	private Date updateDate;
	private int boardView;
	public Board() {}
	public Board(int boardNo, int categoryNo, int userNo, String nickName, String title, String content,
			Date createDate, Date updateDate, int boardView) {
		super();
		this.boardNo = boardNo;
		this.categoryNo = categoryNo;
		this.userNo = userNo;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.boardView = boardView;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", categoryNo=" + categoryNo + ", userNo=" + userNo + ", nickName="
				+ nickName + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", boardView=" + boardView + "]";
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getBoardView() {
		return boardView;
	}
	public void setBoardView(int boardView) {
		this.boardView = boardView;
	}
	
}
