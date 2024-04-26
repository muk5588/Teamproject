package message.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.Message;
import user.dto.User;

@Repository("MessageDao")
public interface MessageDao {

	public int userCntByNickName(String touser);

	public User getUserByNickName(String touser);

	public int insertMessage(Message message);

	public List<Message> getListByUserno(int userNo);

	public int deleteByMessageNo(@Param("arr")ArrayList<Integer> messageNo);

	public int saveUpdateBySave(Message saveMessage);

	
}
