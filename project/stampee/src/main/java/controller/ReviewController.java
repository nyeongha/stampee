// package controller;// package controller;
// import static domain.ReviewType.*;
//
// import java.util.List;
// import java.util.Optional;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// import domain.Review;
// import domain.ReviewType;
// import dto.response.LoggedMemberDto;
// import javafx.application.Platform;
// import javafx.fxml.FXML;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.ButtonType;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.ToggleButton;
// import javafx.scene.layout.FlowPane;
// import javafx.scene.layout.VBox;
// import repository.MemberRepository;
// import repository.ReviewRepository;
// import service.ReviewService;
// import session.MemberSession;
//
// public class ReviewController {
// 	private static final String REVIEW_LABEL_STYLE = "-fx-padding: 10px;";
// 	private static final double SPACING = 10;
// 	private static final String BLOCK_CONTAINER_STYLE = "-fx-padding: 10px; -fx-border-color: #FFB6C1; -fx-border-width: 1px;";
// 	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
// 	@FXML private ScrollPane scrollPane;
// 	@FXML private FlowPane reviewFlowPane;
//
//
// 	private final ReviewService reviewService;
//
// 	@FXML
// 	public void initialize() {
// 		if (reviewFlowPane == null) {
// 			throw new IllegalStateException("reviewFlowPane is not injected by FXML loader");
// 		}
// 		if (scrollPane == null) {
// 			throw new IllegalStateException("scrollPane is not injected by FXML loader");
// 		}
// 	}
//
// 	@FXML
// 	private ToggleButton toggleButton;
//
// 	public ReviewController() {
// 		ReviewRepository reviewRepository = new ReviewRepository();
// 		reviewService = new ReviewService(reviewRepository);
// 	}
//
//
// 	public void init(long id, ReviewType type) {
// 		List<Review> reviews;
// 		if (type == CAFE) {
// 			reviews = reviewService.findReviewsByCafeId(id);
// 			displayReviews(reviews);
// 		} else if (type == MEMBER) {
// 			reviews = reviewService.findReviewsByMemberId(id);
// 			displayReviews(reviews);
// 		} else {
// 			reviews = reviewService.findAllReviews();
// 			displayReviews(reviews);
// 		}
// 	}
//
// 	private void displayReviews(List<Review> reviews) {
// 		FlowPane reviewsContainer = createReviewContainer(reviews);
// 		setScrollPane(reviewsContainer);
// 	}
//
// 	private void setScrollPane(FlowPane reviewsContainer) {
// 		reviewFlowPane.getChildren().clear();
// 		reviewFlowPane.getChildren().addAll(reviewsContainer.getChildren());
// 		scrollPane.setContent(reviewFlowPane);
// 		scrollPane.setFitToWidth(true);
// 		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
// 		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
// 	}
//
// 	public void handleDeleteReview(Review review) {
// 		MemberSession memberSession = MemberSession.getInstance(MemberSession.getInstance().getLoggedMemberDto());
// 		if (memberSession == null) {
// 			showAlert("로그인 필요", "리뷰를 삭제하려면 로그인이 필요합니다.");
// 			return;
// 		}
//
// 		boolean confirm = showConfirmDialog("리뷰 삭제", "정말로 이 리뷰를 삭제하시겠습니까?");
// 		if (!confirm) {
// 			return;
// 		}
//
// 		try {
// 			boolean deleted = reviewService.deleteReview(review.getReviewId(), memberSession.getLoggedMemberDto()
// 				.getMemberId());
// 			if (deleted) {
// 				refreshReviews();
// 				showAlert("삭제 성공", "리뷰가 성공적으로 삭제되었습니다.");
// 				log.info("Review deleted: reviewId={}, memberId={}", review.getReviewId(), memberSession.getLoggedMemberDto().getMemberId());
// 			} else {
// 				showAlert("삭제 실패", "리뷰를 삭제할 수 없습니다. 자신의 리뷰인지 확인해주세요.");
// 				log.warn("Review deletion failed: reviewId={}, memberId={}", review.getReviewId(), memberSession.getLoggedMemberDto().getMemberId());
// 			}
// 		} catch (Exception e) {
// 			showAlert("오류 발생", "리뷰 삭제 중 오류가 발생했습니다. 나중에 다시 시도해주세요.");
// 			log.error("Error occurred while deleting review: reviewId={}, memberId={}", review.getReviewId(), memberSession.getLoggedMemberDto().getMemberId(), e);
// 		}
// 	}
//
// 	private void showAlert(String title,String content) {
// 		Alert alert = new Alert(Alert.AlertType.INFORMATION);
// 		alert.setTitle(title);
// 		alert.setHeaderText(null);
// 		alert.setContentText(content);
// 		alert.showAndWait();
// 	}
//
// 	private void refreshReviews() {
// 	}
//
// 	private boolean showConfirmDialog(String title, String content) {
// 		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
// 		alert.setTitle(title);
// 		alert.setHeaderText(null);
// 		alert.setContentText(content);
// 		Optional<ButtonType> result = alert.showAndWait();
// 		return result.isPresent() && result.get() == ButtonType.OK;
// 	}
//
// 	// private void refreshReviews() {
// 	// 	Platform.runLater(() -> {
// 	// 		// 현재 표시되고 있는 리뷰를 다시 불러오고 표시합니다.
// 	// 		init(LoggedMemberDto, currentType);
// 	// 	});
// 	// }
//
// 	public FlowPane createReviewContainer(List<Review> reviews) {
// 		FlowPane reviewsContainer = new FlowPane(SPACING, SPACING);
// 		reviewsContainer.setStyle("-fx-padding: 10px;");
// 		reviewsContainer.setOrientation(javafx.geometry.Orientation.HORIZONTAL); // 수평 방향으로 설정
//
// 		for (Review review : reviews) {
// 			VBox blockContainer = createBlockContainer(review);
// 			reviewsContainer.getChildren().add(blockContainer);
// 		}
//
// 		return reviewsContainer;
// 	}
//
// 	private VBox createBlockContainer(Review review) {
// 		Label reviewLabel = createReviewLabel(review);
// 		Button deleteButton=new Button("삭제");
// 		deleteButton.setOnAction(event -> handleDeleteReview(review));
//
//
// 		deleteButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white;");
// 		VBox blockContainer=null;
// 		if (MemberSession.getInstance().getLoggedMemberDto().getMemberId()==review.getReviewId()){
// 			blockContainer = new VBox(5, reviewLabel,deleteButton);
// 			blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
// 			blockContainer.setPrefWidth(545); // 너비를 설정하여 수평으로 나열되게 함
// 		}
// 		else{
// 			blockContainer = new VBox(5, reviewLabel);
// 			blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
// 			blockContainer.setPrefWidth(545); // 너비를 설정하여 수평으로 나열되게 함
// 		}
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
//
//
//
package controller;// package controller;
import static domain.ReviewType.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Review;
import domain.ReviewType;
import dto.response.LoggedMemberDto;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import repository.ReviewRepository;
import service.ReviewService;
import session.MemberSession;

