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

	public Member userSignUp(Member member) {
		// connection 영역
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql =
				"INSERT INTO member(member_id, password, email, phone_number)" +
					"values(MEMBER_SEQ.NEXTVAL,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
		return member;
	}

}

