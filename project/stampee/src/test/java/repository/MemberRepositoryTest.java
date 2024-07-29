
package repository;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Member;

class MemberRepositoryTest {

	MemberRepository memberRepository = new MemberRepository();
	public static final String TESTNUM = "010-1234-1234";

	@AfterEach
	void tearDown() throws SQLException {
		memberRepository.deleteUser(TESTNUM);
	}

	@Test
	@DisplayName("전화번호로 회원을 찾을 수 있다.")
	void findUserByPhoneNum(){
		//given
		String phoneNum = TESTNUM;
		Member member = Member.createMember(0L, "양소은", "test@test.com", "1234", TESTNUM);
		memberRepository.userSignUp(member);

		//when
		Member findUser = memberRepository.findUserByPhoneNum(phoneNum);

		//then
		assertThat(findUser)
			.extracting("password", "email", "phoneNumber")
			.containsExactly("1234", "test@test.com", phoneNum);
	}

	@DisplayName("멤버 유효성검사 테스트1")
	@Test
	void memberStaticFactoryMethodTest() {
		//given
		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";

		//when
		final Member member1 = Member.createMember(validMemberId, validUserName, validEmail, validPassword,
			validPhoneNumber);

		// 유효한 경우 객체가 생성되고, 필드 값이 올바르게 설정되었는지 확인
		assertThat(member1).extracting(Member::getId,
			Member::getUserName,
			Member::getEmail,
			Member::getPassword,
			Member::getPhoneNumber)
			.containsExactly(
				100L,
				"진광환",
				"rhkdghks21@naver.com",
				"Wlsldjtm21*",
				"010-3493-8220"
			);
	}

	@DisplayName("멤버 유효성검사 테스트2")
	@Test
	void ifInvalidUserNameTest() throws IllegalArgumentException {
		long invalidMemberId = 100;
		String invalidUserName = "진광환뿌슝빠슝";
		String invalidEmail = "rhkdghks21@naver.com.ac.kr";
		String invalidPassword = "wlsldjt";
		String invalidPhoneNumber = "010-343333-8220";

		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";


		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, invalidUserName, validEmail, validPassword, validPhoneNumber)
		);
	}
	@Test
	void ifInvalidEmailTest() throws IllegalArgumentException {
		long invalidMemberId = 100;
		String invalidUserName = "진광환뿌슝빠슝";
		String invalidEmail = "rhkdghks21@naver.com.ac.kr";
		String invalidPassword = "wlsldjt";
		String invalidPhoneNumber = "010-343333-8220";

		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";



		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, invalidEmail, validPassword, validPhoneNumber)
		);

	}
	@Test
	void ifInvalidPasswordTest() throws IllegalArgumentException {
		long invalidMemberId = 100;
		String invalidUserName = "진광환뿌슝빠슝";
		String invalidEmail = "rhkdghks21@naver.com.ac.kr";
		String invalidPassword = "wlsldjt";
		String invalidPhoneNumber = "010-343333-8220";

		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";



		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, validEmail, invalidPassword, validPhoneNumber)
		);
	}
	@Test
	void ifInvalidPhoneNumberTest() throws IllegalArgumentException {
		long invalidMemberId = 100;
		String invalidUserName = "진광환뿌슝빠슝";
		String invalidEmail = "rhkdghks21@naver.com.ac.kr";
		String invalidPassword = "wlsldjt";
		String invalidPhoneNumber = "010-343333-8220";

		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";


		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, validEmail, validPassword, invalidPhoneNumber)
		);
	}
}
