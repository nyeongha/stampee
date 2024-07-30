package util;

import static exception.ErrorMessage.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GoogleAPIConfigLoader {
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

	public static String getApiKey() {
		if (!isInitialized) {
			throw new IllegalStateException(FAILED_TO_INITIALIZE.getErrorMessage());
		}
		String apiKey = properties.getProperty("google.maps.api.key");
		if (apiKey == null || apiKey.trim().isEmpty()) {
			throw new IllegalStateException(NOT_FOUND_PROPERTIES.getErrorMessage());
		}
		return apiKey;
	}
}
