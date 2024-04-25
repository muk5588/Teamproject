package dto;

public class OrderItem {
	private int orderItemNo;
	private int orderNo;
	private int itemNo;
	private String itemName;
	private int orderQuantity;
	private int price;
	private int couponNo;
	public OrderItem() {}
	public OrderItem(int orderItemNo, int orderNo, int itemNo, String itemName, int orderQuantity, int price,
			int couponNo) {
		super();
		this.orderItemNo = orderItemNo;
		this.orderNo = orderNo;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.orderQuantity = orderQuantity;
		this.price = price;
		this.couponNo = couponNo;
	}
	@Override
	public String toString() {
		return "OrderItem [orderItemNo=" + orderItemNo + ", orderNo=" + orderNo + ", itemNo=" + itemNo + ", itemName="
				+ itemName + ", orderQuantity=" + orderQuantity + ", price=" + price + ", couponNo=" + couponNo + "]";
	}
	public int getOrderItemNo() {
		return orderItemNo;
	}
	public void setOrderItemNo(int orderItemNo) {
		this.orderItemNo = orderItemNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	
}
