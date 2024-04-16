package user.service;

import user.dto.UserDTO;

import java.util.List;

public interface UserService {

	public void userInsert(UserDTO dto);

    public List<UserDTO> userList();

    public UserDTO userDetail(UserDTO userid);

    public void userUpdate(UserDTO dto);

    public void userDelete(UserDTO dto);

    public int passChk(UserDTO dto);

    public int idChk(UserDTO dto);
    
    /**
     * 이메일 인증 서비스
     * @param email - 입력받은 이메일
     * @return - 생성된 인증 번호
     */
	public int checkEmail(String email);

    public UserDTO findUserId(UserDTO dto);

    public void updateUserpw(UserDTO dto);

    public int findUserpw(UserDTO dto);
}
