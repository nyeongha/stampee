package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.MemberRepository;
import service.MemberService;

public class NumberPadController {
	private final MemberService memberService;
	private StringBuilder inputBuilder = new StringBuilder();

	@FXML private TextField inputField;

	public NumberPadController() {
		MemberRepository memberRepository = new MemberRepository();
		memberService = new MemberService(memberRepository);
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
		inputField.getScene().getWindow().hide();
	}

	public TextField getInputField() {
		return inputField;
	}

	@FXML
	private void handleClose(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
}
