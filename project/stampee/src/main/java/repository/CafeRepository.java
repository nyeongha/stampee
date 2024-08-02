package repository;

import static config.DBConnectionUtil.*;
import static config.ConnectionClose.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Cafe;
import dto.response.CafeMemberInfoDto;

public class CafeRepository {
	public long cafeSignUp(Cafe cafe, String menu1, String menu2) {
		String insertCafeSql = "insert into cafe(cafe_Id, name, address, password, email, contact) "
			+ "values(CAFE_SEQ.NEXTVAL,?,?,?,?,?)";
		String insertSignatureSql = "insert into signature(menu_id, menu_name, cafe_id)"
			+ "values(SIGNATURE_SEQ.NEXTVAL, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);  // 트랜잭션 시작

			// 카페 정보 삽입
			pstmt = conn.prepareStatement(insertCafeSql, new String[] {"cafe_id"});
			pstmt.setString(1, cafe.getName());
			pstmt.setString(2, cafe.getAddress());
			pstmt.setString(3, hashPassword(cafe.getPassword()));
			pstmt.setString(4, cafe.getEmail());
			pstmt.setString(5, cafe.getContact());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating cafeId failed, no rows affected.");
			}

			// 생성된 cafe_id 가져오기
			rs = pstmt.getGeneratedKeys();
			//insert문 실행 후 자동 생성된 키 값을 포함하는 ResultSet 객체 반환
			long cafeId;
			if (rs.next()) {
				cafeId = rs.getLong(1);
				//자동 생성된 cafe_id를 가져옴
			} else {
				throw new SQLException("Creating cafe failed, no ID obtained.");
			}

			// 시그니처 메뉴 삽입을 위해 pstmt 재설정
			pstmt.close(); // 기존 pstmt 닫기
			pstmt = conn.prepareStatement(insertSignatureSql);
			pstmt.setString(1, menu1);
			pstmt.setLong(2, cafeId);
			pstmt.executeUpdate();

			pstmt.setString(1, menu2);
			pstmt.setLong(2, cafeId);
			pstmt.executeUpdate();

			conn.commit();  // 트랜잭션 커밋
			return cafeId;
		} catch (SQLException | NoSuchAlgorithmException e) {
			if (conn != null) {
				try {
					conn.rollback();  // 예외 발생 시 롤백
				} catch (SQLException ex) {
					throw new RuntimeException(e);
				}
			}
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public Cafe login(String email, String password) {
		String sql = "SELECT * FROM cafe WHERE email = ?";
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
					return new Cafe(rs.getLong("cafe_id"), rs.getString("name"), rs.getString("address"),
						storedPassword, email,rs.getString("contact"));
				}
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return null;
	}

	public List<CafeMemberInfoDto> findCafeMemberInfoById(long cafeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select "
			+ "m.member_id, "
			+ "m.username, "
			+ "sc.stamp_count, "
			+ "sc.coupon_count "
			+ "from member m left outer join (select nvl(s.member_id, c.member_id) as member_id, "
			+ "                                      nvl(s.cafe_id, c.cafe_id) as cafe_id, "
			+ "                                      nvl(s.count,0) as stamp_count,  "
			+ "                                      nvl(c.count,0) as coupon_count "
			+ "                                 from stamp s full outer join coupon c "
			+ "                                   on s.cafe_id = c.cafe_id "
			+ "                                  and s.member_id = c.member_id "
			+ "                                  ) sc "
			+ "on m.member_id = sc.member_id "
			+ "where sc.cafe_id = ?";

		List<CafeMemberInfoDto> memberInfos = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cafeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CafeMemberInfoDto memberInfo = CafeMemberInfoDto.createCafeMemberDto(
					rs.getLong("member_id"),
					rs.getString("username"),
					rs.getLong("stamp_count"),
					rs.getLong("coupon_count")
				);
				memberInfos.add(memberInfo);
			}
			return memberInfos;
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			close(conn, pstmt, rs);
		}
	}

	public List<String> findSignatureByCafeId(long cafeId) {
		String sql = "select menu_name "
			+ "from signature "
			+ "where cafe_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> menus = new ArrayList<>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cafeId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				menus.add(rs.getString("menu_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return menus;
	}

	public Cafe findCafeById(long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * "
			+ "from cafe "
			+ "where cafe_id=?";

		Cafe cafe = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cafe = new Cafe(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("contact")
				);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return cafe;
	}
}
