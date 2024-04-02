package login.service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.dao.LoginDAO;
import user.dao.UserDao;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LoginDAO loginDAO;

	@Override
	public List<Map<String, Object>> loginProc(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)loginDAO.loginProc(map);
	}
	
	@Override
	public List<Map<String, Object>> loginProcMobile(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)loginDAO.loginProcMobile(map);
	}
	
	@Override
	public List<Map<String, Object>> loginUserMobile(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)loginDAO.loginUserMobile(map);
	}
	
	@Override
	public void passwordSave(Map<String, Object> map) throws Exception {
		loginDAO.passwordSave(map);
	}
	
	/* 사용자관리 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> usermenList(Map<String, Object> map) throws Exception {
		loginDAO.usermenList(map); 
		return (List<Map<String, Object>>) map.get("result");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> usermenDup(Map<String, Object> map) throws Exception {
		loginDAO.usermenDup(map); 
		return (List<Map<String, Object>>) map.get("result");
	}

	@Override
	public int usermenDel(Map<String, Object> map) throws Exception {
		loginDAO.usermenDel(map); 
		return 0;
	}
	
	@Override
	public int usermenUpt(Map<String, Object> map) throws Exception {
		loginDAO.usermenUpt(map); 
		return 0;
	}
	
	@Override
	public void passwordReset(Map<String, Object> map) throws Exception {
		loginDAO.passwordReset(map); 
	}
	
	@Override
	public List<Map<String, Object>> getloginMenuList(Map<String, Object> map) throws Exception {
		return loginDAO.getloginMenuList(map);
	}
	
	@Override
	public List<Map<String, Object>> passCh(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>)loginDAO.passCh(map);
	}

	@Override
	public void insertUserLog(Map<String, Object> map) throws Exception{
		loginDAO.insertUserLog(map);
	}

}
