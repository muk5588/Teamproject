package user.Service;

import user.dto.UserDTO;

import java.util.List;

public interface UserService {
    void userInsert(UserDTO dto);

    //목록 조회
    List<UserDTO> userList();

    //상세(1건) 조회
    UserDTO userDetail(int id);

    //고객 정보 변경 저장
    void userUpdate(UserDTO vo);

    //고객 정보 삭제
    void userDelete(int id);
}
