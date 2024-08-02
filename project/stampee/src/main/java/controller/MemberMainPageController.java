package controller;

import dto.response.MemberInfoDto;
import javafx.scene.layout.FlowPane;
import session.MemberSession;
import dto.response.LoggedMemberDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import repository.MemberRepository;
public class MemberMainPageController implements Initializable {

	@FXML
	private FlowPane MembersFlowPane;

	@FXML
	private Text memberName;

	@FXML
	private Text totalStamps;

	@FXML
	private Text totalCoupons;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		MemberSession instance = MemberSession.getInstance();
		LoggedMemberDto loggedMemberDto = instance.getLoggedMemberDto();


		MemberRepository memberRepository = new MemberRepository();
		List<MemberInfoDto> memberInfos = memberRepository.findMemberInfoById(loggedMemberDto.getMemberId());

		renderMemberCards(memberInfos);

		memberName.setText(loggedMemberDto.getUsername());

		// // 출력영역
		// System.out.println(loggedMemberDto.getUsername());
		// System.out.println(loggedMemberDto.getEmail());
		// System.out.println(loggedMemberDto.getMemberId());

		for (MemberInfoDto memberInfo : memberInfos) {
			System.out.println(memberInfo.getCafeId());
			System.out.println(memberInfo.getCafeName());
			System.out.println(memberInfo.getCouponCount());
			System.out.println(memberInfo.getStampCount());
		}
	}

	public void renderMemberCards(List<MemberInfoDto> memberInfos){

		Long stamp_sum = 0L;
		Long coupon_sum = 0L;
		for (MemberInfoDto memberInfo : memberInfos){
			stamp_sum += memberInfo.getStampCount();
			coupon_sum += memberInfo.getCouponCount();
			VBox memberVBox = new VBox(10);
			memberVBox.getStyleClass().add("customer-card");
			memberVBox.setPrefWidth(150);  // 카드의 너비 설정
			memberVBox.setMaxWidth(150);   // 최대 너비 제한
			memberVBox.setPadding(new Insets(15));

			Text nameText = new Text(memberInfo.getCafeName());
			nameText.getStyleClass().add("customer-name");

			ImageView imageView = new ImageView(new Image("/image/noprofile.png"));
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);

			VBox infoVBox = new VBox(10);

			HBox couponHBox = new HBox(5);
			Label couponLabel = new Label("쿠폰 :");
			Text couponText = new Text(String.valueOf(memberInfo.getCouponCount()));
			couponHBox.getChildren().addAll(couponLabel, couponText);

			HBox stampHBox = new HBox(5);
			Label stampLabel = new Label("스탬프 :");
			Text stampText = new Text(String.valueOf(memberInfo.getStampCount()));
			stampHBox.getChildren().addAll(stampLabel, stampText);

			infoVBox.getChildren().addAll(couponHBox, stampHBox);

			HBox avatarInfoHBox = new HBox(15);
			avatarInfoHBox.getChildren().addAll(imageView, infoVBox);

			memberVBox.getChildren().addAll(nameText, avatarInfoHBox);
			MembersFlowPane.getChildren().add(memberVBox);
		}
		totalStamps.setText(String.valueOf(stamp_sum));
		totalCoupons.setText(String.valueOf(coupon_sum));
	}
}
