package repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dto.ExpiredCouponDto;

class CouponRepositoryTest {
	private CouponRepository couponRepository = new CouponRepository();

	@Test
	@DisplayName("쿠폰 만료 예정 5일전인 사용자를 찾을 수 있다.")
	void findExpiringCoupons(){
		//given
		LocalDate now = LocalDate.of(2024, 7, 28);
		//when
		List<ExpiredCouponDto> expiringCoupons = couponRepository.findExpiringCoupons(now);
		//then
		assertThat(expiringCoupons).hasSize(1)
			.extracting("username", "memberEmail", "cafeName")
			.containsExactly(tuple("성연", "human9062@gmail.com", "카페 아로마"));
	}
}