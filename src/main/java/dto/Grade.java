package dto;

public class Grade {
	private int gradeNo;
	private String gradeName;
	public Grade() {}
	public Grade(int gradeNo, String gradeName) {
		super();
		this.gradeNo = gradeNo;
		this.gradeName = gradeName;
	}
	@Override
	public String toString() {
		return "Grade [gradeNo=" + gradeNo + ", gradeName=" + gradeName + "]";
	}
	public int getGradeNo() {
		return gradeNo;
	}
	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	
}
