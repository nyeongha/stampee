package domain;

import static config.ValidationRules.*;

public class Member {
	private long memberId;
	private String userName;
	private String email;
	private String password;
	private String phoneNumber;

	private Member(long memberId, String userName, String email, String password, String phoneNumber) {
		this.memberId = memberId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	private static void validateMember(String userName, String email, String password, String phoneNumber) {
		if (!email.matches(EMAIL_VALDIDATION_RULE)) {
			throw new IllegalArgumentException();
		}

		if (!userName.matches(USERNAME_VALDIDATION_RULE)) {
			throw new IllegalArgumentException();
		}

		if (!password.matches(PASSWORD_VALDIDATION_RULE)) {
			throw new IllegalArgumentException();
		}

		if (!phoneNumber.matches(PHONE_NUMBER_VALDIDATION_RULE)) {
			throw new IllegalArgumentException();
		}
	}

	public static Member createMember(long memberId, String userName, String email, String password,
		String phoneNumber) {
		// validateMember(userName, email, password, phoneNumber);
		return new Member(memberId, userName, email, password, phoneNumber);
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
