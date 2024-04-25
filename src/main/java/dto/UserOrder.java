package dto;

import java.util.Date;

public class UserOrder {
	private int orderNo;
	private int userNo;
	private String userName;
	private String phone;
	private String pay;
	private int totalPrice;
	private int postCode;
	private String address1;
	private String address2;
	private String method;
	private int paraMount;
	private Date orderDate;
	public UserOrder() {}
	public UserOrder(int orderNo, int userNo, String userName, String phone, String pay, int totalPrice, int postCode,
			String address1, String address2, String method, int paraMount, Date orderDate) {
		super();
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.userName = userName;
		this.phone = phone;
		this.pay = pay;
		this.totalPrice = totalPrice;
		this.postCode = postCode;
		this.address1 = address1;
		this.address2 = address2;
		this.method = method;
		this.paraMount = paraMount;
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "UserOrder [orderNo=" + orderNo + ", userNo=" + userNo + ", userName=" + userName + ", phone=" + phone
				+ ", pay=" + pay + ", totalPrice=" + totalPrice + ", postCode=" + postCode + ", address1=" + address1
				+ ", address2=" + address2 + ", method=" + method + ", paraMount=" + paraMount + ", orderDate="
				+ orderDate + "]";
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getParaMount() {
		return paraMount;
	}
	public void setParaMount(int paraMount) {
		this.paraMount = paraMount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
