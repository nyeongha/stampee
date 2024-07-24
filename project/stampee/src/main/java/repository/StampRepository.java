package repository;

import static Template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Stamp;


public class StampRepository {
	private static final Logger log = LoggerFactory.getLogger(StampRepository.class);

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

	public void delete(long member_id, long cafe_id){
		String sql = "delete from review "
			+ "where member_id = ? "
			+ "and cafe_id = ?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, member_id);
			pstmt.setLong(2, cafe_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(con, pstmt);
		}
	}
}
