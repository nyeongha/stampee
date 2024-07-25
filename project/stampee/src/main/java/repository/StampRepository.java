package repository;

import static template.ConnectionClose.*;
import static config.DBConnectionUtil.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Cafe;
import domain.Member;
import domain.Stamp;

public class StampRepository {
	private static final Logger log = LoggerFactory.getLogger(StampRepository.class);

	public void save(long memberId, long cafeId, int count){
		String sql = "{ call add_stamp(?, ?, ?)}";
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = getConnection();
			cstmt = conn.prepareCall(sql);
			cstmt.setLong(1, memberId);
			cstmt.setLong(2, cafeId);
			cstmt.setInt(3, count);
			cstmt.execute();
		} catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, cstmt);
		}
	}

	public Stamp findStamp(long memberId, long cafeId) {
		String sql = "select * "
			+ "from stamp s join member m "
			+ "on s.member_id = m.member_id "
			+ "join cafe c on s.cafe_id = c.cafe_id "
			+ "where s.member_id = ? "
			+ "and s.cafe_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			pstmt.setLong(2, cafeId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Stamp(rs.getLong("stamp_id"),
					rs.getInt("count"),
					rs.getDate("create_time"),
					getMember(rs), getCafe(rs));
			} else{
				return null;
			}
		}  catch (SQLException e) {
			log.info("db error", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}

	// public void updateStamp(long cafeId, long toMemberId, long fromMemberId, int count){
	// 	String sql = "{ call share_stamp(?, ?, ?)}";
	// 	Connection conn = null;
	// 	CallableStatement cstmt = null;
	// }

	private static Cafe getCafe(ResultSet rs) throws SQLException {
		return new Cafe(rs.getLong("cafe_id"),
			rs.getString("name"),
			rs.getString("address"),
			rs.getString("password"),
			rs.getString("email"),
			rs.getString("contact"));
	}

	private static Member getMember(ResultSet rs) throws SQLException {
		return Member.createMember(rs.getLong("member_id"),
			rs.getString("password"),
			rs.getString("email"),
			rs.getString("phone_number"),
			rs.getString("username"));
	}
}
