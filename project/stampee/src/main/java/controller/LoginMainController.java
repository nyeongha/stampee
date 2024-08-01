package controller;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.SceneNavigator;

public class LoginMainController {

	@FXML public Button memberLoginButton;
	@FXML public Button cafeLoginButton;

	@FXML
	public void initialize() {
		memberLoginButton.setOnAction(this::handleMemberLoginButtonAction);
		cafeLoginButton.setOnAction(this::handleCafeLoginButtonAction);
	}

	@FXML
	private void handleMemberLoginButtonAction(ActionEvent actionEvent) {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/MemberLoginPage.fxml", memberLoginButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCafeLoginButtonAction(ActionEvent event) {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/CafeLoginPage.fxml", cafeLoginButton);
			// Parent cafeSignUpPage = FXMLLoader.load(
			// 	Objects.requireNonNull(getClass().getResource("/fxml/account/CafeLoginPage.fxml")));
			// Scene scene2 = new Scene(cafeSignUpPage);
			//
			// Stage stage2 = (Stage) cafeLoginButton.getScene().getWindow();
			// stage2.setScene(scene2);
			// stage2.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
