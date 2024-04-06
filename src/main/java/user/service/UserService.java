package user.service;

import user.dto.UserDTO;

import java.util.List;

public interface UserService {

    void userInsert(UserDTO dto);

    List<UserDTO> userList();

    UserDTO userDetail(UserDTO userid);

    void userUpdate(UserDTO dto);

    void userDelete(int userno);
}
