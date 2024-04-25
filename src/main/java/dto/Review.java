package dto;

import java.util.Date;

public class Review {
	private int reviewNo;
	private int userNo;
	private int itemNo;
	private String reviewTitle;
	private String reviewContent;
	private Date createReviewData;
	private Date updateReviewData;
	public Review() {}
	public Review(int reviewNo, int userNo, int itemNo, String reviewTitle, String reviewContent, Date createReviewData,
			Date updateReviewData) {
		super();
		this.reviewNo = reviewNo;
		this.userNo = userNo;
		this.itemNo = itemNo;
		this.reviewTitle = reviewTitle;
		this.reviewContent = reviewContent;
		this.createReviewData = createReviewData;
		this.updateReviewData = updateReviewData;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", userNo=" + userNo + ", itemNo=" + itemNo + ", reviewTitle="
				+ reviewTitle + ", reviewContent=" + reviewContent + ", createReviewData=" + createReviewData
				+ ", updateReviewData=" + updateReviewData + "]";
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
	public Date getCreateReviewData() {
		return createReviewData;
	}
	public void setCreateReviewData(Date createReviewData) {
		this.createReviewData = createReviewData;
	}
	public Date getUpdateReviewData() {
		return updateReviewData;
	}
	public void setUpdateReviewData(Date updateReviewData) {
		this.updateReviewData = updateReviewData;
	}
	
}
