package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
			SceneNavigator.getInstance().navigateTo("/fxml/account/MemberLoginPage.fxml", actionEvent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCafeLoginButtonAction(ActionEvent event) {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/CafeLoginPage.fxml", cafeLoginButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
