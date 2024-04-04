package user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import user.dao.UserDao;
import user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserDao userDao ;

    @Override
    public List<UserDTO> userList() {
        return userDao.userList();
    }

    @Override
    public UserDTO userDetail(int userid) {
        return userDao.userDetail(userid);
    }
    public void userInsert(UserDTO dto) {
        userDao.userInsert(dto);
    }

    @Override
    public void userUpdate(UserDTO dto) {
        userDao.userUpdate(dto);
    }

    @Override
    public void userDelete(int userid) {
        userDao.userDelete(userid);
    }

}



