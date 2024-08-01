package dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class LoggedMemberDto {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;
	private String username;
}
