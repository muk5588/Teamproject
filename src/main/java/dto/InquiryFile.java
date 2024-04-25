package dto;

public class InquiryFile {
	private int fileNo;
	private int inquiryNo;
	private String originName;
	private String storedName;
	public InquiryFile() {}
	public InquiryFile(int fileNo, int inquiryNo, String originName, String storedName) {
		super();
		this.fileNo = fileNo;
		this.inquiryNo = inquiryNo;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "InquiryFile [fileNo=" + fileNo + ", inquiryNo=" + inquiryNo + ", originName=" + originName
				+ ", storedName=" + storedName + "]";
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
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
