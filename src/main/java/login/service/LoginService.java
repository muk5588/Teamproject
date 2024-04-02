package login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

	List<Map<String, Object>>  loginProc(Map<String, Object> map) throws Exception;
	List<Map<String, Object>>  loginProcMobile(Map<String, Object> map) throws Exception;
	List<Map<String, Object>>  loginUserMobile(Map<String, Object> map) throws Exception;
	List<Map<String, Object>>  passCh(Map<String, Object> map) throws Exception;
	void  passwordSave(Map<String, Object> map) throws Exception;
	/* 사용자관리 */
	List<Map<String, Object>>  usermenList(Map<String, Object> map) throws Exception;
	List<Map<String, Object>>  usermenDup(Map<String, Object> map) throws Exception;
	int usermenDel(Map<String, Object> map) throws Exception;
	int usermenUpt(Map<String, Object> map) throws Exception;

	void passwordReset(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>>  getloginMenuList(Map<String, Object> map) throws Exception;

	void insertUserLog(Map<String, Object> map) throws Exception;
}
