package repository;

import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Cafe;

public class CafeRepository {

	public void cafeSignUp(Cafe cafe) {
		// SQL
		String sql = "insert into cafe(cafe_Id, name, address, password, email, contact) "
			+ "values(CAFE_SEQ.NEXTVAL,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// check input data is null
			if (cafe.getName() == null || cafe.getAddress() == null || cafe.getPassword() == null || cafe.getEmail() == null || cafe.getContact() == null) {
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
	


	public void login(String email, String password){

	}

}
