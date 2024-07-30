<<<<<<< HEAD

import java.io.IOException;
import java.util.List;
import java.io.InputStream;

=======
>>>>>>> c24c581 ([FEAT] : 적립 요청 기능 추가)
import controller.CouponController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;
=======
>>>>>>> c24c581 ([FEAT] : 적립 요청 기능 추가)
import javafx.stage.Stage;


public class Main extends Application {

	@Override
<<<<<<< HEAD
	public void start(Stage primaryStage) throws Exception {
		try {
			InputStream is = getClass().getResourceAsStream("/font/Jua-Regular.ttf");
			if (is != null) {
				Font font = Font.loadFont(is, 12);
				if (font != null) {
					System.out.println("Font loaded: " + font.getName());
				} else {
					System.out.println("Failed to load font");
				}
			} else {
				System.out.println("Font file not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/account/LoginPage.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Review Block Example");
=======
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KeypadView.fxml"));
		Parent root = loader.load();
		primaryStage.setTitle("Stamp Keypad");
		Scene scene = new Scene(root, 600, 800);
>>>>>>> c24c581 ([FEAT] : 적립 요청 기능 추가)
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

