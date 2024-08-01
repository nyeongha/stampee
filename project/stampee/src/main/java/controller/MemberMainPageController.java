package controller;

import dto.response.MemberInfoDto;
import session.MemberSession;
import dto.response.LoggedMemberDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import repository.MemberRepository;
public class MemberMainPageController implements Initializable {

	@FXML
	private HBox MembersHbox;

	@FXML
	private Text numberOfMembers;

	@FXML
	private Text memberName;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		// MemberSession instance = MemberSession.getInstance();
		// LoggedMemberDto loggedMemberDto = instance.getLoggedMemberDto();

		MemberRepository cafeRepository = new MemberRepository();

		List<MemberInfoDto> memberInfos = cafeRepository.findMemberInfoById(1);



		renderMemberCards(memberInfos);

		numberOfMembers.setText(String.valueOf(memberInfos.size()));
		// memberName.setText(loggedMemberDto.getUsername());
	}

	public void renderMemberCards(List<MemberInfoDto> memberInfos){
		for (MemberInfoDto memberInfo : memberInfos){
			VBox memberVBox = new VBox(10);
			memberVBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: white;");
			memberVBox.setPadding(new Insets(15));

			HBox textHeadingHBox = new HBox();
			Label nameLabel = new Label(memberInfo.getCafeName());
			textHeadingHBox.getChildren().add(nameLabel);

			HBox avatarBlockHBox = new HBox(15);
			StackPane avatarStackPane = new StackPane();
			ImageView imageView = new ImageView(new Image("/image/default_profile_picture.jpg"));
			imageView.setFitHeight(56);
			imageView.setFitWidth(51);
			avatarStackPane.getChildren().add(imageView);

			VBox infoVBox = new VBox(10);
			HBox couponHBox = new HBox();
			Label couponLabel = new Label("쿠폰 갯수:");
			Text couponText = new Text(String.valueOf(memberInfo.getCouponCount()));
			couponHBox.getChildren().addAll(couponLabel, couponText);

			HBox stampHBox = new HBox();
			Label stampLabel = new Label("스탬프 갯수:");
			Text stampText = new Text(String.valueOf(memberInfo.getStampCount()));
			stampHBox.getChildren().addAll(stampLabel, stampText);


			infoVBox.getChildren().addAll(couponHBox, stampHBox);
			avatarBlockHBox.getChildren().addAll(avatarStackPane, infoVBox);

			memberVBox.getChildren().addAll(textHeadingHBox, avatarBlockHBox);
			MembersHbox.getChildren().add(memberVBox);
		}
	}

}
