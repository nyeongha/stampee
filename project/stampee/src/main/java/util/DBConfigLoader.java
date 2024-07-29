package util;

import static exception.ErrorMessage.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class DBConfigLoader {
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
	public static String getURL(){
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String url = properties.getProperty("db.URL");
		if (url == null || url.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return url;
	}

	public static String getUsername(){
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String username = properties.getProperty("db.USERNAME");
		if (username == null || username.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return username;
	}

	public static String getPasswordD(){
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String password = properties.getProperty("db.PASSWORD");
		if (password == null || password.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return password;
	}

}
