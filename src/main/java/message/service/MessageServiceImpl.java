package message.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Message;
import message.dao.MessageDao;
import user.dto.User;
import util.Paging;

@Service
public class MessageServiceImpl implements MessageService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageDao messageDao;
	
	@Override
	public User getUserByNickName(String touser) {
		if( messageDao.userCntByNickName(touser) > 0) {
			return messageDao.getUserByNickName(touser);
		}
		return null;
	}//getUserByNickName(touser)

	@Override
	public int insertMessage(Message message) {
		return messageDao.insertMessage(message);
	}//insertMessage(message)

	@Override
	public List<Message> getListByUserno(int userNo, Paging paging) {
		return messageDao.getListByUserno(paging,userNo);
	}//getListByUserno(userNo)
 
	@Override
	public int deleteByMessageNo(ArrayList<Integer> messageNo) {
		return messageDao.deleteByMessageNo(messageNo);
	}//deleteByMessageNo(ArrayList<Integer> messageNo)

	@Override
	public int saveUpdateBySave(Message saveMessage) {
		return messageDao.saveUpdateBySave(saveMessage);
	}//saveUpdateBySave(saveMessage)

	@Override
	public List<Message> getListBySendUser(int sendUser, Paging paging) {
		return messageDao.getListBySendUser(paging,sendUser);
	}//getListBySendUser(int sendUser)

	@Override
	public Paging messagePaging(Paging paging, int curPage, int userNo) {
		int totalCount = messageDao.getPaging(paging,userNo);
		Paging pagingres = new Paging(totalCount, curPage);
		return pagingres;
	}

	@Override
	public Paging messageSendUserPaging(Paging paging, int curPage, int userNo) {
		int totalCount = messageDao.messageSendUserPaging(paging,userNo);
		Paging pagingres = new Paging(totalCount, curPage);
		return pagingres;
	}

	@Override
	public int readChk(int messageNo) {
		return messageDao.readChk(messageNo);
	}

	@Override
	public int cleanupExpiredMessages() {
		return messageDao.cleanupExpiredMessages();
	}

	
}
