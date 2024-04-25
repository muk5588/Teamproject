package board.dto;

public class delete {

	private String result;
	public delete() {	}
	@Override
	public String toString() {
		return "delete [result=" + result + "]";
	}
	public delete(String result) {
		super();
		this.result = result;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
