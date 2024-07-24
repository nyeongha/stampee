package domain;

import java.time.LocalDate;
import java.util.List;

public class Stamp {
	private long stampId;
	private int count;
	private LocalDate createTime;
	private List<Member> members;
	private List<Cafe> cafes;
}
