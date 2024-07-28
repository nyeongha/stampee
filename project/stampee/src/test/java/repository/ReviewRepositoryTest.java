package repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import domain.Cafe;
import domain.Member;
import domain.Review;

public class ReviewRepositoryTest {

	@Mock
	private Connection conn;

	@Mock
	private ResultSet rs;

	@Mock
	private Review review;

	@Mock
	private PreparedStatement pstmt;

	@Mock
	private CallableStatement cstmt;

	@Mock
	private ReviewRepository reviewRepository;

	@Mock
	private Member member;

	@Mock
	private Cafe cafe;


	@BeforeEach
	void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);

		when(conn.prepareStatement(anyString())).thenReturn(pstmt);
		when(conn.prepareCall(anyString())).thenReturn(cstmt);

		// Example Review object for testing
		review = new Review(
			1L, // review_id
			5, // rating
			"Test contents", // contents
			new Date(System.currentTimeMillis()), // create_time
			new Member(1L, "Test User", "testpassword", "test@example.com", "010-1234-5678"),
			new Cafe(1L, "Test Cafe", "Test Address", "testpass", "testcafe@example.com", "010-9876-5432")
		);

		// 결과를 설정할 ResultSet 모킹
		when(rs.next()).thenReturn(true, true, false); // 세 개의 결과 레코드

		// 첫 번째와 두 번째 리뷰는 동일한 memberId (5L), 세 번째는 다른 memberId (6L)
		when(rs.getLong("review_id")).thenReturn(1L, 3L, 4L);
		when(rs.getInt("rating")).thenReturn(5, 4, 3);
		when(rs.getString("contents")).thenReturn("Updated contents", "Updated contents", "Updated contents");
		when(rs.getDate("create_time")).thenReturn(new Date(System.currentTimeMillis()), new Date(2024-07-24), new Date(2024-07-24));

		// 필드 설정
		when(rs.getLong("member_id")).thenReturn(5L, 5L, 6L); // memberId를 5L, 5L, 6L로 설정
		when(rs.getString("username")).thenReturn("Minyoung", "Minyoung", "AnotherUser");
		when(rs.getString("password")).thenReturn("asdfad123", "asdfad123", "anotherpass");
		when(rs.getString("email")).thenReturn("dslkf@dsfs.com", "dslkf@dsfs.com", "anotheremail@domain.com");
		when(rs.getString("phone_number")).thenReturn("010-2345-3456", "010-2345-3456", "010-9876-5432");

		when(rs.getLong("cafe_id")).thenReturn(1L, 1L, 2L);
		when(rs.getString("name")).thenReturn("커피좋아", "커피좋아", "AnotherCafe");
		when(rs.getString("address")).thenReturn("서울시 어쩌구", "서울시 어쩌구", "AnotherAddress");
		when(rs.getString("password_1")).thenReturn("1234", "1234", "anotherpass1");
		when(rs.getString("email_1")).thenReturn("1212@sdlkf.com", "1212@sdlkf.com", "anothercafe@domain.com");
		when(rs.getString("contact")).thenReturn("010-2222-3333", "010-2222-3333", "010-4444-5555");

		when(pstmt.executeQuery()).thenReturn(rs);

		reviewRepository = spy(new ReviewRepository() {
			@Override
			protected Connection getConnection() {
				return conn;
			}
		});


	}

	@Test
	@DisplayName("insert")
	void testInsertReview() throws SQLException {
		// When
		reviewRepository.insertReview(review);

		// Then
		verify(conn).prepareStatement(anyString());
		verify(pstmt).setInt(1, review.getRating());
		verify(pstmt).setString(2, review.getContents());
		verify(pstmt).setDate(3, review.getCreateTime());
		verify(pstmt).setLong(4, review.getMember().getId());
		verify(pstmt).setLong(5, review.getCafe().getId());
		verify(pstmt).executeUpdate();
	}


	@Test
	@DisplayName(" testUpdateReview")
	void testUpdateReview() throws SQLException {
		// When
		reviewRepository.updateReview(review);

		// Then
		verify(conn).prepareStatement(anyString());
		verify(pstmt).setInt(1, review.getRating());
		verify(pstmt).setString(2, review.getContents());
		verify(pstmt).setLong(3, review.getId());
		verify(pstmt).executeUpdate();
	}

	@Test
	@DisplayName("testFindReviewsByMemberId")
	public void testFindReviewsByMemberId() throws SQLException {
		// Given
		long memberId = 5L;

		// When
		List<Review> reviews = reviewRepository.findReviewsByMemberId(memberId);

		// Then
		assertEquals(2, reviews.size(), "리뷰 개수가 일치해야 합니다.");
		for (Review review : reviews) {
			assertEquals(memberId, review.getMember().getId(), "리뷰의 memberId가 예상과 일치해야 합니다.");
		}
	}

	@Test
	@DisplayName("testFindReviewsByCafeId")
	public void testFindReviewsByCafeId(){

		//Given
		long cafeId=1L;

		//when
		List<Review> reviews = reviewRepository.findReviewsByCafeId(cafeId);

		// Then
		assertEquals(2, reviews.size(), "리뷰 개수가 일치해야 합니다.");
		for (Review review : reviews) {
			assertEquals(cafeId, review.getCafe().getId(), "리뷰의 memberId가 예상과 일치해야 합니다.");
		}



	}

	@Test
	public void testDeleteByReviewId(){
		// given
		long reviewId = 3L;
		long memberId = 5L;

		// when
		reviewRepository.deleteReviewByReviewId(memberId, reviewId);
		List<Review> reviews = reviewRepository.findAllReviews();

		// then
		assertEquals(2, reviews.size(), "리뷰 개수가 일치해야 합니다.");

	}





}
