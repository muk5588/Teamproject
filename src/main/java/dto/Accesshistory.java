package dto;

import java.util.Date;

public class Accesshistory {
	private int accessno;
	private int userno;
	private Date accessdate;
	public Accesshistory() {}
	public Accesshistory(int accessno, int userno, Date accessdate) {
		super();
		this.accessno = accessno;
		this.userno = userno;
		this.accessdate = accessdate;
	}
	@Override
	public String toString() {
		return "Accesshistory [accessno=" + accessno + ", userno=" + userno + ", accessdate=" + accessdate + "]";
	}
	public int getAccessno() {
		return accessno;
	}
	public void setAccessno(int accessno) {
		this.accessno = accessno;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public Date getAccessdate() {
		return accessdate;
	}
	public void setAccessdate(Date accessdate) {
		this.accessdate = accessdate;
	}
	
}
