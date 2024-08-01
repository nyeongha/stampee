import controller.StampController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
			VBox root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Coupon Management");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// @Override
	// public void start(Stage primaryStage) {
	// 	try {
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
	// 		Pane root = loader.load();
	//
	// 		// Initialize data if necessary
	// 		StampController controller = loader.getController();
	// 		controller.initData(39L, 1L);  // Example memberId and cafeId, replace with actual values
	//
	// 		Scene scene = new Scene(root);
	// 		primaryStage.setScene(scene);
	// 		primaryStage.setTitle("Stamp Management");
	// 		primaryStage.show();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }

	public static void main(String[] args) {
		launch(args);
	}
}
