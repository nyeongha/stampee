package repository;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

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
	private Review review;

	@Mock
	private PreparedStatement pstmt;

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

		review = new Review(
			1L, // review_id
			5, // rating
			"Updated contents", // contents
			new Date(System.currentTimeMillis()), // create_time
			new Member(5L, "Minyoung", "dslkf@dsfs.com", "asdfad123", "010-2345-3456"),
			new Cafe(1L, "커피좋아", "서울시 어쩌구", "1234", "1212@sdlkf.com", "010-2222-3333")
		);

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



}
