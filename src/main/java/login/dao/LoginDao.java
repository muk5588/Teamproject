package login.dao;

import org.springframework.stereotype.Repository;
import user.dto.User;

@Repository("LoginDao")
public interface LoginDao {
//	public UserDTO loginProc(UserDTO dto);


	public int selectCntIdPw(User dto);

	public int selectNoByIdPw(User dto);

	public User selectByNo(int loginno);

}
