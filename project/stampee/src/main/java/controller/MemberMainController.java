package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dto.response.MyCouponDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import repository.CouponRepository;
import repository.MemberRepository;
import repository.StampRepository;
import service.CouponService;
import service.MailService;
import service.StampService;

public class MemberMainController implements Initializable {
	private final CouponService couponService;
	private final StampService stampService;

	@FXML private Text userNameText;
	@FXML private Text totalCouponCount;
	@FXML private VBox cafeListContainer;

	public MemberMainController() {
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		CouponRepository couponRepository = new CouponRepository();
		MailService mailService = new MailService();

		this.couponService = new CouponService(couponRepository, mailService);
		this.stampService = new StampService(stampRepository, memberRepository, mailService);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		loadCafeList();
	}

	private void loadCafeList() {
		long memberId = 1L; // 테스트용 임시 ID
		List<MyCouponDto> myCoupons = couponService.getMyCoupon(memberId);
		for (MyCouponDto myCoupon : myCoupons) {
			addCafeItem(myCoupon.getCafeName(),
				myCoupon.getCount(),
				stampService.findMyStamp(memberId, myCoupon.getCafeId()).getCount());
		}
	}

	private void addCafeItem(String name, int couponCount, int stampCount) {
		HBox cafeItem = new HBox();
		cafeItem.getStyleClass().add("cafe-item");

		VBox infoBox = new VBox();
		Text cafeName = new Text(name);
		cafeName.getStyleClass().add("cafe-name");
		Text couponInfo = new Text("쿠폰 갯수: " + couponCount);
		Text stampInfo = new Text("스탬프 갯수: " + stampCount);
		couponInfo.getStyleClass().add("cafe-info");
		stampInfo.getStyleClass().add("cafe-info");
		infoBox.getChildren().addAll(cafeName, couponInfo, stampInfo);

		cafeItem.getChildren().add(infoBox);
		cafeListContainer.getChildren().add(cafeItem);
	}
}