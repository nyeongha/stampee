package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private static final Properties properties = new Properties();
	private static boolean isInitialized = false;

	static {
		try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.err.println("config.properties not found");
			} else {
				properties.load(input);
				isInitialized = true;
			}
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static String getApiKey() {
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String apiKey = properties.getProperty("google.maps.api.key");
		if (apiKey == null || apiKey.trim().isEmpty()) {
			throw new IllegalStateException("API key not found in configuration");
		}
		return apiKey;
	}

	public static String getURL(){
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String URL = properties.getProperty("db.URL");
		if (URL == null || URL.trim().isEmpty()) {
			throw new IllegalStateException("URL not found in configuration");
		}
		return URL;
	}

	public static String getUsername(){
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String USERNAME = properties.getProperty("db.USERNAME");
		if (USERNAME == null || USERNAME.trim().isEmpty()) {
			throw new IllegalStateException("USERNAME not found in configuration");
		}
		return USERNAME;
	}

	public static String getPasswordD(){
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String PASSWORD = properties.getProperty("db.PASSWORD");
		if (PASSWORD == null || PASSWORD.trim().isEmpty()) {
			throw new IllegalStateException("PASSWORD not found in configuration");
		}
		return PASSWORD;
	}

	public static String getEmail(){
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String EMAIL = properties.getProperty("email.EMAIL");
		if (EMAIL == null || EMAIL.trim().isEmpty()) {
			throw new IllegalStateException("EMAIL not found in configuration");
		}
		return EMAIL;
	}

	public static String getAPP_PASSWORD(){
		if (!isInitialized) {
			throw new IllegalStateException("Configuration not properly initialized");
		}
		String APP_PASSWORD = properties.getProperty("email.APP_PASSWORD");
		if (APP_PASSWORD == null || APP_PASSWORD.trim().isEmpty()) {
			throw new IllegalStateException("APP_PASSWORD not found in configuration");
		}
		return APP_PASSWORD;
	}
}
