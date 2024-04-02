package login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginDAO {
    private static final String NAME_SPACE = "login";

    @Autowired
	@Qualifier(value = "focsSqlSession")
	private SqlSession focsSqlSession;

    public HashMap<String, Object> selectUserByName(HashMap<String, Object> user) {
        return focsSqlSession.selectOne(NAME_SPACE + ".selectUserByName",user);
    }

    public void insertUserLog(HashMap<String, Object> map) {
    	focsSqlSession.insert(NAME_SPACE + ".insertUserLog", map);
    }

    public void insertLogOutLog(HashMap<String, Object> map) {
    	focsSqlSession.insert(NAME_SPACE + ".insertLogOutLog", map);
    }

    public void insertUserEventLog(HashMap<String, Object> map) {
    	focsSqlSession.insert(NAME_SPACE + ".insertUserEventLog", map);
    }
    
    public List<Map<String, Object>> loginProc(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.loginInfo", map);
	}
	
	public List<Map<String, Object>> loginProcMobile(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.loginMobileInfo", map);
	}
	
	public List<Map<String, Object>> loginUserMobile(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.loginUserInfo", map);
	}
	
	public void passwordSave(Map<String, Object> map) throws Exception{
		focsSqlSession.update("login.passwordSave", map);
	}

	/* 사용자관리 */
	public List<Map<String, Object>> usermenList(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.usermenList", map);
	}

	public List<Map<String, Object>> usermenDup(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.usermenDup", map);
	}
	
	public void usermenDel(Map<String, Object> map) throws Exception{
		focsSqlSession.delete("login.usermenDel", map);
	}
	
	public void usermenUpt(Map<String, Object> map) throws Exception{
		focsSqlSession.insert("login.usermenUpt", map);
	}
	
	public void passwordReset(Map<String, Object> map) throws Exception{
		focsSqlSession.update("login.passwordReset", map);
	}
	
	public List<Map<String, Object>> getloginMenuList(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.getloginMenuList", map);
	}
	
	public List<Map<String, Object>> passCh(Map<String, Object> map) throws Exception{
		return focsSqlSession.selectList("login.passCh", map);
	}

	public void insertLoginLog(HashMap<String, Object> map) throws Exception {
		focsSqlSession.insert("login.insertLoginLog", map);
	}

	public void insertLogoutLog(HashMap<String, Object> map) throws Exception {
		focsSqlSession.insert("login.insertLogoutLog", map);
	}

	public void insertUserLog(Map<String, Object> map) throws Exception{
		focsSqlSession.insert("login.insertUserLog" , map);
	}

}
