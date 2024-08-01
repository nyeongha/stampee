package controller;

import static domain.ReviewType.*;
import static util.SceneNavigator.*;

import java.io.IOException;
import domain.ReviewType;
import dto.response.LoggedMemberDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import session.CafeSession;
import javafx.stage.Stage;
import session.MemberSession;

public class HeaderController {
	private static final long ALL_MEMBER = 0L;

	@FXML
	public Label logoutLabel;

	@FXML
	public void handleMyReview(MouseEvent event) {
		LoggedMemberDto member = MemberSession.getInstance().getLoggedMemberDto();
		navigateTo("/fxml/reviews.fxml", event, MEMBER, member.getMemberId());
	}

	@FXML
	public void handleReviews(MouseEvent event) {
		navigateTo("/fxml/reviews.fxml", event, ALL, ALL_MEMBER);
	}

	@FXML
	public void handleMyCoupon(MouseEvent event) {
		getInstance().navigateTo("/fxml/CouponPage.fxml", event);
	}

	@FXML
	public void handleLogo(MouseEvent event) {
		getInstance().navigateTo("/fxml/index/MemberMain.fxml", event);
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
		showAlert(Alert.AlertType.INFORMATION, "Logout", "로그아웃 되었습니다.");
		CafeSession.clearSession();			// 1. CafeSession 값 비워주기
		getInstance().navigateTo("/templates/account/LoginPage.fxml", mouseEvent);			//2. 버튼 클릭 시 LoginPage.fxml로 이동
		updateHeaderVisibility();			//3. header의 배너에서 logout 라벨 안 보이게 하기
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