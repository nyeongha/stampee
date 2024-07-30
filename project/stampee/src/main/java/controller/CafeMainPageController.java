package controller;

import domain.Cafe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import repository.CafeRepository;

public class CafeMainPageController implements Initializable {

	@FXML
	private Text memberCountText;
	@FXML
	private GridPane customerGrid;
	@FXML
	private Text cafeNameTextField;

	private String email;
	private String password;

	public CafeMainPageController(){
		CafeRepository cafeRepository = new CafeRepository();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		cafeNameTextField.setText("fave");
		// Load customer data and other initial data
	}

	public void setMember(Cafe cafe) {
		cafeNameTextField.setText(cafe.getName());
		// memberCountText.setText(cafeRepository.fint);
		// emailLabel.setText(cafe.getEmail());
		// phoneNumberLabel.setText(cafe.getPhoneNumber());
		// addressLabel.setText(cafe.getAddress());
	}


}
