package grade.dao;

import grade.dto.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("GradeDao")
public interface GradeDao {
    List<Grade> gradeList();

    Grade gradeDetail(Grade gradeno);

    void gradeDelete(Grade grade);

    void gradeUpdate(Grade grade);

    void gradeInsert(Grade grade);
}
