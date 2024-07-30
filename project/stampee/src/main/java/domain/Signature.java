package domain;

public class Signature {
	private long menuId;
	private String menuName;
	private Cafe cafe;

	// 생성자: 메뉴 이름과 카페 객체를 받아 초기화
	public Signature(String menuName, Cafe cafe) {
		this.menuName = menuName;
		this.cafe = cafe;
	}

	// 생성자: 메뉴 ID, 메뉴 이름, 카페 객체를 받아 초기화
	public Signature(long menuId, String menuName, Cafe cafe) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.cafe = cafe;
	}

	public Cafe getCafe() {
		return cafe;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}
}
