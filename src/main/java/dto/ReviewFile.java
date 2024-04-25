package dto;

public class ReviewFile {
	private int fileNo;
	private int reviewNo;
	private String originName;
	private String storedName;
	public ReviewFile() {}
	public ReviewFile(int fileNo, int reviewNo, String originName, String storedName) {
		super();
		this.fileNo = fileNo;
		this.reviewNo = reviewNo;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "ReviewFile [fileNo=" + fileNo + ", reviewNo=" + reviewNo + ", originName=" + originName
				+ ", storedName=" + storedName + "]";
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getStoredName() {
		return storedName;
	}
	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}
	
}
