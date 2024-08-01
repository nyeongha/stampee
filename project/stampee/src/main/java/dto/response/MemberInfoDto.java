package dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberInfoDto {
	private long cafeId;
	private String cafeName;
	private long stampCount;
	private long couponCount;

	public static MemberInfoDto createMemberDto (long cafeId, String cafeName, long stampCount, long couponCount){
		return new MemberInfoDto(cafeId, cafeName,  stampCount, couponCount);
	}
}
