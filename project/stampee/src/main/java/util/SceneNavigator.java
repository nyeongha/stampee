package util;


import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SceneNavigator {
	private static SceneNavigator instance;

	private SceneNavigator() {}

	public static SceneNavigator getInstance() {
		if (instance == null) {
			instance = new SceneNavigator();
		}
		return instance;
	}

	public void navigateTo(String fxmlPath, Event event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = loader.load();
		Stage stage = getStageFromEvent(event);

		Scene scene = new Scene(root, 600, 800);
		stage.setScene(scene);
		stage.show();
	}

	public void navigateTo(String fxmlPath, Control control) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
		Parent root = loader.load();
		Scene scene = new Scene(root, 600, 800);
		Stage stage = (Stage)control.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	private Stage getStageFromEvent(Event event) {
		if (event.getSource() instanceof Label) {
			return (Stage) ((Label) event.getSource()).getScene().getWindow();
		} else if (event.getSource() instanceof ImageView) {
			return (Stage) ((ImageView) event.getSource()).getScene().getWindow();
		} else {
			throw new IllegalArgumentException("Unsupported event source");
		}
	}
}
