package repository;

import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Member;

public class MemberRepository {

	public void userSignUp(long memberId, String password, String email, String phoneNumber) {
		// connection 영역
		Connection connection = getConnection();
		try {
			connection.setAutoCommit(false);

			Member user = new Member();

			// sql영역
			String sql = new StringBuilder()
				.append("INSERT INTO member(member_id, password, email, phone_number, role)")
				.append("values(?,?,?,?)")
				.toString();

			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			pstmt.setString(2, password);
			pstmt.setString(3, email);
			pstmt.setString(4, phoneNumber);
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();

		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}
}
