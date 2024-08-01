package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cafe {
	private long cafeId;
	private String name;
	private String address;
	private String password;
	private String email;
	private String contact;
}