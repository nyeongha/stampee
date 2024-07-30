package controller;

import java.io.IOException;

import formatter.PhoneNumberFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import repository.MemberRepository;
import service.UserService;

public class NumberPadController {
	private final UserService userService;
	private StringBuilder inputBuilder = new StringBuilder();
	@FXML private TextField inputField;

	public NumberPadController() {
		MemberRepository memberRepository = new MemberRepository();
		userService = new UserService(memberRepository);
	}

	@FXML
	private void handleDigit(javafx.event.ActionEvent event) {
		Button button = (Button)event.getSource();
		String digit = button.getText();
		inputBuilder.append(digit);
		inputField.setText(inputBuilder.toString());
	}

	@FXML
	private void handleClear() { //번호 지우기
		inputBuilder.setLength(0);
		inputField.setText("");
	}

	@FXML
	private void handleOK() {
		try {
			String toPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(inputField.getText().trim());
			userService.findMemberByPhoneNumber(toPhoneNumber);
		} catch (IllegalArgumentException e) {
			showFailPopup();
		}
		closePopup();
		showSuccessPopup();
	}

	// 팝업창의 동작 관리를 위해 메서드로 따로 관리한다네요
	private void closePopup() {
		inputField.getScene().getWindow().hide();
	}

	private void showSuccessPopup() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ShareComplete.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showFailPopup() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/shareFailed.fxml"));
			Parent root = loader.load();

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// FMXL 파일에 정의된 Textfield' 객체를 메서드를 통해 접근하기 위해
	public TextField getInputField() {
		return inputField;
	}

	@FXML
	private void handleClose(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
}
