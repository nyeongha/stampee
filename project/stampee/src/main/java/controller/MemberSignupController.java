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
import util.SceneNavigator;

public class MemberSignupController {

	@FXML private TextField emailField;
	@FXML private TextField nameField;
	@FXML private TextField passwordField;
	@FXML private TextField contactField;

	private final MemberService memberService;

	public MemberSignupController() {
		MemberRepository memberRepository = new MemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@FXML
	public void handleSignUp(ActionEvent event) {
		String email = emailField.getText();
		String name = nameField.getText();
		String password = passwordField.getText();
		String contact = contactField.getText();

		if (email.isEmpty() || name.isEmpty() || password.isEmpty() || contact.isEmpty()) {
			showFailPopup("모든 필드를 입력해주세요.");
			return;
		}

		Member member = new Member(0, name, email, password, contact);
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
			// SceneNavigator.getInstance().navigateTo("/fxml/account/MemberLoginPage.fxml", event);
		} catch (IOException e) {
			showFailPopup("로그인 페이지로 이동하는 동안 오류가 발생했습니다.");
		}
	}

	private void clearFields() {
		emailField.clear();
		nameField.clear();
		passwordField.clear();
		contactField.clear();
	}
}
