package dto.response;

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

	public long getCafeId() {
		return cafeId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getContact() {
		return contact;
	}
}
