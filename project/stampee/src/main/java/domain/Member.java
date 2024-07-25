package domain;

public class Member {
	private long memberId;
	private String userName;
	private String email;
	private String password;
	private String phoneNumber;

	public Member(long memberId, String userName, String email, String password, String phoneNumber) {
		this.memberId = memberId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return memberId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUserName() {
		return userName;
	}
}
