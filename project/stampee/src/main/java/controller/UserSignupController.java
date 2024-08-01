package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserSignupController {
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
		System.out.println("회원가입 버튼 클릭됨");
	}

	private boolean authenticate(String id, String password) {
		return "admin".equals(id) && "password".equals(password);
	}
}