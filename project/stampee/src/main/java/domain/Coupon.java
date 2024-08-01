package domain;

import java.sql.Date;

import lombok.Getter;

@Getter
public class Coupon {
	private long couponId;
	private Date createTime;
	private Date endTime;
	private Member member;
	private Cafe cafe;
}
