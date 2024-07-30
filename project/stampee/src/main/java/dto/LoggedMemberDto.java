package dto;

public class LoggedMemberDto {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;
	private String username;

	private LoggedMemberDto(long memberId, String password, String email, String phoneNumber, String username) {
		this.memberId = memberId;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}

	public long getMemberId() {
		return memberId;
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
