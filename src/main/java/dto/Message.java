package dto;

import java.util.Date;

public class Message {
	private String sender;
	private int messageNo;
	private int sendUserNo;
	private int toUserNo;
	private String content;
	private Date sendDate;
	private String read="N";
	private String save="N";
	public Message() {}
	public Message(String sender, int messageNo, int sendUserNo, int toUserNo, String content, Date sendDate, String read,
			String save) {
		super();
		this.sender = sender;
		this.messageNo = messageNo;
		this.sendUserNo = sendUserNo;
		this.toUserNo = toUserNo;
		this.content = content;
		this.sendDate = sendDate;
		this.read = read;
		this.save = save;
	}
	@Override
	public String toString() {
		return "Message [sender=" + sender + ", messageNo=" + messageNo + ", sendUserNo=" + sendUserNo + ", toUserNo="
				+ toUserNo + ", content=" + content + ", sendDate=" + sendDate + ", read=" + read + ", save=" + save
				+ "]";
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public int getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}
	public int getSendUserNo() {
		return sendUserNo;
	}
	public void setSendUserNo(int sendUserNo) {
		this.sendUserNo = sendUserNo;
	}
	public int getToUserNo() {
		return toUserNo;
	}
	public void setToUserNo(int toUserNo) {
		this.toUserNo = toUserNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	
}
