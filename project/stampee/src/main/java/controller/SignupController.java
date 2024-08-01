package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import repository.CafeRepository;
import service.CafeService;
import util.Popup;

public class SignupController {
	@FXML private TextField nameField;
	@FXML private TextField addressField;
	@FXML private PasswordField passwordField;
	@FXML private PasswordField confirmPasswordField;
	@FXML private TextField emailField;
	@FXML private TextField contactField;
	@FXML private VBox signupContainer;

	private final CafeService cafeService;

	public SignupController() {
		CafeRepository cafeRepository = new CafeRepository();
		this.cafeService = new CafeService(cafeRepository);
	}

	@FXML
	private void handleSignup() {
		long name = Long.parseLong(nameField.getText());
		String address = addressField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		String email = emailField.getText();
		String contact = contactField.getText();

		if (!password.equals(confirmPassword)) {
			Popup.showFailPopup("Passwords do not match.");
		}
	}


	private void clearFields() {
		nameField.clear();
		addressField.clear();
		passwordField.clear();
		confirmPasswordField.clear();
		emailField.clear();
		contactField.clear();
	}
}
