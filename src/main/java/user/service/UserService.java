package user.service;

import user.dto.User;
import util.UserPaging;

import java.util.List;

public interface UserService {

	public void userInsert(User dto);

    public List<User> userList();

    public User userDetail(User userid);

    public void userUpdate(User dto);

    public void userDelete(User dto);



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

	public void blackUser(Long no);

	public void whiteUser(Long no);

	/**
	 * 유저 전체(관리자용) 리스트 페이지 페이징
	 * @param paging - 페이징 객체
	 * @param curPage - 현재 페이지
	 * @return - 페이징 객체
	 */
	public UserPaging getUserListPaging(UserPaging paging, int curPage);

	/**
	 * 유저 전체 조회(페이징 적용)
	 * @param paging - 페이징 객체
	 * @return - 조회된 유저 행
	 */
	public List<User> userPagingList(UserPaging paging);

}
