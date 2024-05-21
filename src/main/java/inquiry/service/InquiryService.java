package inquiry.service;

import java.util.ArrayList;
import java.util.List;

import inquiry.dto.Inquiry;
import user.dto.User;

public interface InquiryService {

	public User getUserByNickName(String touser);
	
	public int insertInquiry(Inquiry inquiry);

	public List<Inquiry> getListByUserno(int userNo);

	public int deleteByInquiryNo(ArrayList<Integer> inquiryNo);

	public int answerUpdateByAnswer(Inquiry answerInquiry);
	
	public List<Inquiry> getListBySendUser(int sendUser);



}
