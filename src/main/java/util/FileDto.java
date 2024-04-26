package util;

import org.apache.ibatis.type.Alias;

@Alias("FileDto")
public class FileDto {
	
	private int fileno;
	private int boardno;
	private String originName;
	private String storedName;
	public FileDto() {
	}
	public FileDto(int fileno, int boardno, String originName, String storedName) {
		super();
		this.fileno = fileno;
		this.boardno = boardno;
		this.originName = originName;
		this.storedName = storedName;
	}
	@Override
	public String toString() {
		return "FileDto [fileno=" + fileno + ", boardno=" + boardno + ", originName=" + originName + ", storedName="
				+ storedName + "]";
	}
	public int getFileno() {
		return fileno;
	}
	public void setFileno(int fileno) {
		this.fileno = fileno;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
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
