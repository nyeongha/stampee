package controller;

import java.util.List;

import domain.Review;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;
import view.ReviewView;

public class ReviewController {
	private long cafeId;
	@FXML private ScrollPane scrollPane;
	private final ReviewService reviewService;
	private final ReviewView reviewView = new ReviewView();

	public ReviewController() {
		MemberRepository memberRepository = new MemberRepository();
		CafeRepository cafeRepository = new CafeRepository();
		ReviewRepository reviewRepository = new ReviewRepository(memberRepository, cafeRepository);
		reviewService = new ReviewService(reviewRepository);
	}

	@FXML
	public void initialize() {
		List<Review> reviews = reviewService.findAllReviews();    //리뷰 가져오기
		displayReviews(reviews);
	}

	private void displayReviews(List<Review> reviews) {
		HBox reviewsContainer = reviewView.createReviewsContainer(reviews);
		setScrollPane(reviewsContainer);
	}

	private void setScrollPane(HBox reviewsContainer) {
		// ScrollPane 설정
		scrollPane.setContent(reviewsContainer);
		scrollPane.setFitToHeight(true); // 스크롤을 수직으로만 사용 가능하게 함
		scrollPane.setFitToWidth(true); // 스크롤을 가로와 세로로 사용 가능하게 함
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // 수평 스크롤바 항상 표시
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // 수직 스크롤바 필요 시 표시
	}

	public void setCafeId(long cafeId){
		this.cafeId = cafeId;
		System.out.println("cafeId: " + cafeId);
	}
}
