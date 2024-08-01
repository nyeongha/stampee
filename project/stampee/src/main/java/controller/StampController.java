package controller;

import static domain.ReviewType.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.ReviewType;
import dto.response.MyStampDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import repository.CafeRepository;
import repository.CouponRepository;
import repository.MemberRepository;
import repository.StampRepository;
import service.CafeService;
import service.CouponService;
import service.MailService;
import service.MapService;
import service.StampService;

public class StampController implements Initializable {
	private final CouponService couponService;
	private final StampService stampService;
	private final CafeService cafeService;
	private MapService mapService;

	@FXML private WebView webView;
	@FXML private ImageView stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9, stamp10;
	@FXML private Label cafeName, cafeAddress, couponCount;
	@FXML private Label signature1, signature2;
	@FXML private Pane mapContainer;
	@FXML private ScrollPane reviewContainer;

	public StampController() {
		MailService mailService = new MailService();
		MemberRepository memberRepository = new MemberRepository();
		StampRepository stampRepository = new StampRepository();
		CouponRepository couponRepository = new CouponRepository();
		CafeRepository cafeRepository = new CafeRepository();

		stampService = new StampService(stampRepository, memberRepository, mailService);
		couponService = new CouponService(couponRepository, mailService);
		cafeService = new CafeService(cafeRepository);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			// Pane mapPane = FXMLLoader.load(getClass().getResource("/fxml/MapOutput.fxml"));
			// mapContainer.getChildren().add(mapPane);
			FXMLLoader mapLoader = new FXMLLoader(getClass().getResource("/fxml/MapOutput.fxml"));
			Pane mapPane = mapLoader.load();
			mapContainer.getChildren().add(mapPane);
			mapService = mapLoader.getController();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reviewListView.fxml"));
			Pane reviewPane = loader.load();
			reviewContainer.setContent(reviewPane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initData(long memberId, long cafeId) {
		MyStampDto myStamp = stampService.findMyStamp(memberId, cafeId);
		int count = couponService.getMyCount(memberId, cafeId);
		setStampImage(myStamp.getCount());

		cafeName.setText(myStamp.getCafeName());
		cafeAddress.setText(myStamp.getCafeAddr());
		couponCount.setText(myStamp.getCafeName() + " 쿠폰 " + count + "장");

		setSignatureMenu(cafeId);
		setReviewContainerCafeId(cafeId);

		mapService.initializeMap(myStamp.getCafeName(), myStamp.getCafeAddr());
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
			} else {
				stamps[i].setImage(emptyStamp);
			}
			stamps[i].setFitWidth(85);
			stamps[i].setFitHeight(85);
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
}
