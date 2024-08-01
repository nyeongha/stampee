package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Review {
	private long reviewId;
	private float rating;
	private String contents;
	private Date createTime;
	private Member author;
	private Cafe cafe;

	public Review(float rating, String contents, Date createTime, Member author, Cafe cafe) {
		this.rating = rating;
		this.contents = contents;
		this.createTime = createTime;
		this.cafe = cafe;
		this.author = author;
	}

	public Review(long reviewId, float rating, String contents) {
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
}
