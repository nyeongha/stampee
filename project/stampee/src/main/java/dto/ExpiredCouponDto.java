package dto;

public class ExpiredCouponDto {
	private String username;
	private String memberEmail;
	private String cafeName;
	private int remainDate;

	public ExpiredCouponDto(String username, String memberEmail, String cafeName, int remainDate) {
		this.username = username;
		this.memberEmail = memberEmail;
		this.cafeName = cafeName;
		this.remainDate = remainDate;
	}

	public String getUsername() {
		return username;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	@Override
	public String toString() {
		return "쿠폰 만료 예정 안내: " + username + "님 " + cafeName + "쿠폰이 " + remainDate + "일 후 만료됩니다.";
	}
}