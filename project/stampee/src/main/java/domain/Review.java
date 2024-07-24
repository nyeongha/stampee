package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Review {
	private long reviewId;
	private int rating;
	private String contents;
	private LocalDateTime createTime;
	private List<Member> members;

}
