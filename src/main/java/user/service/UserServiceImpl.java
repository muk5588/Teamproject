package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import user.dao.UserDao;
import user.dto.UserDTO;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserDTO> userList() {
        return userDao.userList();
    }

    @Override
    public UserDTO userDetail(UserDTO userno) {
        return userDao.userDetail(userno);
    }

    public void userInsert(UserDTO dto) {
        userDao.userInsert(dto);
    }

    @Override
    public void userUpdate(UserDTO dto) {
        userDao.userUpdate(dto);
    }

    @Override
    public void userDelete(UserDTO dto) {
        userDao.userDelete(dto);
    }

}



