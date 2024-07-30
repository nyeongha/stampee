package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserSignupController {public VBox signuppagemain;
	public TextField emailField;
	public TextField nameField;
	public TextField passwordField;
	public TextField contactField;
	@FXML
	private Button signUpButton;

	@FXML
	public void initialize() {
		signUpButton.setOnAction(this::handleSignUpButtonAction);
	}

	@FXML
	private void handleSignUpButtonAction(ActionEvent event) {

		try {
			Parent loginPage = FXMLLoader.load(getClass().getResource("/fxml/account/LoginPage.fxml"));
			Scene loginScene = new Scene(loginPage);
			Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			appStage.setScene(loginScene);
			appStage.show();
		} catch (IOException e) {
			showAlert("Error", "로그인 페이지로 이동하는 동안 오류가 발생했습니다.");
		}

	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}