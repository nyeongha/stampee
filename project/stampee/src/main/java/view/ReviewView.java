package view;

import java.util.List;

import domain.Review;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;

public class ReviewView {

	ReviewRepository reviewRepository;
	MemberRepository memberRepository;
	CafeRepository cafeRepository;

	private final ReviewService reviewService = new ReviewService(reviewRepository);

	public ReviewView(MemberRepository memberRepository, CafeRepository cafeRepository) {
		this.memberRepository = memberRepository;
		this.cafeRepository = cafeRepository;
	}

	public void ReviewViewStart(Stage primaryStage) {
		// 블록 크기
		double blockWidth = 300;
		double blockHeight = 100;
		double spacing = 10;

		// 리뷰 데이터 가져오기
		List<Review> reviews = reviewService.findAllReviews();
		System.out.println(reviews.size());

		// 리뷰 블록을 담을 HBox
		HBox reviewsContainer = new HBox(spacing);
		reviewsContainer.setStyle("-fx-padding: 10px;");

		// 블록 생성 및 데이터 추가
		VBox currentColumn = new VBox(spacing);
		reviewsContainer.getChildren().add(currentColumn);

		for (int i = 0; i < reviews.size(); i++) {

			Review review = reviews.get(i);

			Rectangle block = new Rectangle(blockWidth, blockHeight);
			block.setFill(Color.LIGHTGRAY);
			block.setStroke(Color.BLACK);

			Label reviewLabel = new Label(
				"총리뷰" + reviews.size() + "\n" +
					"Username: " + review.getMember().getUserName() + "\n" +
					"Cafe: " + review.getCafe().getName() + "\n" +
					"Rating: " + review.getRating() + "\n" +
					"Date: " + review.getCreateTime() + "\n" +
					"Content: " + review.getContents()
			);
			reviewLabel.setWrapText(true);
			reviewLabel.setStyle("-fx-padding: 10px;");

			// 블록과 레이블을 포함하는 VBox
			VBox blockContainer = new VBox(5, block, reviewLabel);
			blockContainer.setStyle("-fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");

			currentColumn.getChildren().add(blockContainer);

			// 두 개의 블록이 채워지면 다음 열로 이동
			if ((i + 1) % 2 == 0) {
				currentColumn = new VBox(spacing);
				reviewsContainer.getChildren().add(currentColumn);
			}
		}

		// ScrollPane을 사용하여 수평 스크롤 가능하게 만들기
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(reviewsContainer);
		scrollPane.setFitToHeight(true); // 스크롤을 수직으로만 사용 가능하게 함
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // 수평 스크롤바 항상 표시
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // 수직 스크롤바 필요 시 표시

		// Scene 설정
		Scene scene = new Scene(scrollPane, 800, 600);

		// Stage 설정
		primaryStage.setTitle("Review Block Example");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
