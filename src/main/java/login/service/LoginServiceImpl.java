package login.service;

import login.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.UserDTO;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

//    @Override
//    public boolean loginProc(UserDTO dto) {
//        return loginDao.loginProc(dto);
//    }

    @Override
    public boolean login(UserDTO dto) {

        int result = loginDao.selectCntIdPw(dto);

        if(result > 0) {
            return true;     //로그인 성공
        }else {

        return false;
        }

    }

    @Override
    public int getLoginNo(UserDTO dto) {

        return loginDao.selectNoByIdPw(dto);
    }

    @Override
    public UserDTO info(int loginno) {
        return loginDao.selectByNo(loginno);
    }
}
