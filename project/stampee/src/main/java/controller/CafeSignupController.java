package controller;

import static util.Popup.*;

import java.io.IOException;
import java.util.Objects;

import domain.Cafe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CafeService;
import repository.CafeRepository;

public class CafeSignupController {
	@FXML private TextField emailField;
	@FXML private TextField nameField;
	@FXML private TextField addressField;
	@FXML private TextField passwordField;
	@FXML private TextField contactField;
	@FXML private TextField menuField1;
	@FXML private TextField menuField2;

	private final CafeService cafeService;

	public CafeSignupController() {
		this.cafeService = new CafeService(new CafeRepository());
	}

	@FXML
	private void handleSignUp(ActionEvent event) {
		String email = emailField.getText();
		String name = nameField.getText();
		String address = addressField.getText();
		String password = passwordField.getText();
		String contact = contactField.getText();
		String menu1 = menuField1.getText();
		String menu2 = menuField2.getText();

		if (email.isEmpty() || name.isEmpty() || address.isEmpty() || password.isEmpty() || contact.isEmpty() || menu1.isEmpty() || menu2.isEmpty()) {
			showFailPopup("모든 필드를 입력해주세요.");
			return;
		}

		Cafe cafe = new Cafe(-1, name, address, password, email, contact);

		boolean success = cafeService.cafeSignUp(cafe, menu1, menu2);

		if (success) {
			showSuccessPopup("회원가입이 성공");
			clearFields();
			navigateToLoginPage(event);
		} else {
			showFailPopup("회원가입에 실패했습니다.\n 다시 시도해주세요.");
		}
	}

	private void navigateToLoginPage(ActionEvent event) {
		try {
			Parent loginPage = FXMLLoader.load(
				Objects.requireNonNull(getClass().getResource("/fxml/account/CafeLoginPage.fxml")));
			Scene loginScene = new Scene(loginPage);
			Stage appStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
			appStage.setScene(loginScene);
			appStage.show();
		} catch (IOException e) {
			showFailPopup("오류가 발생했습니다.");
		}
	}

	private void clearFields() {
		emailField.clear();
		nameField.clear();
		addressField.clear();
		passwordField.clear();
		contactField.clear();
		menuField1.clear();
		menuField2.clear();
	}
}
