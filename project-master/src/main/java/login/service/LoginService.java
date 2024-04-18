package login.service;

import user.dto.UserDTO;

public interface LoginService {
	public boolean login(UserDTO dto);


	public int getLoginNo(UserDTO dto);

	public UserDTO info(int loginno);
}
