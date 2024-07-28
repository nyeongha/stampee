package dto;

public class ExpiredCouponDto {
	private String username;
	private String memberEmail;
	private String cafeName;

	public ExpiredCouponDto(String username, String memberEmail, String cafeName) {
		this.username = username;
		this.memberEmail = memberEmail;
		this.cafeName = cafeName;
	}

	public String getUsername() {
		return username;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	@Override
	public String toString() {
		return "쿠폰 만료 예정 안내: " + username + "님 " + cafeName + "쿠폰이 5일 후 만료됩니다.";
	}
}
