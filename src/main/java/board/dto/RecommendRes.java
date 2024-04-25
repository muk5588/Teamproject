package board.dto;

public class RecommendRes {
	
	private int totalRecommend;
	private boolean isRecommend;
	
	public RecommendRes() {}

	public RecommendRes(int totalRecommend, boolean isRecommend) {
		super();
		this.totalRecommend = totalRecommend;
		this.isRecommend = isRecommend;
	}
	@Override
	public String toString() {
		return "RecommendRes [totalRecommend=" + totalRecommend + ", isRecommend=" + isRecommend + "]";
	}

	public int getTotalRecommend() {
		return totalRecommend;
	}

	public void setTotalRecommend(int totalRecommend) {
		this.totalRecommend = totalRecommend;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	
}
