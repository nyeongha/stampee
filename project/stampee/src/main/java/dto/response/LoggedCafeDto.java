package dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoggedCafeDto {
	private long cafeId;
	private String name;
	private String address;
	private String password;
	private String email;
	private String contact;

	public LoggedCafeDto(long cafeId, String name, String address, String password, String email, String contact) {
		this.cafeId = cafeId;
		this.name = name;
		this.address = address;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}

	public LoggedCafeDto() {

	}

}
