package domain;

public class Cafe {
	private long cafeId;
	private String name;
	private String address;
	private String password;
	private String email;
	private String contact;

	public Cafe(long cafeId, String name, String address, String password, String email, String contact) {
		this.cafeId = cafeId;
		this.name = name;
		this.address = address;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}

	public Cafe() {

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

	public void setName(String name) { this.name = name; }

	public void setCafeId(long cafeId) {
		this.cafeId = cafeId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address){ this.address = address;}

	public void setContact(String contact){ this.contact = contact;}
}