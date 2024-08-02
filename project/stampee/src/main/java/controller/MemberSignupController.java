package controller;

import static util.Popup.*;

import java.io.IOException;

import domain.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.MemberRepository;
import service.MemberService;

public class MemberSignupController {

	@FXML private TextField userNameField;
	@FXML private TextField phoneNumberField;
	@FXML private TextField emailField;
	@FXML private TextField passwordField;

	private final MemberService memberService;

	public MemberSignupController() {
		MemberRepository memberRepository = new MemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@FXML
	public void handleSignUp(ActionEvent event) {
		String email = emailField.getText();
		String userName = userNameField.getText();
		String password = passwordField.getText();
		String phoneNumber = phoneNumberField.getText();


		if (email.isEmpty() || userName.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
			showFailPopup("모든 필드를 입력해주세요.");
			return;
		}

		Member member = new Member(-1, userName, email, password, phoneNumber);

		boolean success = memberService.memberSignUp(member);

		if (success) {
			showSuccessPopup("회원가입이 성공");
			clearFields();
			navigateToLoginPage(event);
		} else {
			showFailPopup("회원가입에 실패");
		}
	}

	private void navigateToLoginPage(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/account/MemberLoginPage.fxml"));
			Parent loginPage = loader.load();
			Scene loginScene = new Scene(loginPage);
			Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			appStage.setScene(loginScene);
			appStage.show();
		} catch (IOException e) {
			showFailPopup("로그인 페이지로 이동하는 동안 오류가 발생했습니다.");
		}
	}

	private void clearFields() {
		emailField.clear();
		userNameField.clear();
		passwordField.clear();
		phoneNumberField.clear();
	}
}
