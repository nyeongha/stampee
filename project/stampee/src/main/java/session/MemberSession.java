package session;

import static exception.ErrorMessage.*;

import dto.response.LoggedMemberDto;

public class MemberSession {
	private static MemberSession instance;
	private final LoggedMemberDto loggedMemberDto;

	public MemberSession(LoggedMemberDto loggedMemberDto) {
		this.loggedMemberDto = loggedMemberDto;
	}

	public static synchronized MemberSession getInstance(LoggedMemberDto loggedMemberDto){
		if(instance == null){
			instance = new MemberSession(loggedMemberDto);
			return instance;
		}
		return instance;
	}

	public static  synchronized MemberSession getInstance(){
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
