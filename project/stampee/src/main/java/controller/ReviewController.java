package controller;// package controller;
import static domain.ReviewType.*;

import java.util.List;

import domain.Review;
import domain.ReviewType;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import repository.ReviewRepository;
import service.ReviewService;
import view.ReviewView;

public class ReviewController {
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane reviewFlowPane;
	private final ReviewService reviewService;
	private final ReviewView reviewView = new ReviewView();

	@FXML
	private ToggleButton toggleButton;

	public ReviewController() {
		ReviewRepository reviewRepository = new ReviewRepository();
		reviewService = new ReviewService(reviewRepository);
	}

	public void init(long id, ReviewType type) {
		List<Review> reviews;
		if (type == CAFE) {
			reviews = reviewService.findReviewsByCafeId(id);
			displayReviews(reviews);
		} else if (type == MEMBER) {
			reviews = reviewService.findReviewsByMemberId(id);
			displayReviews(reviews);
		} else {
			reviews = reviewService.findAllReviews();
			displayReviews(reviews);
		}
	}

	private void displayReviews(List<Review> reviews) {
		FlowPane reviewsContainer = reviewView.createReviewContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void setScrollPane(FlowPane reviewsContainer) {
		reviewFlowPane.getChildren().clear(); // 기존 컨텐츠 지우기
		reviewFlowPane.getChildren().addAll(reviewsContainer.getChildren()); // 새로운 컨텐츠 추가
		scrollPane.setContent(reviewFlowPane); // ScrollPane의 컨텐츠를 reviewFlowPane으로 설정
	}
}
