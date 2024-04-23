package login.dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository("SocialDao")
public interface SocialDao {
    public String getSosid(String id) ;

    public HashMap<String, Object> socialLogin(String sosid);
}
