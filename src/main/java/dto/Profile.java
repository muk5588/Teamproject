package dto;

public class Profile {
	private int no;
	private int userNo;
	private String originName;
	private String storedName;
	private String use;
	public Profile() {}
	public Profile(int no, int userNo, String originName, String storedName, String use) {
		super();
		this.no = no;
		this.userNo = userNo;
		this.originName = originName;
		this.storedName = storedName;
		this.use = use;
	}
	@Override
	public String toString() {
		return "Profile [no=" + no + ", userNo=" + userNo + ", originName=" + originName + ", storedName=" + storedName
				+ ", use=" + use + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	
	
}
