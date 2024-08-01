package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import repository.MemberRepository;
import service.UserService;
import view.PopupView;

public class NumberPadController {
	private final UserService userService;
	private StringBuilder inputBuilder = new StringBuilder();
	private PopupView popupView = new PopupView();

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
		inputField.getScene().getWindow().hide();
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
