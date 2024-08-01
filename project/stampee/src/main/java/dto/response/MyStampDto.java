package dto.response;

import domain.Stamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MyStampDto {
	private String cafeName;
	private String cafeAddr;
	private int count;

	public static MyStampDto createMyStampDto(Stamp stamp) {
		return new MyStampDto(stamp.getCafe().getName(),
			stamp.getCafe().getAddress(),
			stamp.getCount());
	}
}
