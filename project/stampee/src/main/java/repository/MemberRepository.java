package repository;

import static config.ConnectionClose.*;
import static config.DBConnectionUtil.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;

public class MemberRepository {
	private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);

	public void memberSignUp(Member member) {
		System.out.println(member.toString());
		System.out.println("레포 들어옴=============================================");
		String insertMemberSql = "insert into member(member_id, username, email, password, phone_number) " +
			"values(MEMBER_SEQ.NEXTVAL,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			//멤버 회원 정보 삽입
			pstmt = conn.prepareStatement(insertMemberSql);
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, hashPassword(member.getPassword()));
			pstmt.setString(4, member.getPhoneNumber());
			pstmt.executeUpdate();

			conn.commit();
			System.out.println("정상===========================================================");
		} catch (SQLException | NoSuchAlgorithmException e) {
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException rollbackEx) {
				log.error("Failed to rollback transaction", rollbackEx);
			}
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, null);
		}
	}

	public Member login(String email, String password) {
		String sql = "select * from member where email = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password");
				if (verifyPassword(password, storedPassword)) {
					// 성공적으로 인증된 경우, Entity에 정보 저장
					Member member = new Member();
					member.setEmail(email);
					member.setPassword(storedPassword); // 데이터베이스 필드에 따라 수정
					member.setUserName(rs.getString("username")); // 데이터베이스 필드에 따라 수정
					member.setPhoneNumber(rs.getString("phone_number")); // 데이터베이스 필드에 따라 수정
					return member;
				} else {
					System.out.println("Invalid password for email: " + email);
				}
			} else {
				System.out.println("Email not found: " + email);
			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Password verification algorithm not found: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, null);
		}
		return null;
	}


	public Member findUserById(long memberId) {
		String sql = "select * "
			+ "from member "
			+ "where member_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return Member.createMember(rs.getLong("member_id"), rs.getString("username"),
					rs.getString("email"), rs.getString("password"), rs.getString("phone_number"));
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
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
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("username"),
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
			close(conn, pstmt, null);
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
			close(conn, pstmt,null);
		}
	}
}
