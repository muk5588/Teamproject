package login.service;

import user.dto.User;

public interface LoginService {
	/**
	 * 로그인 여부 확인
	 * @param dto
	 * @return
	 */
	public boolean login(User dto);


	public int getLoginNo(User dto);

	public User info(int loginno);


	public int historyCheck(int loginno);

	public void insertAccessHistory(int loginno);

	public void updateAccessHistory(int loginno);
}
