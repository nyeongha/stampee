package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
	public static void showSuccessPopup(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(Popup.class.getResource("/fxml/ShareComplete.fxml"));
			Parent root = loader.load();

			Label messageLabel = (Label) root.lookup("#messageLabel");
			messageLabel.setText(message);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void showFailPopup(String message) {
		try {
			FXMLLoader loader = new FXMLLoader(Popup.class.getResource("/fxml/shareFailed.fxml"));
			Parent root = loader.load();

			Label messageLabel = (Label) root.lookup("#messageLabel");
			messageLabel.setText(message);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
