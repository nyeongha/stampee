package controller;


import config.DBConnectionUtil;
import formatter.PhoneNumberFormatter;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;

import repository.MemberRepository;
import repository.StampRepository;
import service.MailService;
import service.StampService;

import java.sql.*;

public class KeypadController {


	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField stampCountField;

	private boolean isPhoneNumberInput = true;
	private StringBuilder currentInput = new StringBuilder();
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

	// 마우스 클릭 전환 메서드
	private void setPhoneNumberInput(boolean isPhoneNumber) {
		isPhoneNumberInput = isPhoneNumber;
		updateDisplayFields();
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

	private void updateDisplayFields() {
		phoneNumberField.setText(phoneNumber.toString());
		stampCountField.setText(stampCount.toString());
		phoneNumberField.setStyle(isPhoneNumberInput ? "-fx-background-color: #e0e0e0;" : "");
		stampCountField.setStyle(!isPhoneNumberInput ? "-fx-background-color: #e0e0e0;" : "");
	}

	@FXML
	private void handleSubmitClick() {

		if (phoneNumber.length() == 0 || stampCount.length() == 0) {
			showFailPopup("전화번호와 스탬프 개수를 모두 입력해주세요.");
			return;
		}

		try {
			String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(phoneNumber.toString());
			int stampCountValue = Integer.parseInt(stampCount.toString());

			if (checkMemberAndIncrementStamp(formattedPhoneNumber, stampCountValue)) {
				showSuccessPopup();
			} else {
				showFailPopup("스탬프 적립에 실패했습니다.");
			}
		} catch (IllegalArgumentException e) {
			showFailPopup("잘못된 입력입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			showFailPopup("데이터베이스 오류가 발생했습니다.");
		}

		phoneNumber.setLength(0);
		stampCount.setLength(0);
		updateDisplayFields();
	}

	private boolean checkMemberAndIncrementStamp(String phoneNumber, int stampCount) throws SQLException {
		try (Connection conn = DBConnectionUtil.getConnection()) {
			conn.setAutoCommit(false);

			try {
				String checkMemberSql = "SELECT member_id FROM member WHERE phone_number = ?";
				try (PreparedStatement pstmt = conn.prepareStatement(checkMemberSql)) {
					pstmt.setString(1, phoneNumber);
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							long memberId = rs.getLong("member_id");

							String incrementStampSql = "UPDATE stamp SET count = count + ? WHERE member_id = ?";
							try (PreparedStatement stampStmt = conn.prepareStatement(incrementStampSql)) {
								stampStmt.setInt(1, stampCount);
								stampStmt.setLong(2, memberId);
								int affectedRows = stampStmt.executeUpdate();

								if (affectedRows > 0) {
									conn.commit();
									return true;
								}
							}
						}
					}
				}

				conn.rollback();
				return false;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		}
	}

	@FXML
	private void GoToHome() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage) phoneNumberField.getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			failGoToHome("화면 전환 중 오류가 발생했습니다.");
		}
	}


	private void failGoToHome(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("오류");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}


	private void showSuccessPopup() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("성공");
		alert.setHeaderText(null);
		alert.setContentText("스탬프가 성공적으로 적립되었습니다.");
		alert.showAndWait();
	}

	private void showFailPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("오류");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}