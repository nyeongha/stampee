package service;

import java.sql.Clob;
import java.sql.Date;
import java.util.List;

import domain.Cafe;
import domain.Member;
import domain.Review;
import repository.ReviewRepository;

public class ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public void deleteReview(long reviewId, long memberId) {
		reviewRepository.deleteReviewByReviewId(reviewId, memberId);
	}

	public void updateReview(long id, int rating, String contents) {
		Review review = new Review(id, rating, contents);
		reviewRepository.updateReview(review);
	}

	public void insertReview(int rating, String contents, Date date, Member member, Cafe cafe) {
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
