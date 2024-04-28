package dto;

public class Coupon {
	private int couponNo;
	private int userNo;
	private String couponName;
	private int salePer;
	private String use;
	public Coupon() {}
	public Coupon(int couponNo, int userNo, String couponName, int salePer, String use) {
		super();
		this.couponNo = couponNo;
		this.userNo = userNo;
		this.couponName = couponName;
		this.salePer = salePer;
		this.use = use;
	}
	@Override
	public String toString() {
		return "Coupon [couponNo=" + couponNo + ", userNo=" + userNo + ", couponName=" + couponName + ", salePer="
				+ salePer + ", use=" + use + "]";
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getSalePer() {
		return salePer;
	}
	public void setSalePer(int salePer) {
		this.salePer = salePer;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}
	
}
