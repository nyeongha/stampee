package controller;

import dto.response.MemberInfoDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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

import java.io.IOException;
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

			// 클릭 이벤트 추가
			memberVBox.setOnMouseClicked(event -> navigateToStampPage(memberInfo.getCafeId()));

			// 호버 효과 추가
			memberVBox.setOnMouseEntered(e -> memberVBox.setStyle("-fx-cursor: hand; -fx-background-color: #f0f0f0;"));
			memberVBox.setOnMouseExited(e -> memberVBox.setStyle(""));

			MembersFlowPane.getChildren().add(memberVBox);

		}
		totalStamps.setText(String.valueOf(stamp_sum));
		totalCoupons.setText(String.valueOf(coupon_sum));
	}

	private void navigateToStampPage(long cafeId) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
			Parent root = loader.load();

			// StampController 초기화
			StampController controller = loader.getController();
			long memberId = MemberSession.getInstance().getLoggedMemberDto().getMemberId();
			controller.initData(memberId, cafeId);

			Scene scene = new Scene(root);
			Stage stage = (Stage) MembersFlowPane.getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Stamp Management");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			// 에러 처리 (예: 알림 대화상자 표시)
			showAlert("오류", "스탬프 페이지로 이동 중 오류가 발생했습니다.");
		}
	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
