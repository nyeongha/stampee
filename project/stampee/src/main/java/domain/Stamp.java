package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Stamp {
	private long stampId;
	private int count;
	private Date createTime;
	private Member member;
	private Cafe cafe;
}
