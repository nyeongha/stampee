package session;

import static exception.ErrorMessage.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import dto.response.LoggedCafeDto;

public class CafeSession {
	private static CafeSession instance;
	private final LoggedCafeDto loggedCafeDto;
	private static final Logger LOGGER = Logger.getLogger(CafeSession.class.getName());

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
			LOGGER.log(Level.WARNING, "User not logged in: {0}", USER_NOT_LOGGED_IN.getErrorMessage());
			throw new IllegalArgumentException(USER_NOT_LOGGED_IN.getErrorMessage());
		}
		LOGGER.log(Level.INFO, "Current CafeSession instance: {0}", instance);
		return instance;
	}

	public static void clearSession() {
		instance = null;
		LOGGER.log(Level.INFO, "Clear CafeSession instance: {0}", instance);
	}

	public LoggedCafeDto getLoggedCafeDto() {
		return loggedCafeDto;
	}
}
