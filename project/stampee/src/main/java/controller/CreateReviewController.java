package controller;

import java.sql.Date;
import java.time.LocalDateTime;

import domain.Cafe;
import domain.Member;
import domain.Review;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;
import session.MemberSession;

public class CreateReviewController {
	@FXML private ComboBox<Float> rating;

	@FXML private TextArea reviewContents;

	@FXML private Button submitReviewButton;

	@FXML
	private AnchorPane reviewPane;
	private final ReviewService reviewService;
	private final MemberRepository memberRepository;
	private final CafeRepository cafeRepository;
	private final ReviewRepository reviewRepository;

	public CreateReviewController(){
		memberRepository = new MemberRepository();
		cafeRepository = new CafeRepository();
		reviewRepository = new ReviewRepository();
		reviewService = new ReviewService(reviewRepository);
	}

	// 멤버 세션 객체
	private Member loggedInMember=null;
	private Cafe selectedCafe=null; // 리뷰할 카페 객체

	@FXML
	public void initialize() {
		// 실수 값을 ComboBox에 추가
		rating.getItems().addAll(5.0f, 4.5f, 4.0f, 3.5f, 3.0f, 2.5f, 2.0f, 1.5f, 1.0f, 0.5f, 0.0f);

		// 버튼 클릭 이벤트 핸들러 설정
		submitReviewButton.setOnAction(event -> handleSubmitButtonAction());
	}

	public void initData(long memberId, long cafeId) {
		// 멤버와 카페 객체 초기화
		this.loggedInMember = memberRepository.findUserById(memberId); // 예시 멤버
		this.selectedCafe = cafeRepository.findCafeById(cafeId); // 예시 카페
	}

	private void handleSubmitButtonAction() {
		Float selectedRating = rating.getValue();
		String contents = reviewContents.getText();


		// 유효성 검사
		if (selectedRating == null) {
			showAlert("Error", "Please select a rating.");
			return;
		}

		if (contents.trim().isEmpty()) {
			showAlert("Error", "Review contents cannot be empty.");
			return;
		}

		if (loggedInMember == null || selectedCafe == null) {
			showAlert("Error", "Invalid member or cafe information.");
			return;
		}

		// 현재 시간 가져오기
		LocalDateTime now = LocalDateTime.now();
		Date createTime = Date.valueOf(now.toLocalDate());

		// 리뷰 객체 생성
		Review review = new Review(selectedRating, contents, createTime, loggedInMember, selectedCafe);

		// 리뷰 삽입
		reviewService.insertReview(selectedRating, contents, createTime, loggedInMember, selectedCafe);

		reviewContents.clear();
		rating.getSelectionModel().clearSelection();

	}

	private void showAlert(String title, String message) {
		// 오류 메시지를 표시하는 간단한 알림창
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	@FXML
	private void handleToggleReview() {
		boolean isVisible = reviewPane.isVisible();
		reviewPane.setVisible(!isVisible);
	}
}

