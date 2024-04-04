package user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDao {
    @Autowired
    @Qualifier(value = "session")
    private SqlSession session;
    public int getUsrListCount(HashMap<String, Object> reqMap) {
        return (Integer) session.selectOne("userInfo.getUsrListCount", reqMap);
    }

    public List<Map<String, Object>> userList(HashMap<String, Object> reqMap) {
        return session.selectList("userInfo.getUserList", reqMap);
    }

    public void insertUser(HashMap<String, Object> reqMap) {
        session.insert("userInfo.insertUserInfo", reqMap);
    }

    public HashMap<String, Object> selectUserId(HashMap<String, Object> reqMap) {
        return (HashMap<String, Object>) session.selectOne("userInfo.findUserId", reqMap);
    }

    public void updateUser(HashMap<String, Object> reqMap) {
        session.update("userInfo.updateUserInfo", reqMap);
    }

    public void deleteUser(HashMap<String, Object> reqMap) {
        session.delete("userInfo.deleteUserInfo", reqMap);
    }

    public List<Map<String, Object>> userBoardList(HashMap<String, Object> reqMap) {
        return session.selectList("userInfo.getUserBoardList", reqMap);
    }
}
