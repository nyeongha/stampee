package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.ConfigLoader;

public class DBConnectionUtil {
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;


	public static Connection getConnection() {
		try {
			//라이브러리에 있는 driver를 알아서 찾음
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);   //데이터베이스에 연결하기 위해서
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	static {
		String dbURL;
		String dbUsername;
		String dbPassword;
		try {
			dbURL = ConfigLoader.getURL();
			dbUsername = ConfigLoader.getUsername();
			dbPassword = ConfigLoader.getPasswordD();
		} catch (IllegalStateException e) {
			e.getMessage();
			dbURL = "DUMMY_URL"; // 또는 다른 적절한 기본값
			dbUsername = "DUMMY_USERNAME";
			dbPassword = "DUMMY_PASSWORD";
		}
		URL = dbURL;
		USERNAME = dbUsername;
		PASSWORD = dbPassword;
	}
}
