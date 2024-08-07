package view;

import controller.NumberPadController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CouponView {
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

	public HBox createCafeItemHBox() {
		HBox cafeItem = new HBox(20);
		cafeItem.getStyleClass().add("cafe-item");
		cafeItem.setAlignment(Pos.CENTER_LEFT);
		return cafeItem;
	}

	public VBox createInfoBox(String name, String address) {
		VBox infoBox = new VBox(5);
		infoBox.setPrefWidth(160); // 카페 정보 영역의 너비 고정
		Label nameLabel = new Label(name);
		nameLabel.getStyleClass().add("cafe-name");
		Label addressLabel = new Label(address);
		addressLabel.getStyleClass().add("cafe-address");
		addressLabel.setWrapText(true);
		infoBox.getChildren().addAll(nameLabel, addressLabel);
		return infoBox;
	}

	public VBox createCouponBox(int couponCount, int expiredCount) {
		VBox couponBox = new VBox(5);
		couponBox.setAlignment(Pos.CENTER_LEFT);
		couponBox.setPrefWidth(100); // 쿠폰 정보 영역의 너비 고정
		ImageView couponIcon = createImageView("/image/gift-card.png", 40, 40);
		Label couponLabel = new Label("보유 쿠폰: " + couponCount);
		Label expiringLabel = new Label("만료 예정: " + expiredCount);
		expiringLabel.setStyle("-fx-text-fill: #FF69B4;");
		couponBox.getChildren().addAll(couponIcon, couponLabel, expiringLabel);
		return couponBox;
	}

	public HBox createStampAndButtonBox(int stampCount, Button shareButton) {
		HBox stampAndButtonBox = new HBox(10);
		stampAndButtonBox.setAlignment(Pos.CENTER_LEFT);
		HBox.setHgrow(stampAndButtonBox, Priority.ALWAYS); // 남은 공간을 모두 사용

		GridPane stampGrid = createStampGrid(stampCount);
		HBox.setHgrow(stampGrid, Priority.ALWAYS); // 스탬프 그리드가 가능한 많은 공간을 사용하도록 설정

		stampAndButtonBox.getChildren().addAll(stampGrid, shareButton);
		return stampAndButtonBox;
	}

	public Button createShareButton() {
		Button shareButton = new Button("공유");
		shareButton.getStyleClass().add("share-button");
		shareButton.setPrefWidth(60);
		return shareButton;
	}

	public GridPane createStampGrid(int stampCount) {
		GridPane grid = new GridPane();
		grid.getStyleClass().add("stamp-grid");
		grid.setHgap(5);
		grid.setVgap(5);

		for (int i = 0; i < 10; i++) {
			ImageView stamp = createImageView(
				i < stampCount ? "/image/github_logo.png" : "/image/github_logo_rec.png", 25, 25
			);
			grid.add(stamp, i % 5, i / 5); // 5x2 그리드로 배치
		}
		return grid;
	}

	private ImageView createImageView(String resourcePath, double width, double height) {
		ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(resourcePath)));
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		return imageView;
	}
}
