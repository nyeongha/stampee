package domain;

import java.time.LocalDate;
import java.util.List;

public class Coupon {
	private long couponId;
	private LocalDate createTime;
	private LocalDate endTime;
	private List<Member> members;
	private List<Cafe> cafes;
}
