package dto.response;

import domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoggedMemberDto {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;
	private String username;

	public static LoggedMemberDto createLoggedMemberDto(Member member){
		return new LoggedMemberDto(member.getMemberId(), member.getPassword(), member.getEmail(),
			member.getPhoneNumber(), member.getUserName());
	}
}
