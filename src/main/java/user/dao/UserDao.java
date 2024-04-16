package user.dao;

import org.springframework.stereotype.Repository;
import user.dto.UserDTO;

import java.util.List;

@Repository("UserDao")
public interface UserDao {
    //sessionSession : PreparedStatement와 표현 방법이 다를뿐 같은 기능을 한다.
    //Autowired : 메모리에 올려둔 주소들이 자동으로 연결 됨

    
    public void userInsert(UserDTO dto);


    public List<UserDTO> userList();


    public UserDTO userDetail(UserDTO userno);

    
    public void userUpdate(UserDTO dto);

    
    public void userDelete(UserDTO dto);

    public int passChk(UserDTO dto);

    public int idChk(UserDTO dto);

    public UserDTO findUserid(UserDTO dto);

    public int findUserpw(UserDTO dto);

    public void updateUserpw(UserDTO dto);
}
