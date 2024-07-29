package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NumberPadController {
	@FXML
	private TextField inputField;

	private StringBuilder inputBuilder = new StringBuilder();

	@FXML
	private void handleDigit(javafx.event.ActionEvent event) {
		Button button = (Button) event.getSource();
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
		closePopup();
	}

	// 팝업창의 동작 관리를 위해 메서드로 따로 관리한다네요
	private void closePopup() {
		inputField.getScene().getWindow().hide();
	}

	// FMXL 파일에 정의된 Textfield' 객체를 메서드를 통해 접근하기 위해
	public TextField getInputField() {
		return inputField;
	}
}
