package inquiry.service;

import java.util.List;

import dto.Inquiry;
import user.dto.User;

public interface InquiryService {

	// 특정 닉네임을 가진 사용자 정보를 반환하는 메서드
	public User getUserByNickName(String touser);
	
	// 문의 정보를 삽입하는 메서드
	public int insertInquiry(Inquiry inquiry);

	// 특정 사용자 번호에 해당하는 문의 목록을 반환하는 메서드
	public List<Inquiry> getListByUserno(int userNo);

	// 문의 번호에 해당하는 문의를 삭제하는 메서드
	public int deleteByInquiryNo(int inquiryNo);

	// 문의 답변을 업데이트하는 메서드
	public int answerUpdateByAnswer(Inquiry answerInquiry);

	// 특정 보낸 사용자 번호에 해당하는 문의 목록을 반환하는 메서드
	public List<Inquiry> getListBySendUser(int sendUser);

	// 문의 정보를 업데이트하는 메서드
	public int updateInquiry(Inquiry inquiry);

	// 특정 매니저 번호에 해당하는 문의 목록을 반환하는 메서드
	public List<Inquiry> getListByManagerNo(int i);

	// 모든 문의 목록을 반환하는 메서드
	public List<Inquiry> getAllInquiries();

}
