

import java.io.File;
import java.net.URL;

import controller.CouponController;

import controller.StampController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

	// @Override
	// public void start(Stage primaryStage) {
	// 	try {
	// 		Font font = Font.loadFont(getClass().getResourceAsStream("/font/SingleDay-Regular.ttf"), 12);
	// 		if (font == null) {
	// 			System.out.println("Failed to load font");
	// 		} else {
	// 			System.out.println("Font loaded successfully: " + font.getFamily());
	// 		}
	//
	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
	// 		Parent root = loader.load();
	//
	// 		CouponController controller = loader.getController();
	// 		controller.initData(37L);
	//
	// 		// Scene scene = new Scene(root);
	// 		//
	// 		// // CSS 파일을 Scene에 추가
	// 		// scene.getStylesheets().add(getClass().getResource("/css/font-style.css").toExternalForm());
	//
	//
	// 		primaryStage.setTitle("Coupon Viewer");
	// 		primaryStage.setScene(new Scene(root));
	// 		primaryStage.show();
	// 	} catch (Exception e) {
	// 		e.printStackTrace();
	// 	}
	// }
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
		Parent root = loader.load();

		StampController controller = loader.getController();
		controller.initData(37L, 9L); // 예시로 memberId 1을 사용

		primaryStage.setTitle("Stamp Viewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		// try {
		//
		// 	File file = new File("src/main/resources/fxml/stamp.fxml");
		// 	if (!file.exists()) {
		// 		System.out.println("FXML file does not exist at: " + file.getAbsolutePath());
		// 	} else {
		// 		FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
		//
		// 		Parent root = loader.load();
		//
		// 		StampController controller = loader.getController();
		// 		controller.initData(37L, 9L);
		//
		// 		primaryStage.setTitle("Stamp Viewer");
		// 		primaryStage.setScene(new Scene(root));
		// 		primaryStage.show();
		//
		// 	}
		//
		//
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// 	System.out.println("Error message: " + e.getMessage());
		// }
	}

	public static void main(String[] args) {
		launch(args);
	}
}

