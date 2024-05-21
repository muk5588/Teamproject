package inquiry.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import inquiry.dto.Inquiry;
import user.dto.User;

@Repository("InquiryDao")
public interface InquiryDao {

	public int userCntByNickName(String touser);

	public User getUserByNickName(String touser);

	public int insertInquiry(Inquiry inquiry);

	public List<Inquiry> getListByUserno(int userNo);

	public int deleteByInquiryNo(ArrayList<Integer> inquiryNo);

	public int answerUpdateByAnswer(Inquiry answerInquiry);

	public List<Inquiry> getListBySendUser(int sendUser);

}
