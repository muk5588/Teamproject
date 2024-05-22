package inquiry.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Inquiry;
import inquiry.dao.InquiryDao;
import user.dto.User;
import util.Paging;

@Service
public class InquiryServiceImpl implements InquiryService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired 
    private InquiryDao inquiryDao;
    

    // 특정 닉네임을 가진 사용자 정보를 반환하는 메서드
    @Override
    public User getUserByNickName(String touser) {
        if (inquiryDao.userCntByNickName(touser) > 0) {
            return inquiryDao.getUserByNickName(touser);
        }
        return null;
    }

    // 문의 정보를 삽입하는 메서드
    @Override
    public int insertInquiry(Inquiry inquiry) {
        return inquiryDao.insertInquiry(inquiry);
    }

    // 특정 사용자 번호에 해당하는 문의 목록을 반환하는 메서드
    @Override
    public List<Inquiry> getListByUserno(int userNo) {
        return inquiryDao.getListByUserno(userNo);
    }

    // 문의 번호에 해당하는 문의를 삭제하는 메서드
    @Override
    public int deleteByInquiryNo(int inquiryNoList) {
        return inquiryDao.deleteByInquiryNo(inquiryNoList);
    }

    // 문의 답변을 업데이트하는 메서드
    @Override
    public int answerUpdateByAnswer(Inquiry answerInquiry) {
        return inquiryDao.answerUpdateByAnswer(answerInquiry);
    }

    // 특정 보낸 사용자 번호에 해당하는 문의 목록을 반환하는 메서드
    @Override
    public List<Inquiry> getListBySendUser(int sendUser) {
        return inquiryDao.getListBySendUser(sendUser);
    }

    // 문의 정보를 업데이트하는 메서드
    @Override
    public int updateInquiry(Inquiry inquiry) {
        return inquiryDao.updateInquiry(inquiry);
    }

    // 특정 매니저 번호에 해당하는 문의 목록을 반환하는 메서드
    @Override
    public List<Inquiry> getListByManagerNo(int i) {
        return inquiryDao.getListByManagerNo(i);
    }

    // 모든 문의 목록을 반환하는 메서드
    @Override
    public List<Inquiry> getAllInquiries(Paging paging) {
        return inquiryDao.getAllInquiries(paging);
    }

	@Override
	public Paging getPaging(int curPage, Paging paging) {
		int totalCount = inquiryDao.getCount(paging);
		Paging pagingres = new Paging(totalCount, curPage);
		return pagingres;
	}


}
