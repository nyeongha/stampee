package repository;

import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Cafe;
import domain.Member;

public class CafeRepository {
	private static final Logger log = LoggerFactory.getLogger(CafeRepository.class);

	public void cafeSignUp(Cafe cafe) {
		// SQL
		String sql = "insert into cafe(cafe_Id, name, address, password, email, contact) "
			+ "values(CAFE_SEQ.NEXTVAL,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// check input data is null
			if (cafe.getName() == null || cafe.getAddress() == null || cafe.getPassword() == null
				|| cafe.getEmail() == null || cafe.getContact() == null) {
				throw new IllegalArgumentException("Name and address and password and email and contact can't be null");
			}
			// check email format
			if (!cafe.getEmail().matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				throw new IllegalArgumentException("Invalid email format");
			}

			// Generate salt and hash password
			String encryptedPassword = hashPassword(cafe.getPassword());

			// DB connection
			conn = getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cafe.getName());
			pstmt.setString(2, cafe.getAddress());
			pstmt.setString(3, encryptedPassword);
			pstmt.setString(4, cafe.getEmail());
			pstmt.setString(5, cafe.getContact());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}


	public boolean login(Cafe cafe){
		//sql
		String sql = "select password from cafe where email = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//db connection
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cafe.getEmail());
			rs = pstmt.executeQuery();

			if(rs.next()){
				String storedPassword = rs.getString("password");
				return verifyPassword(cafe.getPassword(), storedPassword);
			}
			else{
				System.out.println("email not found : "+cafe.getEmail());
				return false;
			}

		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}

	}

	public List<Member> findCafeMembersById(int cafeId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * "
			+ "from member "
			+ "where member_id in (select member_id "
			+ "                      from stamp "
			+ "                     where cafe_id = ?)";

		List<Member> members = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cafeId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = Member.createMember(
					rs.getInt("member_id"),
					rs.getString("username"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("phone_number")
				);
				members.add(member);
			}
			return members;
		} catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException();
		} finally {
			close(conn, pstmt, rs);
		}
	}
}
