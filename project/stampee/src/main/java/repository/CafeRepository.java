package repository;

import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;
import static util.PasswordUtil.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CafeRepository {

	public void userSignUp(String name, String address, String password, String email, String contact) {
		// SQL
		String sql = "insert into cafe(cafe_Id, name, address, password, email, contact) "
			+ "values(CAFE_SEQ.NEXTVAL,?,?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// check input data is null
			if (name == null || address == null || password == null || email == null || contact == null) {
				throw new IllegalArgumentException("Name and address and password and email and contact can't be null");
			}
			// check email format
			if (!email.matches("[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				throw new IllegalArgumentException("Invalid email format");
			}

			// Generate salt and hash password
			String encryptedPassword = hashPassword(password);

			// DB connection
			conn = getConnection();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, encryptedPassword);
			pstmt.setString(4, email);
			pstmt.setString(5, contact);
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}
	//find encryptedPassword
	// public void findEncryptedPassword(String encryptedPassword){
	// 	//sql
	// 	String sql = "select ? from cafe";
	// 	Connection conn = null;
	// 	PreparedStatement pstmt = null;
	//
	// 	conn = getConnection();
	// 	try {
	// 		pstmt = conn.prepareStatement(sql);
	// 		pstmt.setString(1,encryptedPassword );
	// 		pstmt.executeUpdate();
	//
	// 	}catch (RuntimeException e){
	// 		throw new RuntimeException(e);
	//
	// 	}finally{
	// 		close(conn, pstmt);
	// 	}
	// }



	public void login(String email, String password){

	}

}
