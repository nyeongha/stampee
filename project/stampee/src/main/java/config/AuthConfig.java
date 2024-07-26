package config;

public abstract class AuthConfig {
	public final static String EMAIL_VALDIDATION_RULE = "^[a-z][a-z0-9-_.]+@[a-z0-9-_]+(.[a-z0-9-_]+){1,2}$";
	public final static String USERNAME_VALDIDATION_RULE = "^([가-힣]{2,4}|[A-Za-z]{2,30})$";
	public final static String PASSWORD_VALDIDATION_RULE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{10,20}$";
	public final static String PHONE_NUMBER_VALDIDATION_RULE = "^010-[0-9]{3,4}-[0-9]{4}$";
}
