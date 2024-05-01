package login.service;

import login.dao.LoginDao;
import login.dto.AccessHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.User;
import util.Paging;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

//    @Override
//    public boolean loginProc(UserDTO dto) {
//        return loginDao.loginProc(dto);
//    }

    @Override
    public int login(User dto) {

        int result = loginDao.selectNoByIdPw(dto);

        return result;

    }


    @Override
    public User info(User dto) {
        return loginDao.selectByNo(dto);
    }

    @Override
    public List<AccessHistory> loginHistory(){
     return loginDao.loginHistory();
    }


	@Override
	public void insertAccessHistory(User login) {
		loginDao.insertAccessHistory(login);
	}

    @Override
    public List<AccessHistory> history(Paging paging) {
        return loginDao.history(paging);
    }


}
