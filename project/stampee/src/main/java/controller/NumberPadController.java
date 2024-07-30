package controller;

import static config.DBConnectionUtil.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import formatter.PhoneNumberFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NumberPadController {
	@FXML
	private TextField inputField;

	private StringBuilder inputBuilder = new StringBuilder();

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
		String inputText = inputField.getText().trim();
		System.out.println("Input phone number: " + inputText);

		try {
			// PhoneNumberFormatter를 사용하여 전화번호 형식 변환
			String formattedPhoneNumber = PhoneNumberFormatter.formatPhoneNumber(inputText);
			System.out.println("Formatted phone number: " + formattedPhoneNumber);

			String sql = "SELECT COUNT(*) FROM member WHERE phone_number = ?";
			try (Connection conn = getConnection();
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, formattedPhoneNumber);

				// 실제 SQL 쿼리 내용을 출력
				String actualSQL = sql.replace("?", "'" + formattedPhoneNumber + "'");
				System.out.println("Executing query: " + actualSQL);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						int count = rs.getInt(1);
						System.out.println("Query result count: " + count);
						if (count > 0) {
							showSuccessPopup();
						} else {
							showFailPopup();
						}
					} else {
						System.out.println("No results returned from query");
						showFailPopup();
					}
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid phone number format: " + e.getMessage());
			showFailPopup();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException occurred: " + e.getMessage());
			showFailPopup();
		}
		closePopup();
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
}
