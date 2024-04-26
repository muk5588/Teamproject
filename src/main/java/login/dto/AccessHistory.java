package login.dto;

import java.util.Date;

public class AccessHistory {
	private int accessNo;
	private int userNo;
	private Date accessDate;
	private  String nickName;
	private  String userId;
	public AccessHistory() {}

	public AccessHistory(int accessNo, int userNo, Date accessDate, String nickName, String userId) {
		this.accessNo = accessNo;
		this.userNo = userNo;
		this.accessDate = accessDate;
		this.nickName = nickName;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "AccessHistory{" +
				"accessNo=" + accessNo +
				", userNo=" + userNo +
				", accessDate=" + accessDate +
				", nickName='" + nickName + '\'' +
				", userId='" + userId + '\'' +
				'}';
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}