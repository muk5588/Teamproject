package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginDao dao;

    @Override
    public boolean member_insert(MemberVo vo) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public MemberVo member_select(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemberVo member_login(HashMap<String, String> map) {
        return dao.member_login(map);
    }

    @Override
    public boolean member_id_check(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean member_update(MemberVo vo) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean member_delete(String id) {
        // TODO Auto-generated method stub
        return false;
    }
}
