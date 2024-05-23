package message.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import dto.Message;
import user.dto.User;
import util.Paging;

@Repository("MessageDao")
public interface MessageDao {

	public int userCntByNickName(String touser);

	public User getUserByNickName(String touser);

	public int insertMessage(Message message);

	public List<Message> getListByUserno(@Param("paging")Paging paging, @Param("userNo")int userNo);

	public int deleteByMessageNo(@Param("arr")ArrayList<Integer> messageNo);

	public int saveUpdateBySave(Message saveMessage);

	public List<Message> getListBySendUser(@Param("paging")Paging paging, @Param("userNo")int userNo);

	public int getPaging(@Param("paging")Paging paging, @Param("userNo")int userNo);

	public int messageSendUserPaging(@Param("paging")Paging paging, @Param("userNo")int userNo);

	public int readChk(int messageNo);

	public int cleanupExpiredMessages();

	
}
