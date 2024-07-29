// package service;
//
// import static org.mockito.Mockito.*;
//
// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
//
// import javax.mail.MessagingException;
//
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// import dto.ExpiredCouponDto;
// import repository.CouponRepository;
//
// @ExtendWith(MockitoExtension.class)
// class CouponServiceTest {
// 	@Mock MailService mailService;
// 	@Mock CouponRepository couponRepository;
// 	@InjectMocks CouponService couponService;
//
// 	@Test
// 	@DisplayName("쿠폰 만료 예정인 사용자들에게 메일을 전송한다.")
// 	void expiredCoupon() throws MessagingException {
// 		//given
// 		LocalDate now = LocalDate.of(2024, 7, 28);
// 		List<ExpiredCouponDto> expiredCouponDtos = new ArrayList<>();
// 		expiredCouponDtos.add(new ExpiredCouponDto("test1", "test1@naver.com", "테스트 카페1", 1));
// 		expiredCouponDtos.add(new ExpiredCouponDto("test2", "test2@naver.com", "테스트 카페2", 3));
//
// 		//when
// 		when(couponRepository.findExpiringCoupons(now)).thenReturn(expiredCouponDtos);
// 		couponService.expiredCoupon();
//
// 		//then
// 		verify(mailService, times(2)).sendMail(anyString(), anyString(), anyString(), anyString());
// 	}
// }