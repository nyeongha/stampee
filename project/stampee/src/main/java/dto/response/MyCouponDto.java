package dto.response;

public class MyCouponDto {
	private long cafeId;
	private String cafeName;
	private String address;
	private int count;

	public MyCouponDto(long cafeId, String cafeName, String address, int count) {
		this.cafeId = cafeId;
		this.cafeName = cafeName;
		this.address = address;
		this.count = count;
	}

	public static MyCouponDto createMyCouponDto(long cafeId, String cafeName, String address, int count){
		return new MyCouponDto(cafeId, cafeName, address, count);
	}

	public long getCafeId() {
		return cafeId;
	}

	public String getCafeName() {
		return cafeName;
	}

	public String getAddress() {
		return address;
	}

	public int getCount() {
		return count;
	}
}
