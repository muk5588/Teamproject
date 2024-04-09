package grade.service;

import grade.dto.Grade;

import java.util.List;

public interface GradeService {
    public List<Grade> gradeList();

    public Grade gradeDetail(Grade grade);

    public void gradeDelete(Grade grade);

    public void gradeUpdate(Grade grade);

    public void gradeInsert(Grade grade);
}
