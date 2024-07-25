package service;

import java.util.NoSuchElementException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domain.Cafe;
import repository.MemberRepository;
import repository.StampRepository;

@ExtendWith(MockitoExtension.class)
class StampServiceTest {
	@Mock StampRepository stampRepository;
	@Mock MemberRepository memberRepository;
	@InjectMocks StampService stampService;

	@Test
	@DisplayName("회원이 아닌 사람이 스탬프를 저장하려고 하면 예외가 발생한다.")
	void saveStampWithNoSignUpUser() {
		//given
		Cafe cafe = new Cafe(1L, "테스트 카페", "테스트 주소", "1234", "test@test.com", "010-1111-1111");
		String unknown = "010-1234-1234";
		Mockito.when(memberRepository.findUserByPhoneNum(unknown)).thenReturn(null);

		//when //then
		Assertions.assertThatThrownBy(() -> stampService.saveStamp(cafe, unknown, 3))
			.isInstanceOf(NoSuchElementException.class)
			.hasMessage("해당 전화번호로 가입된 회원이 존재하지 않습니다.");
	}
}
