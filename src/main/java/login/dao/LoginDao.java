package login.dao;

import org.springframework.stereotype.Repository;
import user.dto.UserDTO;

@Repository("LoginDao")
public interface LoginDao {
    UserDTO loginProc(UserDTO dto);
}
