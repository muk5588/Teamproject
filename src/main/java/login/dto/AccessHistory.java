package login.dto;

import dto.OrderItem;

import java.util.Date;

public class AccessHistory extends OrderItem {
	private int accessNo;
	private int userNo;
	private Date accessDate ,lastAccessDate;
	private  String nickName,name ,gender;
	private  String userId;
	private  int accessCount;
	public AccessHistory() {}

	public AccessHistory(int accessNo, int userNo, Date accessDate, Date lastAccessDate, String nickName, String name, String gender, String userId, int accessCount) {
		this.accessNo = accessNo;
		this.userNo = userNo;
		this.accessDate = accessDate;
		this.lastAccessDate = lastAccessDate;
		this.nickName = nickName;
		this.name = name;
		this.gender = gender;
		this.userId = userId;
		this.accessCount = accessCount;
	}

	@Override
	public String toString() {
		return "AccessHistory{" +
				"accessNo=" + accessNo +
				", userNo=" + userNo +
				", accessDate=" + accessDate +
				", lastAccessDate=" + lastAccessDate +
				", nickName='" + nickName + '\'' +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", userId='" + userId + '\'' +
				", accessCount=" + accessCount +
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

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
