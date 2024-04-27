package login.dao;

import org.springframework.stereotype.Repository;
import user.dto.User;

@Repository("SocialDao")
public interface SocialDao {
    public String getSosid(String id) ;
    
    public void socialJoin(User dto);

    public User socialLogin(String socid);

	public void insertAccessHistory(User dto);
}
