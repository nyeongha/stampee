package domain;

import static config.ValidationRules.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
	private long memberId;
	private String userName;
	private String email;
	private String password;
	private String phoneNumber;

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
}