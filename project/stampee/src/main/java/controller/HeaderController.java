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
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();

			Stage stage = (Stage)((Label)event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
