package domain;

public class Member {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;
	private String username;


	public long getMemberId() {
		return memberId;
	}

	public Member(long memberId, String password, String email, String phoneNumber, String username) {
		this.memberId = memberId;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}

	public String getPassword() {
		return password;
	}


	public String getEmail() {
		return email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getUsername() {
		return username;
	}
}
