package domain;

public class Member {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;

	public long getMemberId() {
		return memberId;
	}

	public Member(long memberId, String password, String email, String phoneNumber) {
		this.memberId = memberId;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
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
}
