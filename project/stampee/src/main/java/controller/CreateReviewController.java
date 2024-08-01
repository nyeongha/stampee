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
import lombok.RequiredArgsConstructor;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;
import session.MemberSession;
import validation.ReviewValidationResult;

public class CreateReviewController {
	@FXML
	private ComboBox<Float> rating;

	@FXML
	private TextArea reviewContents;

	@FXML
	private Button submitReviewButton;

	@FXML
	private AnchorPane reviewPane;
	private ReviewService reviewService;
	private MemberRepository memberRepository;
	private CafeRepository cafeRepository;
	private ReviewRepository reviewRepository;

	// 멤버 세션 객체
	private Member loggedInMember = null;
	private Cafe selectedCafe = null; // 리뷰할 카페 객체

	public CreateReviewController() {
		memberRepository = new MemberRepository();
		cafeRepository = new CafeRepository();
		reviewRepository = new ReviewRepository();
		reviewService = new ReviewService(reviewRepository);
	}

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
		ReviewValidationResult validationResult = validateReview(selectedRating, contents);
		if (validationResult != ReviewValidationResult.VALID) {
			showAlert("Error", validationResult.getMessage());
			return;
		}

		// 현재 시간 가져오기
		LocalDateTime now = LocalDateTime.now();
		Date createTime = Date.valueOf(now.toLocalDate());

		// 리뷰 삽입
		reviewService.insertReview(selectedRating, contents, createTime, loggedInMember, selectedCafe);

		// UI 업데이트
		reviewContents.clear();
		rating.getSelectionModel().clearSelection();

	}

	private ReviewValidationResult validateReview(Float selectedRating, String contents) {
		if (selectedRating == null) {
			return ReviewValidationResult.NO_RATING;
		}

		if (contents.trim().isEmpty()) {
			return ReviewValidationResult.EMPTY_CONTENTS;
		}

		if (loggedInMember == null || selectedCafe == null) {
			return ReviewValidationResult.INVALID_MEMBER_OR_CAFE;
		}

		return ReviewValidationResult.VALID;
	}

	private void showAlert(String title, String message) {
		// 오류 메시지를 표시하는 간단한 알림창
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
