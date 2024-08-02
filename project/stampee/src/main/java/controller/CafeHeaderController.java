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
import javafx.stage.Stage;
import session.CafeSession;
import session.MemberSession;

public class CafeHeaderController {
	private static final long ALL_MEMBER = 0L;

	@FXML
	public Label logoutLabel;
	@FXML
	public Label loginLabel;


	@FXML
	public void handleLogo(MouseEvent event) throws IOException {
		getInstance().navigateTo("/fxml/index/MemberMainPage.fxml", event);
	}

	@FXML
	public void handleLogin(MouseEvent mouseEvent) throws IOException {
		getInstance().navigateTo("/fxml/account/LoginPageMain.fxml", mouseEvent);
	}

	@FXML
	public void handleLogout(MouseEvent mouseEvent) throws IOException {

		if(CafeSession.getInstance() !=null){
			CafeSession.clearSession();
		}else if(MemberSession.getInstance() !=null){
			MemberSession.clearSession();
		}
		showAlert(Alert.AlertType.INFORMATION, "Logout", "로그아웃 되었습니다.");

		getInstance().navigateTo("/fxml/account/LoginPageMain.fxml", mouseEvent);
	}

	private void updateHeaderVisibility() {
		// 로그아웃 라벨만 숨김
		if (logoutLabel != null) {
			logoutLabel.setVisible(false);
		}
	}

	@FXML
	private void goToKeypadView() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KeypadView.fxml"));
			Parent keypadView = loader.load();

			Stage stage = (Stage) loginLabel.getScene().getWindow();

			Scene keypadScene = new Scene(keypadView);

			stage.setScene(keypadScene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
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