package user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import user.Service.UserService;
import user.dto.UserDTO;

import java.util.HashMap;
import java.util.List;
@Repository
public class UserDao implements UserService {
    @Autowired
    private SqlSession session;
    //sessionSession : PreparedStatement와 표현 방법이 다를뿐 같은 기능을 한다.
    //Autowired : 메모리에 올려둔 주소들이 자동으로 연결 됨

    @Override
    public void userInsert(UserDTO dto) {
        session.insert("userInfo.userInsert",dto);
    }

    @Override
    public List<UserDTO> userList() {
        //customer.mapper라는 맵퍼에서 id가 list인 곳을 찾는다
        return session.selectList("userInfo.userList");
    }

    @Override
    public UserDTO userDetail(int userid) {
        return session.selectOne("userInfo.userDetail", userid);
    }

    @Override
    public void userUpdate(UserDTO dto) {
       session.update("userInfo.userUpdate",dto);
    }

    @Override
    public void userDelete(int userid) {
        session.delete("userInfo.userDelete",userid);
    }

}
