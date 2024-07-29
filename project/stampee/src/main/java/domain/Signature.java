package domain;


public class Signature {
	private long menuId;
	private String menuName;
	private long cafeId;

	public Signature(String menuName) {
		this.menuName = menuName;
	}

	public Signature(long menuId, String menuName, long cafeId) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.cafeId = cafeId;
	}

	public String getMenuName() {
		return menuName;
	}
}
