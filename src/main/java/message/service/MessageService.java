package message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.Message;
import user.dto.User;
import util.Paging;

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
	 * @param paging 
	 * @return - 조회된 전체 행
	 */
	public List<Message> getListByUserno(int userNo, Paging paging);

	/**
	 * 전달받은 messageNo로 메세지 삭제
	 * @param map - Ajax로 전달받은 messageNo ArrayList<Integer>
	 * @return - 영향을 받은 행 수
	 */
	public int deleteByMessageNo(ArrayList<Integer> messageNo);

	/**
	 * AJAX로 통신받은 체크 상태로 저장 여부 업데이트
	 * @param saveMessage - messageno, save ( 'N' | 'Y' ) 를 담은 DTO 객체 
	 * @return - 영향을 받은 행 수
	 */
	public int saveUpdateBySave(Message saveMessage);

	/**
	 * 전달받은 Sender의 번호를 전체 조회
	 * @param sendUser - 현재 로그인한 사람의 userNo
	 * @param paging 
	 * @return 조회된 전체 행
	 */
	public List<Message> getListBySendUser(int sendUser, Paging paging);

	/**
	 * 메세지 페이징
	 * @param paging - 페이징 객체
	 * @param curPage - 현재 페이지
	 * @param userNo - 유저 번호 
	 * @return - 페이징 객체
	 */
	public Paging messagePaging(Paging paging, int curPage, int userNo);

	/**
	 * 보낸 메세지 페이징
	 * @param paging - 페이징 객체
	 * @param curPage - 현재 페이지
	 * @param userNo - 메세지 보낸 유저 번호 
	 * @return - 페이징 객체
	 */
	public Paging messageSendUserPaging(Paging paging, int curPage, int userNo);

	/**
	 * 메세지 읽음
	 * @param messageNo
	 * @return
	 */
	public int readChk(int messageNo);

	/**
	 * 메시지 1달 이후 자동 삭제
	 * @return - 삭제된 메시지 개수
	 */
	public int cleanupExpiredMessages();


}
