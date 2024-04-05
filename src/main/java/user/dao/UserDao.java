package user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import user.dto.UserDTO;
import user.service.UserService;

import java.util.List;

@Repository("UserDao")
public interface UserDao {
    //sessionSession : PreparedStatement와 표현 방법이 다를뿐 같은 기능을 한다.
    //Autowired : 메모리에 올려둔 주소들이 자동으로 연결 됨

    
    public void userInsert(UserDTO dto);


    public List<UserDTO> userList();


    public UserDTO userDetail(UserDTO userid);

    
    public void userUpdate(UserDTO dto);

    
    public void userDelete(int userno);

}
