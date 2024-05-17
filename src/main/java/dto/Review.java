package dto;

import java.util.Date;

public class Review {
	private int reviewNo;
	private int userNo;
	private String nickname;
	private int itemNo;
	private String reviewTitle;
	private String reviewContent;
	private Date createReviewDate;
	private Date updateReviewDate;
	public Review() {}
	public Review(int reviewNo, int userNo, String nickname, int itemNo, String reviewTitle, String reviewContent,
			Date createReviewDate, Date updateReviewDate) {
		super();
		this.reviewNo = reviewNo;
		this.userNo = userNo;
		this.nickname = nickname;
		this.itemNo = itemNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.createReviewDate = createReviewDate;
		this.updateReviewDate = updateReviewDate;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", userNo=" + userNo + ", nickname=" + nickname + ", itemNo=" + itemNo
				+ ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent + ", createReviewDate="
				+ createReviewDate + ", updateReviewDate=" + updateReviewDate + "]";
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getCreateReviewDate() {
		return createReviewDate;
	}
	public void setCreateReviewDate(Date createReviewDate) {
		this.createReviewDate = createReviewDate;
	}
	public Date getUpdateReviewDate() {
		return updateReviewDate;
	}
	public void setUpdateReviewDate(Date updateReviewDate) {
		this.updateReviewDate = updateReviewDate;
	}
	
	
}
