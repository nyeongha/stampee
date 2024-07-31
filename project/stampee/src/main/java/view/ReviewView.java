package view;// package view;
//
// import java.util.List;
// import domain.Review;
// import javafx.scene.control.Label;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
//
// public class ReviewView {
// 	private static final double SPACING = 10;
// 	private static final String CONTAINER_STYLE = "-fx-padding: 10px;";
// 	private static final String BLOCK_CONTAINER_STYLE = "-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;";
// 	private static final String REVIEW_LABEL_STYLE = "-fx-padding: 10px;";
//
// 	public HBox createAllReviewsContainer(List<Review> reviews) {
// 		HBox reviewsContainer = new HBox(SPACING);
// 		reviewsContainer.setStyle(CONTAINER_STYLE);
//
// 		VBox currentColumn = new VBox(SPACING);
// 		reviewsContainer.getChildren().add(currentColumn);
//
// 		for (int i = 0; i < reviews.size(); i++) {
// 			Review review = reviews.get(i);
// 			VBox blockContainer = createBlockContainer(review);
// 			currentColumn.getChildren().add(blockContainer);
//
// 			if ((i + 1) % 2 == 0) {
// 				currentColumn = new VBox(SPACING);
// 				reviewsContainer.getChildren().add(currentColumn);
// 			}
// 		}
//
// 		return reviewsContainer;
// 	}
//
// 	public HBox createMyReviewContainer(List<Review> reviews) {
// 		HBox reviewsContainer = new HBox(SPACING);
// 		reviewsContainer.setStyle(CONTAINER_STYLE);
//
// 		VBox currentColumn = new VBox(SPACING);
// 		reviewsContainer.getChildren().add(currentColumn);
//
// 		for (int i = 0; i < reviews.size(); i++) {
// 			Review review = reviews.get(i);
// 			VBox blockContainer = createBlockContainer(review);
// 			currentColumn.getChildren().add(blockContainer);
//
// 			currentColumn = new VBox(SPACING);
// 			reviewsContainer.getChildren().add(currentColumn);
// 		}
//
// 		return reviewsContainer;
// 	}
//
// 	private VBox createBlockContainer(Review review) {
// 		Label reviewLabel = createReviewLabel(review);
//
// 		VBox blockContainer = new VBox(5, reviewLabel);
// 		blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
//
// 		return blockContainer;
// 	}
//
// 	private Label createReviewLabel(Review review) {
// 		Label reviewLabel = new Label(review.toString());
// 		reviewLabel.setWrapText(true);
// 		reviewLabel.setStyle(REVIEW_LABEL_STYLE);
//
// 		return reviewLabel;
// 	}
// }


import java.util.List;
import domain.Review;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReviewView {
	private static final double SPACING = 10;
	private static final String BLOCK_CONTAINER_STYLE = "-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;";
	private static final String REVIEW_LABEL_STYLE = "-fx-padding: 10px;";
	private static final String CONTAINER_STYLE = "-fx-padding: 10px;";

	public FlowPane createReviewContainer(List<Review> reviews) {
		FlowPane reviewsContainer = new FlowPane(SPACING, SPACING);
		reviewsContainer.setStyle("-fx-padding: 10px;");
		reviewsContainer.setOrientation(javafx.geometry.Orientation.HORIZONTAL); // 수평 방향으로 설정

		for (Review review : reviews) {
			VBox blockContainer = createBlockContainer(review);
			reviewsContainer.getChildren().add(blockContainer);
		}

		return reviewsContainer;
	}

	private VBox createBlockContainer(Review review) {
		Label reviewLabel = createReviewLabel(review);

		VBox blockContainer = new VBox(5, reviewLabel);
		blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
		blockContainer.setPrefWidth(550); // 너비를 설정하여 수평으로 나열되게 함

		return blockContainer;
	}

	private Label createReviewLabel(Review review) {
		Label reviewLabel = new Label(review.toString());
		reviewLabel.setWrapText(true);
		reviewLabel.setStyle(REVIEW_LABEL_STYLE);

		return reviewLabel;
	}

	public FlowPane createMyReviewContainer(List<Review> reviews) {
		FlowPane reviewsContainer = new FlowPane(SPACING,SPACING);
		reviewsContainer.setStyle(CONTAINER_STYLE);

		VBox currentColumn = new VBox(SPACING);
		reviewsContainer.getChildren().add(currentColumn);

		for (int i = 0; i < reviews.size(); i++) {
			Review review = reviews.get(i);
			VBox blockContainer = createBlockContainer(review);
			currentColumn.getChildren().add(blockContainer);

			currentColumn = new VBox(SPACING);
			reviewsContainer.getChildren().add(currentColumn);
		}

		return reviewsContainer;
	}

		public HBox createAllReviewsContainer(List<Review> reviews) {
			HBox reviewsContainer = new HBox(SPACING);
			reviewsContainer.setStyle(CONTAINER_STYLE);

			VBox currentColumn = new VBox(SPACING);
			reviewsContainer.getChildren().add(currentColumn);

			for (int i = 0; i < reviews.size(); i++) {
				Review review = reviews.get(i);
				VBox blockContainer = createBlockContainer(review);
				currentColumn.getChildren().add(blockContainer);

				if ((i + 1) % 2 == 0) {
					currentColumn = new VBox(SPACING);
					reviewsContainer.getChildren().add(currentColumn);
				}
			}

			return reviewsContainer;
		}
}
