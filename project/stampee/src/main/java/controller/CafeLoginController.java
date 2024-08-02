package controller;
import static util.Popup.*;

import session.CafeSession;
import dto.response.LoggedCafeDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repository.CafeRepository;
import service.CafeService;
import util.SceneNavigator;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
				showFailPopup("모든 필드를 입력해주세요.");
				return;
			}

			// 로그인 시 사용자 정보를 받아옴
			LoggedCafeDto loggedCafeDto = cafeService.login(email, password);
			if (loggedCafeDto != null) {
				CafeSession instance = CafeSession.getInstance(loggedCafeDto);
				showSuccessPopup("로그인이 성공적으로 되었습니다.");
				loadIndexPage();	// 로그인 성공 시, 대시보드로 이동
			} else {
				showFailPopup("로그인이 실패했습니다.");
			}
		} catch (NoSuchAlgorithmException e) {
			showFailPopup("시스템 오류가 발생했습니다. \n관리자에게 문의하세요.");
		}
	}

	// 회원가입 버튼 클릭 시 처리 메서드
	@FXML
	private void handleSignUpButtonAction(ActionEvent event) {
		loadSignUpPage();
	}

	// 메인 페이지 로드 메서드 (인증된 사용자의 경우)
	private void loadIndexPage() {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/index/cafeMainPage.fxml", signUpButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 회원가입 페이지 로드 메서드
	private void loadSignUpPage() {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/signUpPageMain.fxml", signUpButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
