package login.service;

import login.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.dto.UserDTO;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public UserDTO loginProc(UserDTO dto) {
        return loginDao.loginProc(dto);
    }
}
