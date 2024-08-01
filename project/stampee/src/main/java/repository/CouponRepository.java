package repository;

import static config.DBConnectionUtil.*;
import static java.time.LocalDate.*;
import static config.ConnectionClose.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.response.ExpiredCouponDto;
import dto.response.MyCouponDto;

public class CouponRepository {
	private final static int NO_COUPON = 0;

	public List<MyCouponDto> findCouponByMemberId(long memberId) {
		String sql = "select c.cafe_id, c.name, c.address, a.count "
			+ " from (select cafe_id, count(*) as count "
			+ "			from coupon"
			+ "			where member_id = ?"
			+ "			group by cafe_id) a join cafe c "
			+ "on a.cafe_id = c.cafe_id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			rs = pstmt.executeQuery();

			List<MyCouponDto> myCouponDtos = new ArrayList<>();
			while (rs.next()) {
				myCouponDtos.add(MyCouponDto.createMyCouponDto(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getInt("count")));
			}
			return myCouponDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}

	public int findCouponByMemberIdAndCafeId(long memberId, long cafeId) {
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
			pstmt.setLong(2, cafeId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("count");
			} else {
				return NO_COUPON;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}

	public int findExpiringCouponCount(long memberId, long cafeId) {
		String sql = "select count(*) as count "
			+ "from coupon "
			+ "where member_id = ? "
			+ "and cafe_id = ? "
			+ "and endtime - trunc(?) <= 7";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			pstmt.setLong(2, cafeId);
			pstmt.setDate(3, Date.valueOf(now()));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("count");
			} else {
				return NO_COUPON;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}

	public List<ExpiredCouponDto> findExpiringCoupons(LocalDate localDate) {
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
			while (rs.next()) {
				expiredCouponDtos.add(new ExpiredCouponDto(rs.getString("username"), rs.getString("email"),
					rs.getString("name"), rs.getInt("remain_date")));
			}
			return expiredCouponDtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}

	public void deleteExpiredCoupons(LocalDate localDate) {
		String sql = "delete from coupon where endtime <= ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(localDate));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt,null);
		}
	}
}
