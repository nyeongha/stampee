package dto.response;

import lombok.Getter;
import lombok.Setter;

// 서정's코드
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


// 원래 코드
// public class LoggedCafeDto {
// 	private long cafeId;
// 	private String name;
// 	private String address;
// 	private String password;
// 	private String email;
// 	private String contact;
//
// 	public LoggedCafeDto(long cafeId, String name, String address, String password, String email, String contact) {
// 		this.cafeId = cafeId;
// 		this.name = name;
// 		this.address = address;
// 		this.password = password;
// 		this.email = email;
// 		this.contact = contact;
// 	}
//
// 	public LoggedCafeDto() {
//
// 	}
//
// 	public long getCafeId() {
// 		return cafeId;
// 	}
//
// 	public void setCafeId(long cafeId) {
// 		this.cafeId = cafeId;
// 	}
//
// 	public String getName() {
// 		return name;
// 	}
//
// 	public void setName(String name) {
// 		this.name = name;
// 	}
//
// 	public String getAddress() {
// 		return address;
// 	}
//
// 	public void setAddress(String address) {
// 		this.address = address;
// 	}
//
// 	public String getPassword() {
// 		return password;
// 	}
//
// 	public void setPassword(String password) {
// 		this.password = password;
// 	}
//
// 	public String getEmail() {
// 		return email;
// 	}
//
// 	public void setEmail(String email) {
// 		this.email = email;
// 	}
//
// 	public String getContact() {
// 		return contact;
// 	}
//
// 	public void setContact(String contact) {
// 		this.contact = contact;
// 	}
// }
