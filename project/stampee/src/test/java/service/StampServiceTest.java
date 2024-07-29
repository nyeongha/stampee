// package service;
//
// import static org.mockito.Mockito.*;
//
// import java.sql.SQLException;
// import java.util.NoSuchElementException;
//
// import javax.mail.MessagingException;
//
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
//
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import domain.Cafe;
// import domain.Member;
// import repository.MemberRepository;
// import repository.StampRepository;
//
// @ExtendWith(MockitoExtension.class)
// class StampServiceTest {
// 	@Mock StampRepository stampRepository;
// 	@Mock MemberRepository memberRepository;
// 	@InjectMocks StampService stampService;
// 	@Mock MailService mailService;
//
// 	@Test
// 	@DisplayName("회원이 아닌 사람이 스탬프를 저장하려고 하면 예외가 발생한다.")
// 	void saveStampWithNoSignUpUser() {
// 		//given
// 		Cafe cafe = new Cafe(1L, "테스트 카페", "테스트 주소", "1234", "test@test.com", "010-1111-1111");
// 		String unknown = "010-1234-1234";
// 		when(memberRepository.findUserByPhoneNum(unknown)).thenReturn(null);
//
// 		//when //then
// 		Assertions.assertThatThrownBy(() -> stampService.saveStamp(cafe, unknown, 3))
// 			.isInstanceOf(NoSuchElementException.class)
// 			.hasMessage("해당 전화번호로 가입된 회원이 존재하지 않습니다.");
// 	}
//
// 	@Test
// 	@DisplayName("친구에게 스탬프를 공유할 수 있다.")
// 	void shareStamp() throws MessagingException, SQLException {
// 		//given
// 		Member fromMember = Member.createMember(38L, "hello", "soeun8636@naver.com", "1234", "010-9984-8636");
// 		Member toMember = Member.createMember(39L, "friend", "friend@example.com", "5678","010-1234-8636");
// 		Cafe cafe = new Cafe(1L, "테스트 카페", "서울시 종로구", "1234", "cafe@naver.com", "010-1234-1234");
//
// 		//when
// 		when(memberRepository.findUserByPhoneNum("010-1234-8636")).thenReturn(toMember);
// 		when(stampRepository.updateStamp(cafe.getId(), fromMember.getId(), 39L, 3)).thenReturn(true);
// 		stampService.shareStamp(fromMember, cafe, "010-1234-8636", 3);
//
// 		//then
// 		verify(mailService, times(2)).sendMail(anyString(), anyString(), anyString(), anyString());
// 	}
//
// 	@Test
// 	@DisplayName("스탬프 개수가 부족하거나 해당 카페 회원이 아닌경우 스탬프를 공유할 수 없다.")
// 	void failShareStamp() throws MessagingException, SQLException {
// 		//given
// 		Member fromMember = Member.createMember(38L, "hello", "soeun8636@naver.com", "1234", "010-9984-8636");
// 		Member toMember = Member.createMember(39L, "friend", "friend@example.com", "5678","010-1234-8636");
// 		Cafe cafe = new Cafe(1L, "테스트 카페", "서울시 종로구", "1234", "cafe@naver.com", "010-1234-1234");
//
// 		//when
// 		when(memberRepository.findUserByPhoneNum("010-1234-8636")).thenReturn(toMember);
// 		when(stampRepository.updateStamp(cafe.getId(), fromMember.getId(), 39L, 3)).thenReturn(false);
// 		stampService.shareStamp(fromMember, cafe, "010-1234-8636", 3);
//
// 		//then
// 		verify(mailService, times(1)).sendMail(anyString(), anyString(), anyString(), anyString());
// 	}
// }
