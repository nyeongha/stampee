import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/index/CafeMainPage.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Stamp Keypad");
		Scene scene = new Scene(root, 600, 800);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

		// FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
		// Parent root = loader.load();
		//
		// CouponController controller = loader.getController();
		// controller.initData(37L); // 예시로 memberId 1을 사용
		//
		// primaryStage.setTitle("Coupon Viewer");
		// primaryStage.setScene(new Scene(root));
		// primaryStage.show();

	public static void main(String[] args) {
		launch(args);
	}

}
