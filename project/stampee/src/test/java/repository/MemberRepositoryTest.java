package repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Member;

class MemberRepositoryTest {
	public static final String TESTNUM = "010-1234-1234";
	MemberRepository memberRepository = new MemberRepository();

	@AfterEach
	void tearDown() {
		memberRepository.deleteUser(TESTNUM);
	}

	@Test
	@DisplayName("전화번호로 회원을 찾을 수 있다.")
	void findUserByPhoneNum() {
		//given
		String phoneNum = TESTNUM;
		Member member = Member.createMember(0L, "1234", "test@test.com", "010-1234-1234", TESTNUM);
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
		assertThat(member1).isNotNull();
		assertThat(member1.getUserName()).isEqualTo(validUserName);
		assertThat(member1.getEmail()).isEqualTo(validEmail);
		assertThat(member1.getPassword()).isEqualTo(validPassword);
		assertThat(member1.getPhoneNumber()).isEqualTo(validPhoneNumber);
	}

	@DisplayName("멤버 유효성검사 테스트2")
	@Test
	void ifInvalidArgumentsTest() throws IllegalArgumentException {
		long invalidMemberId = 100;
		String invalidUserName = "진광환뿌슝빠슝";
		String invalidEmail = "rhkdghks21@naver.com.ac.kr";
		String invalidPassword = "wlsldjt";
		String invalidPhoneNumber = "010-343-8220";

		long validMemberId = 100;
		String validUserName = "진광환";
		String validEmail = "rhkdghks21@naver.com";
		String validPassword = "Wlsldjtm21*";
		String validPhoneNumber = "010-3493-8220";

		//then
		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(invalidMemberId, validUserName, validEmail, validPassword, validPhoneNumber)
		);
		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, invalidUserName, validEmail, validPassword, validPhoneNumber)
		);
		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, invalidEmail, validPassword, validPhoneNumber)
		);
		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, validEmail, invalidPassword, validPhoneNumber)
		);
		assertThrows(IllegalArgumentException.class, () ->
			Member.createMember(validMemberId, validUserName, validEmail, validPassword, invalidPhoneNumber)
		);
	}
}




