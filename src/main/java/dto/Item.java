package dto;

import java.util.Date;

public class Item {
	
	private int itemNo;
	private int userNo;
	private String itemName;
	private Date createDate;
	private String itemComm;
	private int price;
	private int remain;
	private int titleImg;
	private int fileNo;
	private String originName;
	private String storedName;
	public Item() {}
	public Item(int itemNo, int userNo, String itemName, Date createDate, String itemComm, int price, int remain,
			int titleImg, int fileNo, String originName, String storedName) {
		super();
		this.itemNo = itemNo;
		this.userNo = userNo;
		this.itemName = itemName;
		this.createDate = createDate;
		this.itemComm = itemComm;
		this.price = price;
		this.remain = remain;
		this.titleImg = titleImg;
		this.fileNo = fileNo;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", userNo=" + userNo + ", itemName=" + itemName + ", createDate=" + createDate
				+ ", itemComm=" + itemComm + ", price=" + price + ", remain=" + remain + ", titleImg=" + titleImg
				+ ", fileNo=" + fileNo + ", originName=" + originName + ", storedName=" + storedName + "]";
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
	public String getItemComm() {
		return itemComm;
	}
	public void setItemComm(String itemComm) {
		this.itemComm = itemComm;
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
	public int getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(int titleImg) {
		this.titleImg = titleImg;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
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
	
	
}
