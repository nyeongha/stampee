package dto.response;

public class CafeMemberInfoDto {
	private long memberId;
	private String memberName;
	private long stampCount;
	private long couponCount;

	private CafeMemberInfoDto(long memberId, String memberName, long stampCount, long couponCount) {
		this.memberId =  memberId;
		this.memberName =  memberName;
		this.stampCount =  stampCount;
		this.couponCount =  couponCount;
	}

	public static CafeMemberInfoDto createCafeMemberDto (long memberId, String memberName, long stampCount, long couponCount){
		return new CafeMemberInfoDto(memberId, memberName, stampCount, couponCount);
	}

	public long getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public long getStampCount() {
		return stampCount;
	}

	public long getCouponCount() {
		return couponCount;
	}
}
