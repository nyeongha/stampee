package domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
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
		this.reviewId = reviewId;
		this.rating = rating;
		this.contents = contents;
	}


	@Override
	public String toString() {
		return "Username: " + author.getUserName() + "\n" +
			"Cafe: " + cafe.getName() + "\n" +
			"Rating: " + rating + "\n" +
			"Date: " + createTime + "\n" +
			"Content: " + contents;
	}

	public Thread getMember() {
		return null;
	}

	public Cafe getCafe() {
		return cafe;
	}

	public long getReviewId() {
		return reviewId;
	}


}
