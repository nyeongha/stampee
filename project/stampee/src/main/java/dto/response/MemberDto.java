package dto.response;

public class MemberDto {
	private String passWord;
	private String email;


	public MemberDto(String passWord, String email) {
		this.passWord = passWord;
		this.email = email;
	}


	public String getEmail() {
		return email;
	}

	public String getPassWord() {
		return passWord;
	}

}
