package dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CafeMemberInfoDto {
	private long memberId;
	private String memberName;
	private long stampCount;
	private long couponCount;

	public static CafeMemberInfoDto createCafeMemberDto (long memberId, String memberName, long stampCount, long couponCount){
		return new CafeMemberInfoDto(memberId, memberName, stampCount, couponCount);
	}
}
