package user.dao;

import org.springframework.stereotype.Repository;
import user.dto.User;
import util.Paging;

import java.util.List;

@Repository("UserDao")
public interface UserDao {
    //sessionSession : PreparedStatement와 표현 방법이 다를뿐 같은 기능을 한다.
    //Autowired : 메모리에 올려둔 주소들이 자동으로 연결 됨

    
    public void userInsert(User dto);


    public List<User> userList();


    public User userDetail(User userno);

    
    public void userUpdate(User dto);

    
    public void userDelete(User dto);

    public int passChk(User dto);

    public int idChk(User dto);

    public User findUserid(User dto);

    public User findUserpw(User dto);

    public void updateUserpw(User dto);

	public int nickChk(User dto);

	public void userBlack(User dto);

}
