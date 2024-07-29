
import controller.CouponController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		// // FXMLLoader 생성
		// FXMLLoader loader = new FXMLLoader(getClass().getResource("/stamp.fxml"));
		//
		// // 루트 요소 로드
		// Parent root = loader.load();
		// StampController stampController = loader.getController();
		// stampController.initData(38L, 1L);
		//
		// // 스테이지 설정
		// primaryStage.setTitle("스탬프");
		// primaryStage.setScene(new Scene(root, 600, 800));
		// primaryStage.show();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/CouponPage.fxml"));
		Parent root = loader.load();
		CouponController couponController = loader.getController();
		couponController.initData(39L);
		primaryStage.setTitle("Coupon");
		primaryStage.setScene(new Scene(root, 600, 800));
		primaryStage.show();
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
} */