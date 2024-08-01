package dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MyCouponDto {
	private long cafeId;
	private String cafeName;
	private String address;
	private int count;

	public static MyCouponDto createMyCouponDto(long cafeId, String cafeName, String address, int count) {
		return new MyCouponDto(cafeId, cafeName, address, count);
	}
}
