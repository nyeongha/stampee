package controller;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;

import domain.Member;
import dto.response.MyCouponDto;
import formatter.PhoneNumberFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repository.CouponRepository;
import repository.MemberRepository;
import repository.StampRepository;
import service.CouponService;
import service.MailService;
import service.StampService;
import service.UserService;
import view.CouponView;

public class CouponController {
	private static CouponService couponService;
	private static StampService stampService;
	private static UserService userService;
	private final CouponView couponView = new CouponView();

	@FXML
	private VBox cafeListContainer;

	public CouponController() {
		CouponRepository couponRepository = new CouponRepository();
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		MailService mailService = new MailService();
		couponService = new CouponService(couponRepository, mailService);
		stampService = new StampService(stampRepository, memberRepository, mailService);
		userService = new UserService(memberRepository);
	}

	public void initData(long memberId) {
		List<MyCouponDto> myCoupons = couponService.getMyCoupon(memberId);
		for (MyCouponDto myCoupon : myCoupons) {
			addCafeItem(myCoupon.getCafeId(), memberId,
				myCoupon.getCafeName(), myCoupon.getAddress(), myCoupon.getCount(),
				stampService.findMyStamp(memberId, myCoupon.getCafeId()).getCount(),
				couponService.getExpiringCouponCount(memberId, myCoupon.getCafeId()));
		}
	}

	@FXML
	public void handleShareButtonPress(long memberId, long cafeId) {
		Member fromMember = userService.findMemberById(memberId);
		String toPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(showNumberPadPopup());
		try {
			stampService.shareStamp(fromMember, cafeId, toPhoneNumber, 1);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String showNumberPadPopup() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/numberPad.fxml"));
			Parent root = loader.load();
			NumberPadController controller = loader.getController();

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("숫자 입력");
			stage.setScene(new Scene(root));
			stage.showAndWait(); // 팝업 창이 닫힐 때까지 대기

			return controller.getInputField().getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void addCafeItem(long cafeId, long memberId, String name, String address, int couponCount, int stampCount,
		int expiredCount) {
		HBox cafeItem = couponView.createCafeItemHBox();
		VBox infoBox = couponView.createInfoBox(name, address);
		VBox couponBox = couponView.createCouponBox(couponCount, expiredCount);
		Button shareButton = couponView.createShareButton();
		shareButton.setOnAction(event -> handleShareButtonPress(memberId, cafeId));    //공유 버튼 클릭 시 동작
		HBox stampAndButtonBox = couponView.createStampAndButtonBox(stampCount, shareButton);

		cafeItem.getChildren().addAll(infoBox, couponBox, stampAndButtonBox);
		cafeListContainer.getChildren().add(cafeItem);
	}
}
