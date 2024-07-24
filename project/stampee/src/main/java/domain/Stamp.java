package domain;

import java.time.LocalDate;

public class Stamp {
	private long stampId;
	private int count;
	private LocalDate createTime;
	private Member member;
	private Cafe cafe;

	public Stamp(int count, LocalDate createTime, Member member, Cafe cafe) {
		this.count = count;
		this.createTime = createTime;
		this.member = member;
		this.cafe = cafe;
	}

	public int getCount() {
		return count;
	}

	public LocalDate getCreateTime() {
		return createTime;
	}

	public Member getMember() {
		return member;
	}

	public Cafe getCafe() {
		return cafe;
	}
}
