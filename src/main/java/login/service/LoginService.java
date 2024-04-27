package login.service;

import login.dto.AccessHistory;
import user.dto.User;

import java.util.List;

public interface LoginService {
	/**
	 * 로그인 여부 확인
	 * @param dto
	 * @return
	 */
	public int login(User dto);




	public User info(User dto);


	public List<AccessHistory> loginHistory();

	/**
	 * 유저 접속로그 추가
	 * @param login - 유저 정보를 담은 DTO 객체
	 */
	public void insertAccessHistory(User login);
}
