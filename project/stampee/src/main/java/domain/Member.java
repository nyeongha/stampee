package domain;

public class Member {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;

	public Member(long memberId, String phoneNumber, String email, String password) {
		this.memberId = memberId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}

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
