package domain;

import java.sql.Clob;
import java.sql.Date;

public class Review {

	private long reviewId;
	private int rating;
	private String contents;
	private Date createTime;
	private Member author;
	private Cafe cafe;

	public Review(long reviewId, int rating, String contents, Date createTime, Member author, Cafe cafe) {
		this.reviewId = reviewId;
		this.rating = rating;
		this.contents = contents;
		this.createTime = createTime;
		this.cafe = cafe;
		this.author = author;
	}

	public Review(int rating, String contents, Date createTime, Member author, Cafe cafe) {
		this.rating = rating;
		this.contents = contents;
		this.createTime = createTime;
		this.cafe = cafe;
		this.author = author;
	}

	public Review(long reviewId, int rating, String contents) {
		this.reviewId=reviewId;
		this.rating=rating;
		this.contents=contents;
	}

	public long getId() {
		return reviewId;
	}

	public int getRating() {
		return rating;
	}

	public String getContents() {
		return contents;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Member getMember() {
		return author;
	}

	public Cafe getCafe() {
		return cafe;
	}
}
