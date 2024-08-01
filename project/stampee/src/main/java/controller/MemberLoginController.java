package controller;

import static util.Popup.*;

import java.io.IOException;
import dto.response.LoggedMemberDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import repository.MemberRepository;
import service.MemberService;
import session.MemberSession;
import util.SceneNavigator;

public class MemberLoginController {
	private final MemberService memberService = new MemberService(new MemberRepository());

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
		String email = emailField.getText();
		String password = passwordField.getText();

		if (email.isEmpty() ||  password.isEmpty() ) {
			showFailPopup("모든 필드를 입력해주세요.");
			return;
		}

		// 로그인 시 사용자 정보를 받아옴
		LoggedMemberDto loggedMemberDto = memberService.login(email, password);

		if (loggedMemberDto != null) {
			// 세션에 사용자 정보를 저장
			MemberSession instance = MemberSession.getInstance(loggedMemberDto);

			showSuccessPopup("로그인이 성공적으로 되었습니다.");
			// 로그인 성공 시, 대시보드로 이동
			loadIndexPage();
		} else {
			showSuccessPopup("로그인이 실패했습니다. \n이메일 또는 비밀번호를 확인하세요.");
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
			SceneNavigator.getInstance().navigateTo("/fxml/account/SignUpPageMain.fxml", loginButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 회원가입 페이지 로드 메서드
	private void loadSignUpPage() {
		try {
			SceneNavigator.getInstance().navigateTo("/fxml/account/SignUpPageMain.fxml", signUpButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
