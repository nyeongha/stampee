package controller;


import static formatter.PhoneNumberFormatter.*;
import static java.lang.Integer.*;
import static util.Popup.*;

import dto.response.LoggedCafeDto;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

import repository.MemberRepository;
import repository.StampRepository;
import service.MailService;
import service.StampService;
import session.CafeSession;
import java.sql.*;

import static util.SceneNavigator.getInstance;

public class KeypadController {
	@FXML private TextField phoneNumberField;
	@FXML private TextField stampCountField;

	private boolean isPhoneNumberInput = true;
	private StringBuilder stampCount = new StringBuilder();
	private StringBuilder phoneNumber = new StringBuilder();
	private final StampService stampService;

	public KeypadController() {
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		MailService mailService = new MailService();
		stampService = new StampService(stampRepository, memberRepository, mailService);
	}

	@FXML
	public void initialize() {
		phoneNumberField.setEditable(false);
		stampCountField.setEditable(false);
		updateDisplayFields();

		// 마우스 클릭으로 전환
		phoneNumberField.setOnMouseClicked(event -> setPhoneNumberInput(true));
		stampCountField.setOnMouseClicked(event -> setPhoneNumberInput(false));
	}

	@FXML
	private void handleNumberClick(ActionEvent event) {
		String digit = ((Button) event.getSource()).getText();
		if (isPhoneNumberInput) {
			phoneNumber.append(digit);
		} else {
			stampCount.append(digit);
		}
		updateDisplayFields();
	}

	@FXML
	private void handleClearClick() {
		if (isPhoneNumberInput) {
			phoneNumber.setLength(0);
		} else {
			stampCount.setLength(0);
		}
		updateDisplayFields();
	}

	@FXML
	private void handleToggleInput() {
		isPhoneNumberInput = !isPhoneNumberInput;
		updateDisplayFields();
	}

	@FXML
	private void handleSubmitClick() {
		if (phoneNumber.length() == 0 || stampCount.length() == 0) {
			showFailPopup("전화번호와 스탬프 개수를 모두 입력해주세요.");
			return;
		}
		try {
			LoggedCafeDto cafe = CafeSession.getInstance().getLoggedCafeDto();
			stampService.saveStamp(cafe.getCafeId(), formatPhoneNumber(phoneNumber.toString()), parseInt(stampCount.toString()));
			showSuccessPopup("스탬프 적립 성공");
			goToCafeMainPage();
		} catch (IllegalArgumentException | SQLException e) {
			showFailPopup(e.getMessage());
		}

		phoneNumber.setLength(0);
		stampCount.setLength(0);
		updateDisplayFields();
	}

	@FXML
	private void GoToHome() {
		goToCafeMainPage();
	}

	private void updateDisplayFields() {
		phoneNumberField.setText(phoneNumber.toString());
		stampCountField.setText(stampCount.toString());
		phoneNumberField.setStyle(isPhoneNumberInput ? "-fx-background-color: #e0e0e0;" : "");
		stampCountField.setStyle(!isPhoneNumberInput ? "-fx-background-color: #e0e0e0;" : "");
	}

	// 마우스 클릭 전환 메서드
	private void setPhoneNumberInput(boolean isPhoneNumber) {
		isPhoneNumberInput = isPhoneNumber;
		updateDisplayFields();
	}

	private void goToCafeMainPage() {
		try {
			getInstance().navigateTo("/fxml/index/CafeMainPage.fxml", phoneNumberField);
		} catch (IOException e) {
			e.printStackTrace();
			showFailPopup("화면 전환 중 오류가 발생했습니다.");
		}
	}
}
