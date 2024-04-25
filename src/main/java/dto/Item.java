package dto;

import java.util.Date;

public class Item {
	
	private int itemNo;
	private int userNo;
	private String itemName;
	private Date createDate;
	private int price;
	private int remain;
	public Item() {}
	public Item(int itemNo, int userNo, String itemName, Date createDate, int price, int remain) {
		super();
		this.itemNo = itemNo;
		this.userNo = userNo;
		this.itemName = itemName;
		this.createDate = createDate;
		this.price = price;
		this.remain = remain;
	}
	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", userNo=" + userNo + ", itemName=" + itemName + ", createDate=" + createDate
				+ ", price=" + price + ", remain=" + remain + "]";
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	
	
	
}
