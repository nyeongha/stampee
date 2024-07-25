package domain;

import java.sql.Date;

public class Stamp {
	private long stampId;
	private int count;
	private Date createTime;
	private Member member;
	private Cafe cafe;

	public Stamp(long stampId, int count, Date createTime, Member member, Cafe cafe) {
		this.stampId = stampId;
		this.count = count;
		this.createTime = createTime;
		this.member = member;
		this.cafe = cafe;
	}

	public int getCount() {
		return count;
	}

	public Member getMember() {
		return member;
	}

	public Cafe getCafe() {
		return cafe;
	}
}
