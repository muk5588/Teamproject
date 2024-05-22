package inquiry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dto.Inquiry;
import user.dto.User;

@Repository("InquiryDao")
public interface InquiryDao {

	public int userCntByNickName(String touser);

	public User getUserByNickName(String touser);

	public int insertInquiry(Inquiry inquiry);

	public List<Inquiry> getListByUserno(int userNo);

	public int deleteByInquiryNo(int inquiryNoList);

	public int answerUpdateByAnswer(Inquiry answerInquiry);

	public List<Inquiry> getListBySendUser(int sendUser);

	public int updateInquiry(Inquiry inquiry);

	public List<Inquiry> getListByManagerNo(int i);

	public List<Inquiry> getAllInquiries();
	
}
