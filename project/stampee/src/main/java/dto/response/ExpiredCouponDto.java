package dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ExpiredCouponDto {
	private String username;
	private String memberEmail;
	private String cafeName;
	private int remainDate;

	@Override
	public String toString() {
		return "쿠폰 만료 예정 안내: " + username + "님 " + cafeName + "쿠폰이 " + remainDate + "일 후 만료됩니다.";
	}
}