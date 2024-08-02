package controller;

import dto.response.CafeMemberInfoDto;
import javafx.scene.layout.FlowPane;
import session.CafeSession;
import dto.response.LoggedCafeDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import repository.CafeRepository;
public class CafeMainPageController implements Initializable {

	@FXML private FlowPane cafeMembersFlowPane;
	@FXML private Text numberOfMembers;
	@FXML private Text cafeName;
	@FXML private Text cafeAddress;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		CafeSession instance = CafeSession.getInstance();
		LoggedCafeDto loggedCafeDto = instance.getLoggedCafeDto();

		CafeRepository cafeRepository = new CafeRepository();
		List<CafeMemberInfoDto> memberInfos = cafeRepository.findCafeMemberInfoById(loggedCafeDto.getCafeId());
		renderCafeMemberCards(memberInfos);

		numberOfMembers.setText(String.valueOf(memberInfos.size()));
		cafeName.setText(loggedCafeDto.getName());
		cafeAddress.setText(loggedCafeDto.getAddress());
	}

	public void renderCafeMemberCards(List<CafeMemberInfoDto> memberInfos) {
		for (CafeMemberInfoDto memberInfo : memberInfos) {
			VBox memberVBox = new VBox(10);
			memberVBox.getStyleClass().add("customer-card");
			memberVBox.setPrefWidth(150);  // 카드의 너비 설정
			memberVBox.setMaxWidth(150);   // 최대 너비 제한
			memberVBox.setPadding(new Insets(15));

			Text nameText = new Text(memberInfo.getMemberName());
			nameText.getStyleClass().add("customer-name");

			ImageView imageView = new ImageView(new Image("/image/noprofile.png"));
			imageView.setFitHeight(50);
			imageView.setFitWidth(50);

			VBox infoVBox = new VBox(10);

			HBox couponHBox = new HBox(5);
			Text couponLabel = new Text("쿠폰 :");
			Text couponText = new Text(String.valueOf(memberInfo.getCouponCount()));
			couponHBox.getChildren().addAll(couponLabel, couponText);

			HBox stampHBox = new HBox(5);
			Text stampLabel = new Text("스탬프 :");
			Text stampText = new Text(String.valueOf(memberInfo.getStampCount()));
			stampHBox.getChildren().addAll(stampLabel, stampText);

			infoVBox.getChildren().addAll(couponHBox, stampHBox);

			HBox avatarInfoHBox = new HBox(15);
			avatarInfoHBox.getChildren().addAll(imageView, infoVBox);

			memberVBox.getChildren().addAll(nameText, avatarInfoHBox);
			cafeMembersFlowPane.getChildren().add(memberVBox);
		}
	}
}
