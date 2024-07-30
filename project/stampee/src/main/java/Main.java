
import java.util.List;

import controller.StampController;
import domain.Review;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import repository.CafeRepository;
import repository.MemberRepository;
import repository.ReviewRepository;
import service.ReviewService;

public class Main extends Application {

	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// 	Parent root = FXMLLoader.load(
	// 		getClass().getResource("/templates/account/LoginPage.fxml"));
	// 	primaryStage.setTitle("카페 위치 정보");
	// 	primaryStage.setScene(new Scene(root, 600, 800));
	// 	primaryStage.show();
	// }
	//
	// public static void main(String[] args) {
	// 	launch(args);
	// }

	// public void start(Stage primaryStage) throws Exception {
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
	// 	Parent root = loader.load();
	//
	// 	CouponController controller = loader.getController();
	// 	controller.initData(38L); // 예시로 memberId 1을 사용
	//
	// 	primaryStage.setTitle("Coupon Viewer");
	// 	primaryStage.setScene(new Scene(root));
	// 	primaryStage.show();
	// }
	//
	// public static void main(String[] args) {
	// 	launch(args);
	// }

	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
		Parent root = loader.load();

		StampController controller = loader.getController();
		controller.initData(39L, 1L); // 예시로 memberId 1을 사용

		primaryStage.setTitle("Stamp Viewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// @Override
	// public void start(Stage primaryStage) {
	// 	try {
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reviewListView.fxml"));
	// 		Parent root = loader.load();
	// 		Scene scene = new Scene(root);
	// 		primaryStage.setTitle("Review Block Example");
	// 		primaryStage.setScene(scene);
	// 		primaryStage.show();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
	//
	// public static void main(String[] args) {
	// 	launch(args);
	// }
}

