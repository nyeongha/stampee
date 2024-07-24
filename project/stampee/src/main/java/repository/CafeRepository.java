package repository;

import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Cafe;

public class CafeRepository {

	public void userSignUp(long cafeId, String name, String address, String password, String email, String contact){
		//sql
		String sql = "insert into cafe(cafe_Id, name, address, password, email, contact) values(?,?,?,?,?,?) " ;

		try {
			//db connection
			Connection conn = getConnection();
			conn.setAutoCommit(false);

			Cafe cafe = new Cafe();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cafeId);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, password);
			pstmt.setString(5, email);
			pstmt.setString(6, contact);
			pstmt.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
