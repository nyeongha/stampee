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
}
