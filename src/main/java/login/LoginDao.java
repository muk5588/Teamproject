package login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
@Repository
public class LoginDao implements LoginService{
    @Autowired
    private SqlSession sql; //이미 만들어둔 SqlSession을 이용하기 위해 Autowired를 사용한다

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
        return sql.selectOne("member.mapper.com.test.www.login", map);
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
