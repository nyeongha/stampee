
package controller;

import domain.Cafe;
import domain.LoginSession;
import dto.response.LoggedMemberDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.CafeRepository;

import java.io.IOException;

import javax.mail.Session;

public class LoginPageController {
	CafeRepository cafeRepository = new CafeRepository();

	@FXML
	private TextField emailField;

	@FXML
	private TextField passwordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button signUpButton;

	@FXML
	public void initialize() {
		loginButton.setOnAction(this::handleLoginButtonAction);
		signUpButton.setOnAction(this::handleSignUpButtonAction);
	}
	String email;
	String password;
	@FXML
	private void handleLoginButtonAction(ActionEvent event) {
		 email = emailField.getText();
		 password = passwordField.getText();

		System.out.println("Log in button clicked");
		loadIndexPage(email, password);
		LoginSession instance = LoginSession.getInstance(new LoggedMemberDto());

		// LoginSession instance = LoginSession.getInstance();
		LoggedMemberDto loggedMemberDto = instance.getLoggedMemberDto();
		// 로그인 검증 로직 추가
		if (cafeRepository.login(email, password)) {
			showAlert("sucess", "로그인이 성공적으로 완료되었습니다.");
			// 로그인 성공 시 인덱스 페이지로 이동
			loadIndexPage(email, password);
		} else {
			// 로그인 실패 시 경고 메시지 또는 다른 처리
			showAlert("Error", "로그인이 실패했습니다. 다시 시도해주세요");

		}
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

	private void loadIndexPage(String id, String password) {
		try {
			Parent indexPage = FXMLLoader.load(getClass().getResource("/fxml/index/CafeMainPage.fxml"));

			// // 인덱스 페이지의 컨트롤러 가져오기
			// CafeMainPageController controller = loader.getController();
			// controller.setCafeCredentials(id, password);

			Scene scene = new Scene(indexPage);
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
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