public class ReviewController {
	private static final String REVIEW_LABEL_STYLE = "-fx-padding: 10px;";
	private static final double SPACING = 10;
	private static final String BLOCK_CONTAINER_STYLE = "-fx-padding: 10px; -fx-border-color: #FFB6C1; -fx-border-width: 1px;";
	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
	@FXML private ScrollPane scrollPane;
	@FXML private FlowPane reviewFlowPane;

	private final ReviewService reviewService;
	private long currentId;
	private ReviewType currentType;

	@FXML
	public void initialize() {
		if (reviewFlowPane == null) {
			throw new IllegalStateException("reviewFlowPane is not injected by FXML loader");
		}
		if (scrollPane == null) {
			throw new IllegalStateException("scrollPane is not injected by FXML loader");
		}
	}

	@FXML
	private ToggleButton toggleButton;

	public ReviewController() {
		ReviewRepository reviewRepository = new ReviewRepository();
		reviewService = new ReviewService(reviewRepository);
	}

	public void init(long id, ReviewType type) {
		this.currentId = id;
		this.currentType = type;

		List<Review> reviews;
		if (type == CAFE) {
			reviews = reviewService.findReviewsByCafeId(id);
		} else if (type == MEMBER) {
			reviews = reviewService.findReviewsByMemberId(id);
		} else {
			reviews = reviewService.findAllReviews();
		}
		displayReviews(reviews);
	}

