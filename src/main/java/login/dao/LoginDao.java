package login.dao;

import org.springframework.stereotype.Repository;
import user.dto.UserDTO;

@Repository("LoginDao")
public interface LoginDao {
//	public UserDTO loginProc(UserDTO dto);


	public int selectCntIdPw(UserDTO dto);

	public int selectNoByIdPw(UserDTO dto);

	public UserDTO selectByNo(int loginno);

}
