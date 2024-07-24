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

}
