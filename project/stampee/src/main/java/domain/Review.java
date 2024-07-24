package domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class Review {

	private long reviewId;
	private int rating;
	private String contents;
	private Date createTime;
	private Member author;
	private Cafe cafe;

	public Review(long reviewId,
					int rating,
					String contents,
					Date createTime
					) {
		this.reviewId = reviewId;
		this.rating = rating;
		this.contents = contents;
		this.createTime = createTime;
	}

}
