package dto;

import java.util.Date;

public class Basket {
	private int basketNo;
	private int userNo;
	private int itemNo;
	private int quantity;
	private Date additionalDate;
	public Basket() {}
	public Basket(int basketNo, int userNo, int itemNo, int quantity, Date additionalDate) {
		super();
		this.basketNo = basketNo;
		this.userNo = userNo;
		this.itemNo = itemNo;
		this.quantity = quantity;
		this.additionalDate = additionalDate;
	}
	@Override
	public String toString() {
		return "Basket [basketNo=" + basketNo + ", userNo=" + userNo + ", itemNo=" + itemNo + ", quantity=" + quantity
				+ ", additionalDate=" + additionalDate + "]";
	}
	public int getBasketNo() {
		return basketNo;
	}
	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getAdditionalDate() {
		return additionalDate;
	}
	public void setAdditionalDate(Date additionalDate) {
		this.additionalDate = additionalDate;
	}
	
}
