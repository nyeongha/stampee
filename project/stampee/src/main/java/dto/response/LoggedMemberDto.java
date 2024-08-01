package dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoggedMemberDto {
	private long memberId;
	private String password;
	private String email;
	private String phoneNumber;
	private String username;

	public LoggedMemberDto(long memberId, String password, String email, String phoneNumber, String username) {
		this.memberId = memberId;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.username = username;
	}

	public LoggedMemberDto() {

	}

	public String getUsername() {
		return username;
	}
}
