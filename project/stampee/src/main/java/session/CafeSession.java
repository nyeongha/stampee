package session;

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
		}
		return instance;
	}

	public static synchronized CafeSession getInstance() {
		if (instance == null) {
			return null;
		}
		return instance;
	}

	public static void clearSession() {
		instance = null;
	}

	public LoggedCafeDto getLoggedCafeDto() {
		return loggedCafeDto;
	}
}
