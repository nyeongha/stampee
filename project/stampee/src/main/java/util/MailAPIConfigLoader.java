package util;

import static exception.ErrorMessage.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class MailAPIConfigLoader {
	private static final Properties properties = new Properties();
	private static boolean isInitialized = false;

	static {
		try (InputStream input = GoogleAPIConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new FileNotFoundException(NOT_FOUND_CONFIG.getErrorMessage());
			} else {
				properties.load(input);
				isInitialized = true;
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static String getEmail(){
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String email = properties.getProperty("mail.EMAIL");
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return email;
	}

	public static String getAPP_PASSWORD(){
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String APP_PASSWORD = properties.getProperty("mail.APP_PASSWORD");
		if (APP_PASSWORD == null || APP_PASSWORD.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return APP_PASSWORD;
	}
}
