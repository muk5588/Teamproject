package message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.Message;
import message.dao.MessageDao;
import user.dto.User;

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
	public List<Message> getListByUserno(int userNo) {
		return messageDao.getListByUserno(userNo);
	}//getListByUserno(userNo)

	//test용 
	@Override
	public int deleteByMessageNo(ArrayList<Integer> messageNo) {
		return messageDao.deleteByMessageNo(messageNo);
	}

	@Override
	public int saveUpdateBySave(Message saveMessage) {
		return messageDao.saveUpdateBySave(saveMessage);
	}

	
}
