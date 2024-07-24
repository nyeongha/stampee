package repository;

import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Stamp;


public class StampRepository {
	private static final Logger log = LoggerFactory.getLogger(StampRepository.class);

	public Stamp save(Stamp stamp){
		String sql = "insert into stamp(count, create_time, member_id, cafe_id) "
			+ "values(?, ?, ?, ?)";

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
			close(con, pstmt, null);
		}
	}

	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("rs close error", e);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				log.info("pstmt close error", e);
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("con close error", e);
			}
		}
	}
}
