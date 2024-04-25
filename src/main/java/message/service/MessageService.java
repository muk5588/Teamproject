package message.service;

import user.dto.User;

public interface MessageService {

	/**
	 * 닉네임으로 유저의 정보를 조회하여 반환한다.
	 * @param touser
	 * @return
	 */
	public User getUserByNickName(String touser);

}
