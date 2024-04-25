package dto;

public class ItemFile {
	private int fileNo;
	private int itemNo;
	private String originName;
	private String storedName;
	public ItemFile() {}
	public ItemFile(int fileNo, int itemNo, String originName, String storedName) {
		super();
		this.fileNo = fileNo;
		this.itemNo = itemNo;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "ItemFile [fileNo=" + fileNo + ", itemNo=" + itemNo + ", originName=" + originName + ", storedName="
				+ storedName + "]";
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
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
