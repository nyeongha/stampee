package repository;

import static config.DBConnectionUtil.*;
import static config.ConnectionClose.*;

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

public class ReviewRepository {
	private MemberRepository memberRepository;
	private CafeRepository cafeRepository;

	public ReviewRepository(MemberRepository memberRepository, CafeRepository cafeRepository) {
		this.memberRepository = memberRepository;
		this.cafeRepository = cafeRepository;
	}

	public List<Review> findAllReviews() {
		String sql = "SELECT "
			+ "r.review_id, r.rating, r.contents, r.create_time, "
			+ "m.phone_number, "
			+ "c2.contact "
			+ "FROM review r "
			+ "JOIN member m ON r.member_id = m.member_id "
			+ "JOIN cafe c2 ON r.cafe_id = c2.cafe_id";

		List<Review> reviews = new ArrayList<>();

		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				long reviewId = rs.getLong("review_id");
				String contact = rs.getString("contact");
				String phoneNumber = rs.getString("phone_number");

				// Member와 Cafe를 한 번만 조회하도록 개선
				Member member = memberRepository.findUserByPhoneNum(phoneNumber);
				Cafe cafe = cafeRepository.findCafeByContact(contact);

				Review review = new Review(
					reviewId,
					rs.getInt("rating"),
					rs.getString("contents"),
					rs.getDate("create_time"),
					member,
					cafe
				);
				reviews.add(review);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e);
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
			close(conn, pstmt, null);
		}
	}

	public void updateReview(Review review) {        //리뷰 수정,서비스 반영,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update review "
			+ "set rating=?, contents=? "
			+ "where review_id = ?";

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
			close(conn, pstmt, null);
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
			close(conn, cstmt, null);
		}
	}

	public List<Review> findReviewsByMemberId(long memberId) {        //멤버별 리뷰 조회,서비스 반영,테스트 완
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT r.review_id, r.rating, r.contents, r.create_time, m.username, " +
			"m.member_id, m.password AS member_password, m.email AS member_email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password AS cafe_password, c.email AS cafe_email, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON c.cafe_id = r.cafe_id " +
			"WHERE m.member_id = ?";

		List<Review> reviews = new ArrayList<>();

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("username"),
					rs.getString("member_password"),
					rs.getString("member_email"),
					rs.getString("phone_number")
				);

				Cafe cafe = new Cafe(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("cafe_password"),
					rs.getString("cafe_email"),
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

		String sql = "SELECT r.review_id, r.rating, r.contents, r.create_time, m.username, " +
			"m.member_id, m.password AS member_password, m.email AS member_email, m.phone_number, " +
			"c.cafe_id, c.name, c.address, c.password AS cafe_password, c.email AS cafe_email, c.contact " +
			"FROM review r " +
			"JOIN member m ON r.member_id = m.member_id " +
			"JOIN cafe c ON c.cafe_id = r.cafe_id " +
			"WHERE c.cafe_id = ?";


		List<Review> reviews = new ArrayList<>();
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cafeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = Member.createMember(
					rs.getLong("member_id"),
					rs.getString("username"),
					rs.getString("member_password"),
					rs.getString("member_email"),
					rs.getString("phone_number")
				);

				Cafe cafe = new Cafe(
					rs.getLong("cafe_id"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("cafe_password"),
					rs.getString("cafe_email"),
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
}