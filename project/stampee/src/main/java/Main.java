import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(
			getClass().getResource("/templates/account/LoginPage.fxml"));
		primaryStage.setTitle("카페 위치 정보");
		primaryStage.setScene(new Scene(root, 600, 800));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}