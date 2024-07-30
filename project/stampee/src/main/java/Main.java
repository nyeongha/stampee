import controller.CouponController;

import controller.StampController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// FXML 파일 경로를 설정합니다.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));

			// FXML 파일을 로드하고 루트 레이아웃을 가져옵니다.
			Parent root = loader.load();

			// Scene을 설정하고 Stage에 추가합니다.
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Coupon Application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	//
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

	// @Override
	// public void start(Stage primaryStage) {
	// 	try {
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/reusables/Header.fxml"));
	// 		BorderPane root = loader.load();
	//
	// 		Scene scene = new Scene(root, 600, 800);
	// 		primaryStage.setTitle("Header Test Application");
	// 		primaryStage.setScene(scene);
	// 		primaryStage.show();
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// }
	//
	// public static void main(String[] args) {
	// 	launch(args);
	// }
}

