package repository;

import static config.ConnectionClose.*;
import static config.DBConnectionUtil.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Member;
import dto.response.MemberInfoDto;

public class MemberRepository {
	public void memberSignUp(Member member) {
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
		} catch (SQLException | NoSuchAlgorithmException e) {
			try {
				conn.rollback();
			} catch (SQLException rollbackEx) {
				throw new RuntimeException(e);
			}
		} finally {
			close(conn, pstmt, null);
		}
	}

	public List<MemberInfoDto> findMemberInfoById(long memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select c.cafe_id, c.name, a.coupon_count, s.count as stamp_count "
			+ "from (select cafe_id, count(*) as coupon_count "
			+ "    from coupon "
			+ "    where member_id = ?  "
			+ "    group by cafe_id) a join cafe c "
			+ "on a.cafe_id = c.cafe_id "
			+ "join stamp s "
			+ "on a.cafe_id = s.cafe_id "
			+ "and s.member_id = ? ";

		List<MemberInfoDto> memberInfos = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			pstmt.setLong(2, memberId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MemberInfoDto memberInfo = MemberInfoDto.createMemberDto(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getLong("coupon_count"),
					rs.getLong("stamp_count")
				);
				memberInfos.add(memberInfo);
			}
			return memberInfos;
		} catch (SQLException e) {
			e.printStackTrace();
			// log.info("db error", e);
			throw new RuntimeException();
		} finally {
			close(conn, pstmt, rs);
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
					return new Member(rs.getLong("member_id"), rs.getString("username"), email, storedPassword,
						rs.getString("phone_number"));
				}
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
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
			close(conn, pstmt, rs);
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
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}
}
