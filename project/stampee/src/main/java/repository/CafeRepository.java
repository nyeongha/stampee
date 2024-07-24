package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CafeRepository {

	public void userSignUp(long cafeId, String name, String address, String password, String email, String contact) {
		// SQL
		String sql = "INSERT INTO cafe(cafe_Id, name, address, password, email, contact) VALUES(CAFE_SEQ.NEXTVAL,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// DB connection
			conn = getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			pstmt.setString(5, contact);
			pstmt.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rollbackEx) {
					throw new RuntimeException(rollbackEx);
				}
			}
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}
}
