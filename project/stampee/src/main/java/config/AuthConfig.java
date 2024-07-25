package config;

public abstract class AuthConfig {
	public final static String EMAIL_VALDIDATION_RULE = "^[a-z][a-z0-9-_.]+@[a-z0-9-_]+(.[a-z0-9-_]+){1,2}$";
	public final static String USERNAME_VALDIDATION_RULE = "^([a-zA-Z]+\\s[a-zA-Z]+)|([가-힇]+)$";
	public final static String PASSWORD_VALDIDATION_RULE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{10,20}$";
	public final static String PHONE_NUMBER_VALDIDATION_RULE = "^010-[0-9]{3,4}-[0-9]{4}$";
}
