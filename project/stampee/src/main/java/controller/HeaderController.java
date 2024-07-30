package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HeaderController {

	@FXML
	public void handleMyReview(MouseEvent event) {
		navigateTo("/fxml/reviewListView.fxml", event);
	}

	@FXML
	public void handleReviews(MouseEvent event) {
		navigateTo("/fxml/CouponPage.fxml", event);
	}

	@FXML
	public void handleMyCoupon(MouseEvent event) {
		navigateTo("/fxml/CouponPage.fxml", event);
	}

	private void navigateTo(String fxmlPath, MouseEvent event) {
		try {
			// FXMLLoader를 사용하여 FXML 파일을 로드합니다.
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			// 이벤트 소스 (Label)에서 현재 스테이지를 가져옵니다.
			Stage stage = (Stage) ((Label) event.getSource()).getScene().getWindow();

			// 새로운 씬을 생성하고, 현재 스테이지에 설정합니다.
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
