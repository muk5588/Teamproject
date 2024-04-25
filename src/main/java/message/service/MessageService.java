package message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Message;
import user.dto.User;

public interface MessageService {

	/**
	 * 닉네임으로 유저의 정보를 조회하여 반환한다.
	 * @param touser
	 * @return
	 */
	public User getUserByNickName(String touser);

	/**
	 * 내용, 보내는사람, 받는사람 정보를 Message TB에 삽입
	 * @param message - content, toUser, sendUser 정보를 담은 DTO 객체
	 * @return - 영향을 받은 행 개수 0:실패 | 1:성공
	 */
	public int insertMessage(Message message);

	/**
	 * 유저 번호로 메세지 조회
	 * @param userNo - 유저 번호
	 * @return - 조회된 전체 행
	 */
	public List<Message> getListByUserno(int userNo);

	/**
	 * 전달받은 messageNo로 메세지 삭제
	 * @param param - Ajax로 전달받은 messageNo ArrayList<Integer>
	 * @return - 영향을 받은 행 수
	 */
	public int deleteByMessageNo(HashMap<String, Object> param);

}
