package grade.service;

import grade.dto.Grade;

import java.util.List;

public interface GradeService {
    List<Grade> gradeList();

    Grade gradeDetail(Grade grade);

    void gradeDelete(Grade grade);

    void gradeUpdate(Grade grade);

    void gradeInsert(Grade grade);
}
