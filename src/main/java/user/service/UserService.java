package user.service;

import user.dto.User;
import util.Paging;

import java.util.List;

public interface UserService {

	public void userInsert(User dto);

    public List<User> userList();

    public User userDetail(User userid);

    public void userUpdate(User dto);

    public void userDelete(User dto);

    public int passChk(User dto);

    public int idChk(User dto);
    
    /**
     * 이메일 인증 서비스
     * @param email - 입력받은 이메일
     * @return - 생성된 인증 번호
     */
	public int checkEmail(String email);

    public User findUserId(User dto);

    public void updateUserpw(User dto);

    public User findUserpw(User dto);

	public int nickChk(User dto);

	public void userBlack(User dto);

}
