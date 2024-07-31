package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.DBConfigLoader;

public class DBConnectionUtil {
	private static final String URL = DBConfigLoader.getURL();
	private static final String USERNAME = DBConfigLoader.getUsername();
	private static final String PASSWORD = DBConfigLoader.getPasswordD();

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);   //데이터베이스에 연결하기 위해서
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
