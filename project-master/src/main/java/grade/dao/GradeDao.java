package grade.dao;

import grade.dto.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("GradeDao")
public interface GradeDao {
	public List<Grade> gradeList();

	public Grade gradeDetail(Grade gradeno);

	public void gradeDelete(Grade grade);

	public void gradeUpdate(Grade grade);

	public void gradeInsert(Grade grade);
}
