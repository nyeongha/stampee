package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
			Parent userSignUpPage = FXMLLoader.load(getClass().getResource("/templates/account/UserSignupPage.fxml"));
			Scene scene1 = new Scene(userSignUpPage);

			// Get the current stage (window)
			Stage stage1 = (Stage) memberSignUpButton.getScene().getWindow();
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleCafeSignUpButtonAction(ActionEvent event) {
		try {
			Parent cafeSignUpPage = FXMLLoader.load(getClass().getResource("/templates/account/CafeSignupPage.fxml"));
			Scene scene2 = new Scene(cafeSignUpPage);

			Stage stage2 = (Stage) cafeSignUpButton.getScene().getWindow();
			stage2.setScene(scene2);
			stage2.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadIndexPage() {
		try {
			Parent indexPage = FXMLLoader.load(getClass().getResource("/templates/account/SignUpPageMain.fxml"));
			Scene scene = new Scene(indexPage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
