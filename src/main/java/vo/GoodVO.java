package vo;

public class GoodVO {
	private int totalRecomm;
	private int isRecomm;
	public GoodVO() {}
	public GoodVO(int totalRecomm, int isRecomm) {
		super();
		this.totalRecomm = totalRecomm;
		this.isRecomm = isRecomm;
	}
	@Override
	public String toString() {
		return "GoodVo [totalRecomm=" + totalRecomm + ", isRecomm=" + isRecomm + "]";
	}
	public int getTotalRecomm() {
		return totalRecomm;
	}
	public void setTotalRecomm(int totalRecomm) {
		this.totalRecomm = totalRecomm;
	}
	public int getIsRecomm() {
		return isRecomm;
	}
	public void setIsRecomm(int isRecomm) {
		this.isRecomm = isRecomm;
	}
	
}
