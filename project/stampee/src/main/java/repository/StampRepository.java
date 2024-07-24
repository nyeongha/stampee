package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Stamp;


public class StampRepository {
	public Stamp save(Stamp stamp){
		String sql = "insert into stamp(stamp_id, count, create_time, member_id, cafe_id) "
			+ "values(STAMP_SEQ.NEXTVAL, ?, ?, ?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, stamp.getCount());
			pstmt.setDate(2, Date.valueOf(stamp.getCreateTime()));
			pstmt.setLong(3, stamp.getMember().getMemberId());
			pstmt.setLong(4, stamp.getCafe().getCafeId());

			pstmt.executeUpdate();

			return stamp;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt);
		}
	}
}