	private void displayReviews(List<Review> reviews) {
		FlowPane reviewsContainer = createReviewContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void setScrollPane(FlowPane reviewsContainer) {
		reviewFlowPane.getChildren().clear();
		reviewFlowPane.getChildren().addAll(reviewsContainer.getChildren());
		scrollPane.setContent(reviewFlowPane);
		scrollPane.setFitToWidth(true);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	}

	public void handleDeleteReview(Review review) {


		boolean confirm = showConfirmDialog("리뷰 삭제", "정말로 이 리뷰를 삭제하시겠습니까?");
		if (!confirm) {
			return;
		}

		try {
			boolean deleted = reviewService.deleteReview(review.getReviewId(),MemberSession.getInstance().getLoggedMemberDto().getMemberId() );
			if (deleted) {
				refreshReviews();
				showAlert("삭제 성공", "리뷰가 성공적으로 삭제되었습니다.");
				log.info("Review deleted: reviewId={}, memberId={}", review.getReviewId(),MemberSession.getInstance().getLoggedMemberDto().getMemberId());
			} else {
				showAlert("삭제 실패", "리뷰를 삭제할 수 없습니다. 자신의 리뷰인지 확인해주세요.");
				log.warn("Review deletion failed: reviewId={}, memberId={}", review.getReviewId(), review.getReviewId(),MemberSession.getInstance().getLoggedMemberDto().getMemberId());
			}
		} catch (Exception e) {
			showAlert("오류 발생", "리뷰 삭제 중 오류가 발생했습니다. 나중에 다시 시도해주세요.");
			log.error("Error occurred while deleting review: reviewId={}, memberId={}", review.getReviewId(),review.getReviewId(),MemberSession.getInstance().getLoggedMemberDto().getMemberId(), e);
		}
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);

		// CSS 스타일 적용
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
		dialogPane.getStyleClass().add("custom-alert");
		// 기본 아이콘 제거
		dialogPane.setGraphic(null);

		alert.showAndWait();
	}

	private void refreshReviews() {
		Platform.runLater(() -> {
			init(currentId, currentType);
		});
	}

	private boolean showConfirmDialog(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);


		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);

		// CSS 스타일 적용
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("/css/alert.css").toExternalForm());
		dialogPane.getStyleClass().add("custom-alert");

		// 기본 아이콘 제거
		dialogPane.setGraphic(null);

		Optional<ButtonType> result = alert.showAndWait();
		return result.isPresent() && result.get() == ButtonType.OK;
	}

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
		Button deleteButton = new Button("삭제");
		deleteButton.setOnAction(event -> handleDeleteReview(review));

		deleteButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white;");
		VBox blockContainer;
		if (MemberSession.getInstance().getLoggedMemberDto().getMemberId() == review.getAuthor().getMemberId()) {
			blockContainer = new VBox(5, reviewLabel, deleteButton);
			blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
			blockContainer.setPrefWidth(545); // 너비를 설정하여 수평으로 나열되게 함
		} else {
			blockContainer = new VBox(5, reviewLabel);
			blockContainer.setStyle(BLOCK_CONTAINER_STYLE);
			blockContainer.setPrefWidth(545); // 너비를 설정하여 수평으로 나열되게 함
		}

		return blockContainer;
	}

	private Label createReviewLabel(Review review) {
		Label reviewLabel = new Label(review.toString());
		reviewLabel.setWrapText(true);
		reviewLabel.setStyle(REVIEW_LABEL_STYLE);

		return reviewLabel;
	}
}
