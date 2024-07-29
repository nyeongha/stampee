package controller;

import java.sql.SQLException;
import java.util.List;

import domain.Member;
import domain.Review;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;

public class ReviewController {
	@FXML
	private FlowPane reviewFlowPane;

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@FXML
	public void initialize() throws SQLException {
		List<Review> reviews = reviewService.findAllReviews();
		System.out.println(reviews.size());
		for (Review review : reviews) {
			reviewFlowPane.getChildren().add(createReviewBox(review));
		}
	}

	private VBox createReviewBox(Review review) {
		VBox reviewBox = new VBox();
		reviewBox.setStyle("-fx-border-color: black; -fx-padding: 10; -fx-background-color: #f0f0f0;");
		reviewBox.setPrefSize(280, 150); // Adjust size to fit two boxes per row

		// Extract the first 20 characters and add "..." if necessary
		String content = review.getContents();
		String shortContent = content.length() > 20 ? content.substring(0, 20) + "..." : content;

		// Create labels
		Label titleLabel = new Label("Title: " + shortContent);
		Label authorLabel = new Label("Author: " + review.getMember().getUserName());
		Label ratingLabel = new Label("Rating: " + review.getRating());

		// Only add full content if it is not displayed in the title
		Label contentLabel = new Label("Content: " + content);

		// Add labels to VBox
		reviewBox.getChildren().addAll(titleLabel, authorLabel, contentLabel, ratingLabel);
		return reviewBox;
	}

}


