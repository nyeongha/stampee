package controller;// package controller;
//
// import static domain.ReviewType.*;
//
// import java.util.List;
//
// import domain.Review;
// import domain.ReviewType;
// import javafx.fxml.FXML;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.layout.FlowPane;
// import repository.CafeRepository;
// import repository.MemberRepository;
// import repository.ReviewRepository;
// import service.ReviewService;
// import view.ReviewView;
//
// public class ReviewController {
// 	@FXML private ScrollPane scrollPane;
// 	private final ReviewService reviewService;
// 	private final ReviewView reviewView = new ReviewView();
// 	@FXML private FlowPane reviewFlowPane;
//
// 	public ReviewController() {
// 		MemberRepository memberRepository = new MemberRepository();
// 		CafeRepository cafeRepository = new CafeRepository();
// 		ReviewRepository reviewRepository = new ReviewRepository(memberRepository, cafeRepository);
// 		reviewService = new ReviewService(reviewRepository);
// 	}
//
// 	public void init(long id, ReviewType type) {
// 		List<Review> reviews;
// 		if (type == CAFE) {
// 			reviews = reviewService.findReviewsByCafeId(id);
// 			displayCafeReviews(reviews);
// 		} else if (type == MEMBER) {
// 			reviews = reviewService.findReviewsByMemberId(id);
// 			displayMyReviews(reviews);
// 		} else {
// 			reviews = reviewService.findAllReviews();
// 			displayReviews(reviews);
// 		}
// 	}
//
// 	private void displayReviews(List<Review> reviews) {
// 		FlowPane reviewsContainer = reviewView.createAllReviewsContainer(reviews);
// 		setScrollPane(reviewsContainer);
// 	}
//
// 	private void displayMyReviews(List<Review> reviews) {
// 		FlowPane reviewsContainer = reviewView.createAllReviewsContainer(reviews);
// 		setScrollPane(reviewsContainer);
// 	}
//
// 	private void displayCafeReviews(List<Review> reviews) {
// 		FlowPane reviewsContainer = reviewView.createAllReviewsContainer(reviews);
// 		setScrollPane(reviewsContainer);
// 	}
//
// 	private void setScrollPane(FlowPane reviewsContainer) {
// 		scrollPane.setContent(reviewsContainer);
// 		scrollPane.setFitToHeight(true); // 스크롤을 수직으로만 사용 가능하게 함
// 		scrollPane.setFitToWidth(true); // 스크롤을 가로와 세로로 사용 가능하게 함
// 		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // 수평 스크롤바 항상 표시
// 		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // 수직 스크롤바 필요 시 표시
// 	}
// }

import static domain.ReviewType.*;

import java.util.Collections;
import java.util.List;

import domain.Review;
import domain.ReviewType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;
import view.ReviewView;

public class ReviewController {
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane reviewFlowPane;
	private final ReviewService reviewService;
	private final ReviewView reviewView = new ReviewView();

	public ReviewController() {
		MemberRepository memberRepository = new MemberRepository();
		CafeRepository cafeRepository = new CafeRepository();
		ReviewRepository reviewRepository = new ReviewRepository();
		reviewService = new ReviewService(reviewRepository);
	}

	public void init(long id, ReviewType type) {
		List<Review> reviews;
		if (type == CAFE) {
			reviews = reviewService.findReviewsByCafeId(id);
			displayCafeReviews(reviews);
		} else if (type == MEMBER) {
			reviews = reviewService.findReviewsByMemberId(id);
			displayMyReviews(reviews);
		} else {
			reviews = reviewService.findAllReviews();
			displayReviews(reviews);
		}
	}

	private void displayReviews(List<Review> reviews) {
		Collections.reverse(reviews); // 리뷰를 역순으로 정렬
		FlowPane reviewsContainer = reviewView.createReviewContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void displayMyReviews(List<Review> reviews) {
		Collections.reverse(reviews); // 리뷰를 역순으로 정렬
		FlowPane reviewsContainer = reviewView.createReviewContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void displayCafeReviews(List<Review> reviews) {
		Collections.reverse(reviews); // 리뷰를 역순으로 정렬
		FlowPane reviewsContainer = reviewView.createReviewContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void setScrollPane(FlowPane reviewsContainer) {
		reviewFlowPane.getChildren().clear(); // 기존 컨텐츠 지우기
		reviewFlowPane.getChildren().addAll(reviewsContainer.getChildren()); // 새로운 컨텐츠 추가
		scrollPane.setContent(reviewFlowPane); // ScrollPane의 컨텐츠를 reviewFlowPane으로 설정
		// scrollPane.setFitToHeight(true); // 수직 스크롤바 사용
		// scrollPane.setFitToWidth(true); // 수평 스크롤바 사용
		// scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // 수평 스크롤바 항상 표시
		// scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // 수직 스크롤바 비활성화
	}
}
