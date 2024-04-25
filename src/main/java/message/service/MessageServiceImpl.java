package message.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import message.dao.MessageDao;
import user.dto.User;

@Service
public class MessageServiceImpl implements MessageService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageDao messageDao;
	
	@Override
	public User getUserByNickName(String touser) {
		return null;
	}//getUserByNickName(touser)
	
}
