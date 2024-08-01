package controller;

import static domain.ReviewType.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dto.response.MyStampDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import repository.CafeRepository;
import repository.CouponRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import repository.StampRepository;
import service.CafeService;
import service.CouponService;
import service.MailService;
import service.MapService;
import service.ReviewService;
import service.StampService;

public class StampController implements Initializable {
	private final CouponService couponService;
	private final StampService stampService;
	private final CafeService cafeService;
	private MapService mapService;

	private final ReviewService reviewService;

	@FXML private ImageView stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9, stamp10;
	@FXML private Label cafeName, cafeAddress, couponCount, cafeRating;;
	@FXML private Label signature1, signature2;
	@FXML private Pane mapContainer;
	@FXML private ScrollPane reviewContainer;
	@FXML private AnchorPane createReviewContainer;

	public StampController() {
		MailService mailService = new MailService();
		MemberRepository memberRepository = new MemberRepository();
		StampRepository stampRepository = new StampRepository();
		CouponRepository couponRepository = new CouponRepository();
		CafeRepository cafeRepository = new CafeRepository();
		ReviewRepository reviewRepository = new ReviewRepository();

		stampService = new StampService(stampRepository, memberRepository, mailService);
		couponService = new CouponService(couponRepository, mailService);
		cafeService = new CafeService(cafeRepository);
		reviewService = new ReviewService(reviewRepository);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("/fxml/MapOutput.fxml"));
			Pane mapPane = mapLoader.load();
			mapContainer.getChildren().add(mapPane);
			mapService = mapLoader.getController();

			AnchorPane CreateReview=FXMLLoader.load(getClass().getResource("/fxml/CreateReview.fxml"));
			createReviewContainer.getChildren().add(CreateReview);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reviewListView.fxml"));
			Pane reviewPane = loader.load();
			reviewContainer.setContent(reviewPane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initData(long memberId, long cafeId) {
		MyStampDto myStamp = stampService.findMyStamp(memberId, cafeId);
		float ratingAvg = reviewService.getCafeRatingAvg(cafeId);

		int count = couponService.getMyCount(memberId, cafeId);
		setStampImage(myStamp.getCount());

		cafeName.setText(myStamp.getCafeName());
		cafeAddress.setText(myStamp.getCafeAddr());
		couponCount.setText(myStamp.getCafeName() + " 쿠폰 " + count + "장");
		cafeRating.setText(ratingAvg + "");

		setSignatureMenu(cafeId);
		setReviewContainerCafeId(cafeId);

		mapService.initializeMap(myStamp.getCafeName(), myStamp.getCafeAddr());
		setCreateReviewContainer(memberId,cafeId);
	}

	private void setSignatureMenu(long cafeId) {
		List<String> menus = cafeService.getSignatureMenu(cafeId);
		Label[] signatureMenus = {signature1, signature2};

		for (int i = 0; i < menus.size(); i++) {
			signatureMenus[i].setText(menus.get(i));
		}
	}

	private void setStampImage(int count) {
		Image filledStamp = new Image(getClass().getResourceAsStream("/image/github_logo.png"));
		Image emptyStamp = new Image(getClass().getResourceAsStream("/image/github_logo_rec.png"));

		ImageView[] stamps = {stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9, stamp10};

		for (int i = 0; i < stamps.length; i++) {
			if (i < count) {
				stamps[i].setImage(filledStamp);
				stamps[i].setFitWidth(70);
				stamps[i].setFitHeight(70);
			} else {
				stamps[i].setImage(emptyStamp);
				stamps[i].setFitWidth(50);
				stamps[i].setFitHeight(50);
			}

		}
	}

	private void setReviewContainerCafeId(long cafeId) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reviewListView.fxml"));
			Pane reviewPane = loader.load();

			ReviewController controller = loader.getController();
			controller.init(cafeId, CAFE);

			reviewContainer.setContent(reviewPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void setCreateReviewContainer(long memberId,long cafeId){

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateReview.fxml"));
		try {
			AnchorPane createReview = loader.load(); // 루트 노드가 VBox인 경우

			// CreateReviewController의 인스턴스 가져오기
			CreateReviewController controller = loader.getController();
			controller.initData(memberId, cafeId);

			// VBox에 자식 노드를 추가
			createReviewContainer.getChildren().setAll(createReview);
		} catch (IOException e) {
			throw new RuntimeException("Error loading FXML file for CreateReview: " + e.getMessage(), e);
		}
	}
}
