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
			Parent userSignUpPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/account/MemberLoginPage.fxml")));
			Scene scene1 = new Scene(userSignUpPage);

			// Get the current stage (window)
			Stage stage1 = (Stage) memberLoginButton.getScene().getWindow();
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCafeLoginButtonAction(ActionEvent event) {
		try {
			Parent cafeSignUpPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/account/CafeLoginPage.fxml")));
			Scene scene2 = new Scene(cafeSignUpPage);

			Stage stage2 = (Stage) cafeLoginButton.getScene().getWindow();
			stage2.setScene(scene2);
			stage2.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
