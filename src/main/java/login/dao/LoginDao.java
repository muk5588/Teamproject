package login.dao;

import login.dto.AccessHistory;
import org.springframework.stereotype.Repository;
import user.dto.User;

import java.util.List;

@Repository("LoginDao")
public interface LoginDao {
//	public UserDTO loginProc(UserDTO dto);




	public int selectNoByIdPw(User dto);

	public User selectByNo(User dto);


	public int historyCheck(int loginno);

	public void insertAccessHistory(User login);

    public List<AccessHistory> loginHistory();
}
