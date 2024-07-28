package repository;

import static template.ConnectionClose.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Cafe;
import domain.Member;
import domain.Review;

public abstract class ReviewRepository {

	public List<Review> findAllReviews() {        //전체 리뷰 조회,서비스 반영완,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

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
					rs.getDate("create_time"),
					member,
					cafe);

				reviews.add(review);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}

		return reviews;
	}

	public void insertReview(Review review) {        //리뷰 생성,서비스 반영,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into review (review_id, rating, contents, create_time, member_id, cafe_id) "
			+ "values (review_seq.nextval, ?, ?, ?, ?, ?)";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getRating());
			pstmt.setString(2, review.getContents());
			pstmt.setDate(3, review.getCreateTime());
			pstmt.setLong(4, review.getMember().getId());
			pstmt.setLong(5, review.getCafe().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}

	public void updateReview(Review review) {        //리뷰 수정,서비스 반영,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update review "
			+ "set rating=?, contents=? "
			+ "where review_id=?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, review.getRating());
			pstmt.setString(2, review.getContents());
			pstmt.setLong(3, review.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt);
		}
	}

	public void deleteReviewByReviewId(long memberId, long reviewId) {        //리뷰삭제,서비스 반영,테스트 완
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = getConnection();
			cstmt = conn.prepareCall("{call delete_review_by_review_id(?, ?)}");
			cstmt.setLong(1, memberId);
			cstmt.setLong(2, reviewId);
			cstmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			// 리소스 정리
			close(conn, cstmt);
		}
	}

	public List<Review> findReviewsByMemberId(long memberId) {        //멤버별 리뷰 조회,서비스 반영,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT r.review_id, r.rating, r.contents, r.create_time, " +
			"m.member_id, m.password, m.email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password_1, c.email_1, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON r.cafe_id = c.cafe_id " +
			"WHERE m.member_id = ?";

		List<Review> reviews = new ArrayList<Review>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("username"),
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
					rs.getDate("create_time"),
					member,
					cafe
				);
				reviews.add(review);
			}

		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}

		return reviews;
	}

	public List<Review> findReviewsByCafeId(long cafeId) {            //카페리뷰조회,서비스 반영
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT r.review_id, r.rating, r.contents, r.create_time, " +
			"m.member_id, m.password, m.email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password_1, c.email_1, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON r.cafe_id = c.cafe_id " +
			"WHERE c.cafeId = ?";

		List<Review> reviews = new ArrayList<Review>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cafeId);

			rs = pstmt.executeQuery();

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
					rs.getDate("create_time"),
					member,
					cafe
				);
				reviews.add(review);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstmt, rs);
		}

		return reviews;
	}

	protected abstract Connection getConnection();
}