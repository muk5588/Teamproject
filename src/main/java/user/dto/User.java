package user.dto;


public class User {
	private String name, userid, userpw, nickname, gender,email, address,detailAddress,extraAddress,phone, suserno,black	;
	private int userno, postcode;
	private int gradeno=1;

	public User() {
	}

	public User(String name, String userid, String userpw, String nickname,String black, String gender, String email, String address, String detailAddress, String extraAddress, String phone, String suserno, int userno, int postcode, int gradeno, int reportno) {
		this.name = name;
		this.userid = userid;
		this.userpw = userpw;
		this.nickname = nickname;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.detailAddress = detailAddress;
		this.extraAddress = extraAddress;
		this.phone = phone;
		this.suserno = suserno;
		this.userno = userno;
		this.postcode = postcode;
		this.gradeno = gradeno;
		this.black = black;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"name='" + name + '\'' +
				", userid='" + userid + '\'' +
				", userpw='" + userpw + '\'' +
				", nickname='" + nickname + '\'' +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				", detailAddress='" + detailAddress + '\'' +
				", extraAddress='" + extraAddress + '\'' +
				", phone='" + phone + '\'' +
				", suserno='" + suserno + '\'' +
				", userno=" + userno +
				", postcode=" + postcode +
				", gradeno=" + gradeno +
				", black=" + black +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public int getgradeno() {
		return gradeno;
	}

	public void setgradeno(int gradeno) {
		this.gradeno = gradeno;
	}

	public String getBlack() {
		return black;
	}

	public void setReportno(String black) {
		this.black = black;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

    public void setSuserno(String suserno) {
        this.suserno = suserno;
    }

    public String getSuserno() {
        return suserno;
    }
}
