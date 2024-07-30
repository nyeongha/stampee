package config;

import static util.DBConfigLoader.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	private static final String URL = getURL();
	private static final String USERNAME = getUsername();
	private static final String PASSWORD = getPasswordD();

	public static Connection getConnection() {
		try {
			//라이브러리에 있는 driver를 알아서 찾음
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);   //데이터베이스에 연결하기 위해서
			return connection;
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
