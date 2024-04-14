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
}
