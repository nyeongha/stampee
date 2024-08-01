package controller;

import static domain.ReviewType.*;

import java.io.IOException;
import java.util.Objects;

import domain.ReviewType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import session.CafeSession;
import javafx.stage.Stage;

public class HeaderController {
	@FXML
	public Label logoutLabel;

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
	@FXML
	public void handleLogout(MouseEvent mouseEvent) {
		/*
		1. CafeSession 값 비워주기
		2. 버튼 클릭 시 LoginPage.fxml로 이동
		3. header의 배너에서 logout 버튼 안 보이게 하기
		 */
		// 1. CafeSession 값 비워주기
		showAlert(Alert.AlertType.INFORMATION, "Logout", "로그아웃 되었습니다.");

		CafeSession.clearSession();
		//2. 버튼 클릭 시 LoginPage.fxml로 이동
		try {
			Parent loginPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/account/LoginPage.fxml")));
			Scene scene = new Scene(loginPage);
			Stage stage = (Stage) ((Label) mouseEvent.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//3. header의 배너에서 logout 라벨 안 보이게 하기
		updateHeaderVisibility();

	}

	private void updateHeaderVisibility() {
		// 로그아웃 라벨만 숨김
		if (logoutLabel != null) {
			logoutLabel.setVisible(false);
		}
	}

	// 알림 창을 보여주는 메서드
	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
