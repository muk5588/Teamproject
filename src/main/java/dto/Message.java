package dto;

import java.util.Date;

public class Message {
	private int messageNo;
	private int senduserNo;
	private int touserNo;
	private String content;
	private Date sendDate;
	private char read;
	private char save;
	public Message() {}
	public Message(int messageNo, int senduserNo, int touserNo, String content, Date sendDate, char read, char save) {
		super();
		this.messageNo = messageNo;
		this.senduserNo = senduserNo;
		this.touserNo = touserNo;
		this.content = content;
		this.sendDate = sendDate;
		this.read = read;
		this.save = save;
	}
	@Override
	public String toString() {
		return "Message [messageNo=" + messageNo + ", senduserNo=" + senduserNo + ", touserNo=" + touserNo
				+ ", content=" + content + ", sendDate=" + sendDate + ", read=" + read + ", save=" + save + "]";
	}
	public int getMessageNo() {
		return messageNo;
	}
	public void setMessageNo(int messageNo) {
		this.messageNo = messageNo;
	}
	public int getSenduserNo() {
		return senduserNo;
	}
	public void setSenduserNo(int senduserNo) {
		this.senduserNo = senduserNo;
	}
	public int getTouserNo() {
		return touserNo;
	}
	public void setTouserNo(int touserNo) {
		this.touserNo = touserNo;
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
