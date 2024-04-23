package login.dao;

import org.springframework.stereotype.Repository;
import user.dto.UserDTO;

@Repository("SocialDao")
public interface SocialDao {
    public String getSosid(String id) ;
    
    public void socialJoin(UserDTO dto);

    public UserDTO socialLogin(String socid);
}
