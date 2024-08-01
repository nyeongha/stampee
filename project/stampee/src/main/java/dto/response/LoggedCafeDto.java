package dto.response;

import domain.Cafe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoggedCafeDto {
	private long cafeId;
	private String name;
	private String address;
	private String password;
	private String email;
	private String contact;

	public static LoggedCafeDto createLoggedCafeDto(Cafe cafe){
		return new LoggedCafeDto(cafe.getCafeId(), cafe.getName(), cafe.getAddress(), cafe.getPassword(), cafe.getEmail(), cafe.getContact());
	}
}
