package session;

import static exception.ErrorMessage.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import dto.response.LoggedMemberDto;

public class MemberSession {
	private static MemberSession instance;
	private final LoggedMemberDto loggedMemberDto;
	private static final Logger LOGGER = Logger.getLogger(MemberSession.class.getName());

	public MemberSession(LoggedMemberDto loggedMemberDto) {
		this.loggedMemberDto = loggedMemberDto;
	}

	public static synchronized MemberSession getInstance(LoggedMemberDto loggedMemberDto){
		if(instance == null){
			instance = new MemberSession(loggedMemberDto);
		}
		return instance;
	}

	public static  synchronized MemberSession getInstance(){
		if(instance == null){
			LOGGER.log(Level.WARNING, "User not logged in: {0}", USER_NOT_LOGGED_IN.getErrorMessage());
			throw new IllegalArgumentException(USER_NOT_LOGGED_IN.getErrorMessage());
		}
		LOGGER.log(Level.INFO, "Current CafeSession instance: {0}", instance);
		return instance;
	}

	public static void clearSession() {
		instance = null;
		LOGGER.log(Level.INFO, "Clear MemberSession instance: {0}", instance);

	}

	public LoggedMemberDto getLoggedMemberDto() {
		return loggedMemberDto;
	}
}
