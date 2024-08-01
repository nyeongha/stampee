package service;

import java.sql.Date;
import java.util.List;

import domain.Cafe;
import domain.Member;
import domain.Review;
import lombok.RequiredArgsConstructor;
import repository.ReviewRepository;

@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;

	public boolean deleteReview(long reviewId, long memberId) {
		return reviewRepository.deleteReviewByReviewId(reviewId, memberId);
	}

	public void updateReview(long id, int rating, String contents) {
		Review review = new Review(id, rating, contents);
		reviewRepository.updateReview(review);
	}

	public float getCafeRatingAvg(long cafeId) {
		return reviewRepository.cafeAvgOfRating(cafeId);
	}

	public void insertReview(float rating, String contents, Date date, Member member, Cafe cafe) {
		Review review = new Review(rating, contents, date, member, cafe);
		reviewRepository.insertReview(review);
	}

	public List<Review> findAllReviews() {
		return reviewRepository.findAllReviews();
	}

	public List<Review> findReviewsByMemberId(long memberId) {
		return reviewRepository.findReviewsByMemberId(memberId);
	}

	public List<Review> findReviewsByCafeId(long cafeId) {
		return reviewRepository.findReviewsByCafeId(cafeId);
	}
}
