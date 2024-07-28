package repository;
import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

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
}