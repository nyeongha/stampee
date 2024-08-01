package service;

import static formatter.MailMessageFormatter.*;
import static java.time.LocalDate.*;

import java.util.List;
import javax.mail.MessagingException;
import dto.response.ExpiredCouponDto;
import dto.response.MyCouponDto;
import lombok.RequiredArgsConstructor;
import repository.CouponRepository;


@RequiredArgsConstructor
public class CouponService {
	private final CouponRepository couponRepository;
	private final MailService mailService;

	public void expiredCoupon() throws MessagingException {
		couponRepository.deleteExpiredCoupons(now());
		List<ExpiredCouponDto> expiringCoupons = couponRepository.findExpiringCoupons(now());
		for (ExpiredCouponDto expiringCoupon : expiringCoupons) {
			mailService.sendMail(expiringCoupon.getMemberEmail(), expiringCoupon.toString(), EXPIRED_COUPON.getMessage());
		}
	}

	public int getMyCount(long memberId, long cafeId) {
		return couponRepository.findCouponByMemberIdAndCafeId(memberId, cafeId);
	}

	public List<MyCouponDto> getMyCoupon(long memberId) {
		return couponRepository.findCouponByMemberId(memberId);
	}

	public int getExpiringCouponCount(long memberId, long cafeId) {
		return couponRepository.findExpiringCouponCount(memberId, cafeId);
	}
}