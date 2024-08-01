package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Signature {
	private long menuId;
	private String menuName;
	private Cafe cafe;

}
