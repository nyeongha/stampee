package domain;

import static exception.ErrorMessage.*;

import dto.LoggedMemberDto;

public class LoginSession {
	private static LoginSession instance;
	private final LoggedMemberDto loggedMemberDto;

	public LoginSession(LoggedMemberDto loggedMemberDto) {
		this.loggedMemberDto = loggedMemberDto;
	}

	public static synchronized LoginSession getInstance(LoggedMemberDto loggedMemberDto){
		if(instance == null){
			instance = new LoginSession(loggedMemberDto);
			return instance;
		}
		return instance;
	}

	public static  synchronized LoginSession getInstance(){
		if(instance == null){
			throw new IllegalArgumentException(USER_NOT_LOGGED_IN.getErrorMessage());
		}
		 return instance;
	}

	public void clearSession() {
		instance = null;
	}

	public LoggedMemberDto getLoggedMemberDto() {
		return loggedMemberDto;
	}
}
