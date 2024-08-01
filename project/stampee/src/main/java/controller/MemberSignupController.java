package controller;

import java.io.IOException;

import domain.Member;
import javafx.event.ActionEvent; // JavaFX 이벤트 임포트
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.MemberRepository;
import service.MemberService;

public class MemberSignupController {

	@FXML private TextField emailField;
	@FXML private TextField nameField;
	@FXML private TextField passwordField;
	@FXML private TextField contactField;
	@FXML private Button signUpButton;

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
			showAlert("Error", "모든 필드를 입력해주세요.");
			return;
		}

		Member member = new Member(-1, name, password, email, contact);
		boolean success = memberService.memberSignUp(member);

		if (success) {
			showAlert("Success", "회원가입이 성공적으로 완료되었습니다!");
			clearFields();
			navigateToLoginPage(event);
		} else {
			showAlert("Error", "회원가입에 실패했습니다. 다시 시도해주세요.");
		}
	}

	private void navigateToLoginPage(ActionEvent event) {
		try {
			System.out.println("여기까지 왁다~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~````````````````````");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/account/MemberLoginPage.fxml"));
			System.out.println("화면까지호출");
			// loader.setControllerFactory(param -> new MemberSignupController());
			Parent loginPage = loader.load();
			Scene loginScene = new Scene(loginPage);
			Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			appStage.setScene(loginScene);
			appStage.show();
		} catch (IOException e) {
			showAlert("Error", "로그인 페이지로 이동하는 동안 오류가 발생했습니다.");
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void clearFields() {
		emailField.clear();
		nameField.clear();
		passwordField.clear();
		contactField.clear();
	}
}
