package grade.service;

import grade.dao.GradeDao;
import grade.dto.Grade;
import user.dto.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<Grade> gradeList() {
        return gradeDao.gradeList();
    }

    @Override
    public Grade gradeDetail(Grade gradeno) {
        return gradeDao.gradeDetail(gradeno);
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

	@Override
	public int autoGradeUpdater2(List<User> user2) {
		return gradeDao.autoGradeUpdater2(user2);
	}

	@Override
	public List<Map<String, Object>> getUserWriteCountAndaccessCount() {
		return gradeDao.getUserWriteCountAndaccessCount();
	}

	@Override
	public int autoGradeUpdater3(List<User> user3) {
		return gradeDao.autoGradeUpdater3(user3);
	}
}
