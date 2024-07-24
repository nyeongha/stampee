package repository;

import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import domain.Stamp;

public class StampRepository {
	public Stamp save(long memberId, long cafeId, int count){
		String sql = "insert into stamp(count, create_time, member_id, cafe_id) "
			+ "values(?, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setDate(2, Date.valueOf(LocalDate.now()));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public Stamp update(Stamp stamp){
		String sql = "";

		Connection con = null;

		con = getConnection();

		return null;
	}
}
