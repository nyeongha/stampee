package repository;

import static config.DBConnectionUtil.*;
import static template.ConnectionClose.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Cafe;
import domain.Member;
import domain.Review;

public class ReviewRepository {

	public List<Review> findAllReviews() {		//전체 리뷰 조회
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * "
			+ "from review r "
			+ "join member m "
			+ "on r.member_id=m.member_id "
			+ "join cafe c2 "
			+ "on r.cafe_id=c2.cafe_id ";

		List<Review> reviews = new ArrayList<>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("password"),
					rs.getString("userName"),
					rs.getString("email"),
					rs.getString("phone_number")
				);

				Cafe cafe = new Cafe(rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("password_1"),
					rs.getString("email_1"),
					rs.getString("contact")
				);

				Review review = new Review(rs.getLong("review_id"),
					rs.getInt("rating"),
					rs.getString("contents"),
					rs.getDate("createTime"),
					member,
					cafe);

				reviews.add(review);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, ps, rs);
		}

		return reviews;
	}

	public void insertReview(Review review) {		//리뷰 생성,서비스 반영
		Connection conn = null;
		PreparedStatement ps = null;

		String sql = "insert into review (review_id, rating, contents, createTime, member_id, cafe_id) values (review_seq.nextval, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, review.getRating());
			ps.setString(2, review.getContents());
			ps.setDate(3, review.getCreateTime());
			ps.setLong(4, review.getMember().getId());
			ps.setLong(5, review.getCafe().getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void updateReview(Review review) {		//리뷰 수정,서비스 반영
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update review "
			+ "set rating=?, contents=?, "
			+ "where review_id=?";

		try {
			conn = ps.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setLong(1, review.getId());
			ps.setInt(2, review.getRating());
			ps.setString(3, review.getContents());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, ps);
		}
	}

	public void deleteReviewByReviewId(long memberId, long reviewId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 첫 번째 쿼리: reviewId에 해당하는 member_id를 가져오기
			String sql1 = "select member_id from review where review_id=?";
			ps = conn.prepareStatement(sql1);
			ps.setLong(1, reviewId);
			rs = ps.executeQuery();

			if (rs.next()) {
				long reviewMemberId = rs.getLong("member_id");

				// memberId가 일치하면 삭제 쿼리 실행
				if (reviewMemberId == memberId) {
					String sql2 = "delete from review where review_id=?";
					ps = conn.prepareStatement(sql2);
					ps.setLong(1, reviewId);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 리소스 정리
			close(conn,ps,rs);
		}
	}

	public List<Review> findReviewsBymemberId(long memberId) {        //멤버별 리뷰 조회
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT r.review_id, r.rating, r.contents, r.createTime, " +
			"m.member_id, m.password, m.email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password_1, c.email_1, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON r.cafe_id = c.cafe_id " +
			"WHERE m.memberId = ?";

		List<Review> reviews = new ArrayList<Review>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, memberId);

			rs = ps.executeQuery();

			while (rs.next()) {
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("userName"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("phone_number")
				);

				Cafe cafe = new Cafe(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("password_1"),
					rs.getString("email_1"),
					rs.getString("contact")
				);

				Review review = new Review(
					rs.getLong("review_id"),
					rs.getInt("rating"),
					rs.getString("contents"),
					rs.getDate("createTime"),
					member,
					cafe
				);
				reviews.add(review);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, ps, rs);
		}

		return reviews;
	}

	public List<Review> findReviewsByCafeId(long cafeId) {            //카페리뷰조회
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT r.review_id, r.rating, r.contents, r.createTime, " +
			"m.member_id, m.password, m.email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password_1, c.email_1, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON r.cafe_id = c.cafe_id " +
			"WHERE c.cafeId = ?";

		List<Review> reviews = new ArrayList<Review>();

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setLong(1, cafeId); // ID를 설정하여 조회

			rs = ps.executeQuery();

			if (rs.next()) {
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("userName"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("phone_number")
				);

				Cafe cafe = new Cafe(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("password_1"),
					rs.getString("email_1"),
					rs.getString("contact")
				);

				Review review = new Review(
					rs.getLong("review_id"),
					rs.getInt("rating"),
					rs.getString("contents"),
					rs.getDate("createTime"),
					member,
					cafe
				);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, ps, rs);
		}

		return reviews;
	}

}