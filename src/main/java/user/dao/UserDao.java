package user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("UserDao")
public class UserDao {
    @Autowired
    @Qualifier(value = "session")
    private SqlSession session;
    /* 사용자 목록 조회 */
    public List<Map<String, Object>> getUserList(Map<String, Object> map) throws Exception{
        return session.selectList("userInfo.getUserList", map);
    }

    /* 사용자 ID 중복체크 카운트 */
    public int getuserListCount(Map<String, Object> map) throws Exception{
        return (Integer) session.selectOne("userInfo.getuserListCount", map);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Object> findUserId(HashMap<String, Object> params) throws Exception {
        return (HashMap<String, Object>) session.selectOne("userInfo.findUserId", params);
    }

    /* 사용자 등록 */
    public void CreateUser(Map<String, Object> map) throws Exception {
        session.insert("userInfo.insertUserInfo", map);
    }

    /* 사용자 수정 */
    public void updateUser(Map<String, Object> map) throws Exception {
        session.update("userInfo.updateUserInfo", map);
    }

    /* 사용자 삭제 */
    public void deleteUser(Map<String, Object> map) throws Exception {
        session.delete("userInfo.deleteUserInfo", map);
    }
    /* 사용자 권한 목록 조회 */
    public List<Map<String, Object>> getUserRoleList(Map<String, Object> map) throws Exception{
        return session.selectList("userInfo.getUserRoleList", map);
    }


}
