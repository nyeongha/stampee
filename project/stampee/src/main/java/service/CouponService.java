package service;

import static formatter.MailMessageFormatter.*;

import java.time.LocalDate;
import java.util.List;

import javax.mail.MessagingException;

import dto.ExpiredCouponDto;
import repository.CouponRepository;

public class CouponService {
	private final CouponRepository couponRepository;
	private final MailService mailService;

	public CouponService(CouponRepository couponRepository, MailService mailService) {
		this.couponRepository = couponRepository;
		this.mailService = mailService;
	}

	public void expiredCoupon() throws MessagingException {
		List<ExpiredCouponDto> expiringCoupons = couponRepository.findExpiringCoupons(LocalDate.now());
		for (ExpiredCouponDto expiringCoupon : expiringCoupons) {
			mailService.sendMail(expiringCoupon.getMemberEmail(), "human9062@gmail.com", expiringCoupon.toString(), EXPIRED_COUPON.getMessage());
		}
	}

	public int getMyCount(long memberId, long cafeId){
		return couponRepository.findCouponByMemberId(memberId, cafeId);
	}
}
