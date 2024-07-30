

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
		Parent root = loader.load();

		CouponController controller = loader.getController();
		controller.initData(37L); // 예시로 memberId 1을 사용

		primaryStage.setTitle("Coupon Viewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

