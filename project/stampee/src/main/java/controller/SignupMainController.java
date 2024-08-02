package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.SceneNavigator;

import java.io.IOException;

public class SignupMainController {
	@FXML public VBox signuppagemain;
	@FXML public Button memberSignUpButton;
	@FXML private Button cafeSignUpButton;

	@FXML
	public void initialize() {
		memberSignUpButton.setOnAction(this::handleUserSignUpButtonAction);
		cafeSignUpButton.setOnAction(this::handleCafeSignUpButtonAction);
	}

	@FXML
	private void handleUserSignUpButtonAction(ActionEvent actionEvent) {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/memberSignupPage.fxml", memberSignUpButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCafeSignUpButtonAction(ActionEvent event) {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/cafeSignupPage.fxml", cafeSignUpButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
