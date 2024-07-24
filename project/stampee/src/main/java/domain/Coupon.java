package domain;

import java.time.LocalDate;
public class Coupon {
	private long couponId;
	private LocalDate createTime;
	private LocalDate endTime;
	private Member member;
	private Cafe cafe;
}
