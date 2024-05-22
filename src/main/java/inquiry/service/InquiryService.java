package inquiry.service;

import java.util.ArrayList;
import java.util.List;

import dto.Inquiry;
import user.dto.User;

public interface InquiryService {

	public User getUserByNickName(String touser);
	
	public int insertInquiry(Inquiry inquiry);

	public List<Inquiry> getListByUserno(int userNo);

	public int deleteByInquiryNo(int inquiryNo);

	public int answerUpdateByAnswer(Inquiry answerInquiry);

	public List<Inquiry> getListBySendUser(int sendUser);

	public int updateInquiry(Inquiry inquiry);

	public List<Inquiry> getListByManagerNo(int i);

	public List<Inquiry> getAllInquiries();


}
