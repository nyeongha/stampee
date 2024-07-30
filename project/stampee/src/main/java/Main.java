
import java.io.InputStream;

import controller.CouponController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
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

