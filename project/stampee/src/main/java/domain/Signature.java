package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Signature {
	private long menuId;
	private String menuName;
	private Cafe cafe;

	// 생성자: 메뉴 이름과 카페 객체를 받아 초기화
	public Signature(String menuName, Cafe cafe) {
		this.menuName = menuName;
		this.cafe = cafe;
	}
}
