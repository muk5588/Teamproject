package inquiry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Inquiry;
import inquiry.dao.InquiryDao;
import user.dto.User;

@Service
public class InquiryServiceImpl implements InquiryService{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired InquiryDao inquiryDao;
	

	@Override
	public User getUserByNickName(String touser) {
		if( inquiryDao.userCntByNickName(touser) > 0) {
			return inquiryDao.getUserByNickName(touser);
		}
		return null;
	}

	@Override
	public int insertInquiry(Inquiry inquiry) {
		return inquiryDao.insertInquiry(inquiry);
	}

	@Override
	public List<Inquiry> getListByUserno(int userNo) {
		return inquiryDao.getListByUserno(userNo);
	}

	@Override
	public int deleteByInquiryNo(int inquiryNoList) {
		return inquiryDao.deleteByInquiryNo(inquiryNoList);
	}

	@Override
	public int answerUpdateByAnswer(Inquiry answerInquiry) {
		return inquiryDao.answerUpdateByAnswer(answerInquiry);
	}

	@Override
	public List<Inquiry> getListBySendUser(int sendUser) {
		return inquiryDao.getListBySendUser(sendUser);
	}

	@Override
	public int updateInquiry(Inquiry inquiry) {
		return inquiryDao.updateInquiry(inquiry);
	}

	@Override
	public List<Inquiry> getListByManagerNo(int i) {
		return inquiryDao.getListByManagerNo(i);
	}

	@Override
	public List<Inquiry> getAllInquiries() {
		return inquiryDao.getAllInquiries();
	}


}
