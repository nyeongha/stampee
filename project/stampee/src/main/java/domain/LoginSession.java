package domain;

import static exception.ErrorMessage.*;

import dto.LoggedMemberDto;

public class LoginSession {
	private static LoginSession instance;
	private final LoggedMemberDto loggedMemberDto;

	public LoginSession(LoggedMemberDto loggedMemberDto) {
		this.loggedMemberDto = loggedMemberDto;
	}
	//로그인 시 새로운 세션을 생성
	public static synchronized LoginSession getInstance(LoggedMemberDto loggedMemberDto){
		if(instance == null){
			instance = new LoginSession(loggedMemberDto);
		}
		return instance;
	}
	//세션이 있는 경우 반환, 없는 경우 예외 발생
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
