package user.dto;

import login.service.SocialServiceImpl;

public class EmailCheck extends SocialServiceImpl {

	private String email;
	private int authNum;
	
	public EmailCheck() {}

	public EmailCheck(String email, int authNum) {
		super();
		this.email = email;
		this.authNum = authNum;
	}

	@Override
	public String toString() {
		return "EmailCheck [email=" + email + ", authNum=" + authNum + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthNum() {
		return authNum;
	}

	public void setAuthNum(int authNum) {
		this.authNum = authNum;
	}
	
}
