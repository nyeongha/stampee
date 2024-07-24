package domain;

import java.time.LocalDateTime;

public class Review {
	private long reviewId;
	private int rating;
	private String contents;
	private LocalDateTime createTime;
	private Member member;
}
