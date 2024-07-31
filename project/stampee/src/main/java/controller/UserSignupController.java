package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
		// 회원가입 버튼 클릭 시 로직 추가
		System.out.println("회원가입 버튼 클릭됨");
	}

	private boolean authenticate(String id, String password) {
		// 간단한 검증 예시
		return "admin".equals(id) && "password".equals(password);
	}


}