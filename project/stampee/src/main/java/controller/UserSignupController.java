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
	private void handleLoginButtonAction(ActionEvent event) {
		String password = passwordField.getText();

		System.out.println("Log in button clicked");
		loadIndexPage();

		// 로그인 검증 로직 추가
		// if (authenticate(id, password)) {
		// 	// 로그인 성공 시 인덱스 페이지로 이동
		// 	loadIndexPage();
		// } else {
		// 	// 로그인 실패 시 경고 메시지 또는 다른 처리
		// 	System.out.println("로그인 실패");
		// }
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

	private void loadIndexPage() {
		try {
			Parent indexPage = FXMLLoader.load(getClass().getResource("/templates/account/SignUpPageMain.fxml"));
			Scene scene = new Scene(indexPage);
			// Stage stage = (Stage) loginButton.getScene().getWindow();
			// stage.setScene(scene);
			// stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void AccountController(MouseEvent mouseEvent) {
	}
}