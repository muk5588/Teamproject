package login.service;

import login.dao.LoginDao;
import login.dto.AccessHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.User;

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
    public boolean login(User dto) {

        int result = loginDao.selectCntIdPw(dto);

        if(result > 0) {
            return true;     //로그인 성공
        }else {

        return false;
        }

    }

    @Override
    public int getLoginNo(User dto) {

        return loginDao.selectNoByIdPw(dto);
    }

    @Override
    public User info(int loginno) {
        return loginDao.selectByNo(loginno);
    }

    @Override
    public int historyCheck(int loginno) {
        return loginDao.historyCheck(loginno);
    }

    @Override
    public void insertAccessHistory(int loginno) {
        loginDao.insertAccessHistory(loginno);
    }

    @Override
    public void updateAccessHistory(int loginno) {
        loginDao.updateAccessHistory(loginno);
    }

    @Override
    public List<AccessHistory> loginHistory(){
     return loginDao.loginHistory();
    }


}
