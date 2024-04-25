package login.service;

import user.dto.User;

public interface LoginService {
	public boolean login(User dto);


	public int getLoginNo(User dto);

	public User info(int loginno);

}
