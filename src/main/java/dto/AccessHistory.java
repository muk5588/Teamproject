package dto;

import java.util.Date;

public class AccessHistory {
	private int accessNo;
	private int userNo;
	private Date accessDate;
	public AccessHistory() {}
	public AccessHistory(int accessNo, int userNo, Date accessDate) {
		super();
		this.accessNo = accessNo;
		this.userNo = userNo;
		this.accessDate = accessDate;
	}
	@Override
	public String toString() {
		return "AccessHistory [accessNo=" + accessNo + ", userNo=" + userNo + ", accessDate=" + accessDate + "]";
	}
	public int getAccessNo() {
		return accessNo;
	}
	public void setAccessNo(int accessNo) {
		this.accessNo = accessNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public Date getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}
	
}
