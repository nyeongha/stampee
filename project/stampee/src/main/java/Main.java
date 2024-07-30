
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application{

	@Override
	public void start(Stage primaryStage){

		try{
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/account/LoginPage.fxml"));
			primaryStage.setTitle("카페 위치 정보");

			Scene scene = new Scene(root, 600, 800);
			// scene.getStylesheets().add(getClass().getResource("modern-style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e){
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}

/*	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CouponPage.fxml"));
		Parent root = loader.load();

		CouponController controller = loader.getController();
		controller.initData(38L); // 예시로 memberId 1을 사용

		primaryStage.setTitle("Coupon Viewer");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}*/
