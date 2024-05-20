package inquiry.dto;

public class InquiryFile {

	private int fileNo;
	private int inquiryNo;
	private String origin_Name;
	private String stored_Name;
	
	public InquiryFile() {}

	public InquiryFile(int fileNo, int inquiryNo, String origin_Name, String stored_Name) {
		super();
		this.fileNo = fileNo;
		this.inquiryNo = inquiryNo;
		this.origin_Name = origin_Name;
		this.stored_Name = stored_Name;
	}

	@Override
	public String toString() {
		return "InquiryFile [fileNo=" + fileNo + ", inquiryNo=" + inquiryNo + ", origin_Name=" + origin_Name
				+ ", stored_Name=" + stored_Name + "]";
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

	public String getOrigin_Name() {
		return origin_Name;
	}

	public void setOrigin_Name(String origin_Name) {
		this.origin_Name = origin_Name;
	}

	public String getStored_Name() {
		return stored_Name;
	}

	public void setStored_Name(String stored_Name) {
		this.stored_Name = stored_Name;
	}
	
	
	
}
