package login.dao;

import login.dto.AccessHistory;
import org.springframework.stereotype.Repository;
import user.dto.User;

import java.util.List;

@Repository("LoginDao")
public interface LoginDao {
//	public UserDTO loginProc(UserDTO dto);


	public int selectCntIdPw(User dto);

	public int selectNoByIdPw(User dto);

	public User selectByNo(int loginno);


	public int historyCheck(int loginno);

	public void insertAccessHistory(int loginno);

	public void updateAccessHistory(int loginno);

    public List<AccessHistory> loginHistory();
}
