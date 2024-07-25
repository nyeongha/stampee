/*todo
 * 이메일에
 * */

package dto;

import domain.Member;

public class Authentication {
	private final String EMAIL_VALDIDATION_RULE = "^[a-z][a-z0-9\\-\\_\\.]+@[a-z0-9\\-\\_]+(\\.[a-z0-9\\-\\_]+){1,2}$";
	private final String PASSWORD_VALDIDATION_RULE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{10,20}$";
	private final String PHONE_NUMBER_VALDIDATION_RULE = "^010-[0-9]{3,4}-[0-9]{4}$";
	Member member;

	public Authentication(Member user) {
		member = user;
	}

	public boolean isValid() {
		String email = member.getEmail();
		String password = member.getPassword();
		String phoneNumber = member.getPhoneNumber();
		if (!email.matches(EMAIL_VALDIDATION_RULE)) {
			return false;
		} else if (!password.matches(PASSWORD_VALDIDATION_RULE)) {
			return false;
		} else if (!phoneNumber.matches(PHONE_NUMBER_VALDIDATION_RULE)) {
			return false;
		}
		return true;
	}
}
