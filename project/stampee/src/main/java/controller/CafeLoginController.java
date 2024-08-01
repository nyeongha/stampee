package controller;
import session.CafeSession;
import dto.response.LoggedCafeDto;
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
import service.CafeService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class CafeLoginController {
	private final CafeService cafeService = new CafeService(new CafeRepository());

	@FXML public TextField emailField;
	@FXML private TextField passwordField;
	@FXML private Button loginButton;
	@FXML private Button signUpButton;



	@FXML
	public void initialize() {
		loginButton.setOnAction(this::handleLoginButtonAction);
		signUpButton.setOnAction(this::handleSignUpButtonAction);
	}



	@FXML
	private void handleLoginButtonAction(ActionEvent event) {
		try {
			String email = emailField.getText();
			String password = passwordField.getText();

			if (email.isEmpty() ||  password.isEmpty() ) {
				showAlert(Alert.AlertType.INFORMATION, "Error", "모든 필드를 입력해주세요.");
				return;
			}

			// 로그인 시 사용자 정보를 받아옴
			LoggedCafeDto loggedCafeDto = cafeService.login(email, password);

			if (loggedCafeDto != null) {
				// 세션에 사용자 정보를 저장
				CafeSession instance = CafeSession.getInstance(loggedCafeDto);
				System.out.println("instance + ======================" + instance.getLoggedCafeDto().getEmail());

				// 세션 검증 및 출력
				verifySession();

				showAlert(Alert.AlertType.INFORMATION, "Success", "로그인이 성공적으로 되었습니다.");
				// 로그인 성공 시, 대시보드로 이동
				loadIndexPage();
			} else {
				showAlert(Alert.AlertType.INFORMATION, "Error", "로그인이 실패했습니다. 이메일 또는 비밀번호를 확인하세요.");
			}
		} catch (NoSuchAlgorithmException e) {
			showAlert(Alert.AlertType.INFORMATION, "Error", "시스템 오류가 발생했습니다. 관리자에게 문의하세요.");
		}
	}


	// 세션 검증 메서드
	private void verifySession() {
		try {
			CafeSession instance = CafeSession.getInstance();
			LoggedCafeDto loggedCafeDto = instance.getLoggedCafeDto();
			System.out.println("Email: " + loggedCafeDto.getEmail());
			System.out.println("Username: " + loggedCafeDto.getName());
			// 필요한 다른 정보도 출력
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	// 알림 창을 보여주는 메서드

	private void showAlert(Alert.AlertType information, String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// 회원가입 버튼 클릭 시 처리 메서드
	@FXML
	private void handleSignUpButtonAction(ActionEvent event) {
		loadSignUpPage();
	}

	// 메인 페이지 로드 메서드 (인증된 사용자의 경우)
	private void loadIndexPage() {
		try {
			Parent indexPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/index/CafeMainPage.fxml")));
			Scene scene = new Scene(indexPage);
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 회원가입 페이지 로드 메서드
	private void loadSignUpPage() {
		try {
			Parent signUpPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/account/SignUpPageMain.fxml")));
			Scene scene = new Scene(signUpPage);
			Stage stage = (Stage) signUpButton.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
