package dto.response;

import domain.Stamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
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
