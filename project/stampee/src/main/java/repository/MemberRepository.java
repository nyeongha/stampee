package repository;
import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				"INSERT INTO member(member_id, username, email, password, phone_number)" +
					"values(MEMBER_SEQ.NEXTVAL,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getPhoneNumber());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
		return member;
	}

	public Member findUserByPhoneNum(String phoneNum) {
		String sql = "select * from member "
			+ "where phone_number = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Member member = Member.createMember(rs.getLong("member_id"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("phone_number"));

				return member;
			} else {
				return null;
			}
		} catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public void deleteUser(String phoneNum) throws SQLException {
		String sql = "delete from member "
			+ "where phone_number = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}
}

