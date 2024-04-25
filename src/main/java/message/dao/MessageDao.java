package message.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import dto.Message;
import user.dto.User;

@Repository("MessageDao")
public interface MessageDao {

	public int userCntByNickName(String touser);

	public User getUserByNickName(String touser);

	public int insertMessage(Message message);

	public List<Message> getListByUserno(int userNo);
	
}
