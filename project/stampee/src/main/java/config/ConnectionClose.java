package config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ConnectionClose {
	private static final Logger log = LoggerFactory.getLogger(ConnectionClose.class);

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("rs close error", e);
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.info("pstmt close error", e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("con close error", e);
			}
		}
	}
}
