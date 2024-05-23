package grade.service;

import grade.dto.Grade;
import user.dto.User;

import java.util.List;
import java.util.Map;

public interface GradeService {
    public List<Grade> gradeList();

    public Grade gradeDetail(Grade grade);

    public void gradeDelete(Grade grade);

    public void gradeUpdate(Grade grade);

    public void gradeInsert(Grade grade);

    /**
     * 자동 등급 업그레이드 ( 자정에 실행 )
     * @param user - 등급업을 위한 List<User> 객체 
     * @return
     */
	public int autoGradeUpdater2(List<User> user);

	/**
	 * 유저의 누적 접속일, 글쓰기 횟수 조회
	 * @return
	 */
	public List<Map<String, Object>> getUserWriteCountAndaccessCount();

	public int autoGradeUpdater3(List<User> user3);
}
