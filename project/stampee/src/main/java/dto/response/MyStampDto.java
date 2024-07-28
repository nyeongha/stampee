package dto.response;

import domain.Stamp;

public class MyStampDto {
	private String cafeName;
	private String cafeAddr;
	private int count;

	private MyStampDto(String cafeName, String cafeAddr, int count) {
		this.cafeName = cafeName;
		this.cafeAddr = cafeAddr;
		this.count = count;
	}

	public static MyStampDto createMyStampDto(Stamp stamp){
		return new MyStampDto(stamp.getCafe().getName(),
			stamp.getCafe().getAddress(),
			stamp.getCount());
	}
}
