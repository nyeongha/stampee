package controller;

import domain.Cafe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import repository.CafeRepository;

public class CafeMainPageController implements Initializable {

	private static CafeRepository cafeRepository = new CafeRepository();

	@FXML
	private Text memberCountText;
	@FXML
	private GridPane customerGrid;
	@FXML
	private Text cafeNameTextField;

	private String email;
	private String password;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Load customer data and other initial data
		loadCustomerData();
	}

	public void setCafeCredentials(String email, String password) {
		this.email = email;
		this.password = password;
		loadCafeData();
	}

	private void loadCustomerData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

			int row = 0;
			int col = 0;
			while (resultSet.next()) {
				String customerName = resultSet.getString("name");
				Text customerText = new Text(customerName);
				customerGrid.add(customerText, col, row);
				row++;
			}

			resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM customers");
			if (resultSet.next()) {
				int count = resultSet.getInt("count");
				memberCountText.setText(String.valueOf(count));
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCafeData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdatabase", "username", "password");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM cafes WHERE email = '" + this.email + "' AND password = '" + this.password + "'");

			if (resultSet.next()) {
				String cafeName = resultSet.getString("name");
				cafeNameTextField.setText(cafeName);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
