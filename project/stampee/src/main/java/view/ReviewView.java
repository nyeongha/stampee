package view;

import java.util.List;
import domain.Review;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ReviewView {
	private static final double BLOCK_WIDTH = 300;
	private static final double BLOCK_HEIGHT = 100;
	private static final double SPACING = 10;
	private static final String CONTAINER_STYLE = "-fx-padding: 10px;";
	private static final String BLOCK_CONTAINER_STYLE = "-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;";
	private static final String REVIEW_LABEL_STYLE = "-fx-padding: 10px;";

	public HBox createReviewsContainer(List<Review> reviews) {
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

	private VBox createBlockContainer(Review review) {
		Rectangle block = createBlock();
		Label reviewLabel = createReviewLabel(review);

		VBox blockContainer = new VBox(5, block, reviewLabel);
		blockContainer.setStyle(BLOCK_CONTAINER_STYLE);

		return blockContainer;
	}

	private Rectangle createBlock() {
		Rectangle block = new Rectangle(BLOCK_WIDTH, BLOCK_HEIGHT);
		block.setFill(Color.LIGHTGRAY);
		block.setStroke(Color.BLACK);

		return block;
	}

	private Label createReviewLabel(Review review) {
		Label reviewLabel = new Label(review.toString());
		reviewLabel.setWrapText(true);
		reviewLabel.setStyle(REVIEW_LABEL_STYLE);

		return reviewLabel;
	}
}
