package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;

import domain.Member;
import dto.response.MyCouponDto;
import formatter.PhoneNumberFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import view.PopupView;

public class CouponController implements Initializable {
	private final CouponService couponService;
	private final StampService stampService;
	private final UserService userService;
	private final CouponView couponView = new CouponView();
	private final PopupView popupView = new PopupView();

	@FXML private VBox cafeListContainer;

	public CouponController() {
		CouponRepository couponRepository = new CouponRepository();
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		MailService mailService = new MailService();

		couponService = new CouponService(couponRepository, mailService);
		stampService = new StampService(stampRepository, memberRepository, mailService);
		userService = new UserService(memberRepository);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// LoggedMemberDto loggedMemberDto = LoginSession.getInstance().getLoggedMemberDto();
		// long memberId = loggedMemberDto.getMemberId();
		long memberId = 39l;
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
		try {
			String toPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(showNumberPadPopup());
			stampService.shareStamp(fromMember, cafeId, toPhoneNumber, 1);
			popupView.showSuccessPopup("스탬프 공유 성공");
		} catch (MessagingException | SQLException |  IllegalArgumentException e) {
			popupView.showFailPopup(e.getMessage());
		}
	}

	public String showNumberPadPopup() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/numberPad.fxml"));
			Parent root = loader.load();
			NumberPadController controller = loader.getController();

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("전화번호 입력");
			stage.setScene(new Scene(root));
			stage.showAndWait();

			return controller.getInputField().getText();
		} catch (Exception e) {
			return null;
		}
	}

	private void addCafeItem(long cafeId, long memberId, String name, String address, int couponCount, int stampCount, int expiredCount) {
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
