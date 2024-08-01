package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import repository.CafeRepository;
import service.CafeService;

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
			showAlert("Error", "Passwords do not match.");
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
		nameField.clear();
		addressField.clear();
		passwordField.clear();
		confirmPasswordField.clear();
		emailField.clear();
		contactField.clear();
	}
}
