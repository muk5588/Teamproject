package board.dto;

public class Category {
	private int categoryNo;
	private int gradeNo;
	private String categoryName;
	public Category() {}
	public Category(int categoryNo, int gradeNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.gradeNo = gradeNo;
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [categoryNo=" + categoryNo + ", gradeNo=" + gradeNo + ", categoryName=" + categoryName + "]";
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
