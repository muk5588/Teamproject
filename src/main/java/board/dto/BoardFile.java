package board.dto;

public class BoardFile {
	private int fileNo;
	private int boardNo;
	private String originName;
	private String storedName;
	public BoardFile() {}
	public BoardFile(int fileNo, int boardNo, String originName, String storedName) {
		super();
		this.fileNo = fileNo;
		this.boardNo = boardNo;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "BoardFile [fileNo=" + fileNo + ", boardNo=" + boardNo + ", originName=" + originName + ", storedName="
				+ storedName + "]";
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
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
