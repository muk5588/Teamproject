package login.service;

import login.dto.AccessHistory;
import user.dto.User;

import java.util.List;

public interface LoginService {
	/**
	 * 로그인 여부 확인
	 * @param dto
	 * @return
	 */
	public int login(User dto);




	public User info(User dto);


	public int historyCheck(int loginno);

	public void insertAccessHistory(int loginno);

	public void updateAccessHistory(int loginno);

	public List<AccessHistory> loginHistory();
}
