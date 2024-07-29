package repository;

import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dto.ExpiredCouponDto;

public class CouponRepository {
	private final static int NO_COUPON = 0;

	public int findCouponByMemberId(long memberId, long cafe_id){
		String sql = "select count(*) as count "
			+ "from coupon "
			+ "where member_id = ? "
			+ "and cafe_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			pstmt.setLong(2, cafe_id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				return rs.getInt("count");
			}else{
				return NO_COUPON;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			close(conn, pstmt, rs);
		}
	}

	public List<ExpiredCouponDto> findExpiringCoupons(LocalDate localDate){
		String sql = "select m.email, ca.name, m.username, co.endtime - trunc(?) as remain_date "
			+ "from member m join coupon co "
			+ "on m.member_id = co.member_id "
			+ "join cafe ca "
			+ "on co.cafe_id = ca.cafe_id "
			+ "where co.endtime - ? in (1, 3, 7)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(localDate));
			pstmt.setDate(2, Date.valueOf(localDate));
			rs = pstmt.executeQuery();

			List<ExpiredCouponDto> expiredCouponDtos = new ArrayList<>();
			while(rs.next()){
				expiredCouponDtos.add(new ExpiredCouponDto(rs.getString("username"), rs.getString("email"),
					rs.getString("name"), rs.getInt("remain_date")));
			}
			return expiredCouponDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
}
