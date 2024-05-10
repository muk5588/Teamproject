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
	private String address;
	private String detailAddress;
	private String extraAddress;
	private String method;
	private int paraMount;
	private Date orderDate;
	private String impUid;
	private String merchantUid;
	public UserOrder() {}
	public UserOrder(int orderNo, int userNo, String userName, String phone, String pay, int totalPrice, int postCode,
			String address, String detailAddress, String extraAddress, String method, int paraMount, Date orderDate,
			String impUid, String merchantUid) {
		super();
		this.orderNo = orderNo;
		this.userNo = userNo;
		this.userName = userName;
		this.phone = phone;
		this.pay = pay;
		this.totalPrice = totalPrice;
		this.postCode = postCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.extraAddress = extraAddress;
		this.method = method;
		this.paraMount = paraMount;
		this.orderDate = orderDate;
		this.impUid = impUid;
		this.merchantUid = merchantUid;
	}
	@Override
	public String toString() {
		return "UserOrder [orderNo=" + orderNo + ", userNo=" + userNo + ", userName=" + userName + ", phone=" + phone
				+ ", pay=" + pay + ", totalPrice=" + totalPrice + ", postCode=" + postCode + ", address=" + address
				+ ", detailAddress=" + detailAddress + ", extraAddress=" + extraAddress + ", method=" + method
				+ ", paraMount=" + paraMount + ", orderDate=" + orderDate + ", impUid=" + impUid + ", merchantUid="
				+ merchantUid + "]";
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getExtraAddress() {
		return extraAddress;
	}
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
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
	public String getImpUid() {
		return impUid;
	}
	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}
	public String getMerchantUid() {
		return merchantUid;
	}
	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}
	
}
