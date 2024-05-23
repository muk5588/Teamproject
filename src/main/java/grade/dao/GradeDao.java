package grade.dao;

import grade.dto.Grade;
import user.dto.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository("GradeDao")
public interface GradeDao {
	public List<Grade> gradeList();

	public Grade gradeDetail(Grade gradeno);

	public void gradeDelete(Grade grade);

	public void gradeUpdate(Grade grade);

	public void gradeInsert(Grade grade);

	public List<Map<String, Object>> getUserWriteCountAndaccessCount();

	public int autoGradeUpdater2(@Param("list")List<User> user);
	
	public int autoGradeUpdater3(@Param("list")List<User> user);
}
