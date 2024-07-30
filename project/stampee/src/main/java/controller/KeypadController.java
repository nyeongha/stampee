package controller;

import static config.DBConnectionUtil.*;

import config.DBConnectionUtil;
import formatter.PhoneNumberFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.*;

public class KeypadController {

	@FXML
	private TextField phoneNumberField;

	private StringBuilder phoneNumber = new StringBuilder();

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
		String inputText = phoneNumberField.getText().trim();

		try {
			// PhoneNumberFormatter를 사용하여 전화번호 형식 변환
			String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(inputText);

			// 회원 확인 및 스탬프 증가
			if (checkMemberAndIncrementStamp(formattedPhoneNumber)) {
				showSuccessPopup();
			} else {
				showFailPopup();
			}
		} catch (IllegalArgumentException e) {
			showFailPopup();
		} catch (SQLException e) {
			e.printStackTrace();
			showFailPopup();
		}

		handleClearClick();
	}

	private boolean checkMemberAndIncrementStamp(String phoneNumber) throws SQLException {
		try (Connection conn = DBConnectionUtil.getConnection()) {
			// 트랜잭션 시작
			conn.setAutoCommit(false);

			try {
				// 회원 확인
				String checkMemberSql = "SELECT member_id FROM member WHERE phone_number = ?";
				try (PreparedStatement pstmt = conn.prepareStatement(checkMemberSql)) {
					pstmt.setString(1, phoneNumber);
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							long memberId = rs.getLong("member_id");

							// 스탬프 증가
							String incrementStampSql = "UPDATE stamp SET count = count + 1 WHERE member_id = ?";
							try (PreparedStatement stampStmt = conn.prepareStatement(incrementStampSql)) {
								stampStmt.setLong(1, memberId);
								int affectedRows = stampStmt.executeUpdate();

								if (affectedRows > 0) {
									conn.commit();
									return true;
								}
							}
						}
					}
				}

				// 회원을 찾지 못했거나 스탬프 업데이트에 실패한 경우
				conn.rollback();
				return false;
			} catch (SQLException e) {
				conn.rollback();
				throw e;
			}
		}
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