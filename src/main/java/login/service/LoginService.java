package login.service;

import user.dto.UserDTO;

public interface LoginService {
    UserDTO loginProc(UserDTO dto);
}
