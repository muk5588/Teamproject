package board.dto;

public class Good {
	private int userNo;
	private int boardNo;
	public Good() {}
	public Good(int userNo, int boardNo) {
		super();
		this.userNo = userNo;
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "Good [userNo=" + userNo + ", boardNo=" + boardNo + "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
}
