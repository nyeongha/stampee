package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;

public class MemberRepository {
	private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);

	public void userSignUp(Member member) {
		// connection 영역
		Connection connection = getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = new StringBuilder()
				.append("INSERT INTO member(member_id, password, email, phone_number, role)")
				.append("values(MEMBER_SEQ.NEXTVAL,?,?,?)")
				.toString();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhoneNumber());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(connection, pstmt);
		}
	}


}

