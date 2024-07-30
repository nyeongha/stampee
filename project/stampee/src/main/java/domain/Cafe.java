package domain;

import java.util.List;

public class Cafe {
	private long cafeId;
	private final String name;
	private final String address;
	private String password;
	private String email;
	private final String contact;
	private List<Signature> signatures;

	public Cafe(long cafeId, String name, String address, String password, String email, String contact) {
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

	public String getContact() {
		return contact;
	}

	public String getEmail() {
		return email;
	}


	public long getId() {
		return cafeId;
	}

	public void setCafeId(long cafeId) {
		this.cafeId = cafeId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}