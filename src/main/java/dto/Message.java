package dto;

import java.util.Date;

public class Message {
	private int messageNo;
	private int sendUserNo;
	private int toUserNo;
	private String content;
	private Date sendDate;
	private char read='N';
	private char save='N';
	public Message() {}
	public Message(int messageNo, int sendUserNo, int toUserNo, String content, Date sendDate, char read, char save) {
		super();
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
		return "Message [messageNo=" + messageNo + ", sendUserNo=" + sendUserNo + ", toUserNo=" + toUserNo
				+ ", content=" + content + ", sendDate=" + sendDate + ", read=" + read + ", save=" + save + "]";
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
	public char getRead() {
		return read;
	}
	public void setRead(char read) {
		this.read = read;
	}
	public char getSave() {
		return save;
	}
	public void setSave(char save) {
		this.save = save;
	}

	
}
