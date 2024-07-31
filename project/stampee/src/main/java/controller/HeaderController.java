package controller;

import static domain.ReviewType.*;

import java.io.IOException;

import domain.ReviewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;

public class HeaderController {

	@FXML
	public void handleMyReview(MouseEvent event) {
		// LoggedMemberDto member = LoginSession.getInstance().getLoggedMemberDto();
		navigateTo("/fxml/reviews.fxml", event, MEMBER, 38L);
	}

	@FXML
	public void handleReviews(MouseEvent event) {
		navigateTo("/fxml/reviews.fxml", event, ALL, 0L);
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

	private void navigateTo(String fxmlPath, MouseEvent event, ReviewType reviewType, long id) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			ReviewController controller = loader.getController();
			controller.init(id, reviewType);

			Stage stage = (Stage)((Label)event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
