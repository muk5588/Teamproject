package grade.service;

import grade.dao.GradeDao;
import grade.dto.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<Grade> gradeList() {
        return gradeDao.gradeList();
    }

    @Override
    public Grade gradeDetail(Grade grade) {
        return gradeDao.gradeDetail(grade);
    }

    @Override
    public void gradeDelete(Grade grade) {
        gradeDao.gradeDelete(grade);
    }

    @Override
    public void gradeUpdate(Grade grade) {
        gradeDao.gradeUpdate(grade);
    }

    @Override
    public void gradeInsert(Grade grade) {
        gradeDao.gradeInsert(grade);
    }
}
