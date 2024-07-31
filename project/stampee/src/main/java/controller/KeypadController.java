package controller;

import static formatter.PhoneNumberFormatter.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import repository.MemberRepository;
import repository.StampRepository;
import service.MailService;
import service.StampService;

import java.sql.*;

public class KeypadController {

	@FXML private TextField phoneNumberField;

	private StringBuilder phoneNumber = new StringBuilder();
	private final StampService stampService;

	public KeypadController() {
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		MailService mailService = new MailService();
		stampService = new StampService(stampRepository, memberRepository, mailService);
	}

	@FXML
	private void handleNumberClick(javafx.event.ActionEvent event) {
		String digit = ((javafx.scene.control.Button) event.getSource()).getText();
		phoneNumber.append(digit);
		phoneNumberField.setText(phoneNumber.toString());
	}

	@FXML
	private void handleClearClick() {
		phoneNumber.setLength(0);
		phoneNumberField.clear();
	}

	@FXML
	private void handleSubmitClick() {
		try {
			// LoggedCafeDto cafeInfo = CafeSession.getInstance().getLoggedCafeDto();
			String phoneNumber = formatPhoneNumber(phoneNumberField.getText().trim());
			stampService.saveStamp(1L, phoneNumber, 1);
		} catch (IllegalArgumentException | SQLException e) {
			showFailPopup();
		}
	    showSuccessPopup();
		handleClearClick();
	}

	private void showSuccessPopup() {
		// 성공 팝업 표시 로직
		System.out.println("stamp success.");
	}

	private void showFailPopup() {
		// 실패 팝업 표시 로직
		System.out.println("stamp failed.");
	}
}