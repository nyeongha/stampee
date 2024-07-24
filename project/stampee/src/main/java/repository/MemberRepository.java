package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Member;

public class MemberRepository {
	private static final Logger log = LoggerFactory.getLogger(MemberRepository.class);

	public void userSignUp(Member member) {
		Connection connection = getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = new StringBuilder()
				.append("INSERT INTO member(member_id, password, email, phone_number)")
				.append("values(MEMBER_SEQ.NEXTVAL,?,?,?)")
				.toString();

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhoneNumber());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(connection, pstmt);
		}
	}

	public Member findUserByPhoneNum(String phoneNum) {
		String sql = "select * from member "
			+ "where phone_number = ?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Member member = new Member(rs.getLong("memberId"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("phoneNum"));

				return member;
			} else {
				throw new NoSuchElementException("해당 전화번호로 가입된 회원이 없습니다.");
			}
		} catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt, rs);
		}
	}
}

