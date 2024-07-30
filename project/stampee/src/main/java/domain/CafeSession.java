package domain;

import static exception.ErrorMessage.*;

import dto.response.LoggedCafeDto;

public class CafeSession {
	private static CafeSession instance;
	private final LoggedCafeDto loggedCafeDto;

	public CafeSession(LoggedCafeDto loggedCafeDto) {
		this.loggedCafeDto = loggedCafeDto;
	}

	public static synchronized CafeSession getInstance(LoggedCafeDto loggedCafeDto){
		if(instance == null){
			instance = new CafeSession(loggedCafeDto);
			return instance;
		}
		return instance;
	}

	public static  synchronized CafeSession getInstance(){
		if(instance == null){
			throw new IllegalArgumentException(USER_NOT_LOGGED_IN.getErrorMessage());
		}
		return instance;
	}

	public void clearSession() {
		instance = null;
	}

	public LoggedCafeDto getLoggedCafeDto() {
		return loggedCafeDto;
	}
}
