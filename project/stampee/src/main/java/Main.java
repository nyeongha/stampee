import javax.mail.MessagingException;

import controller.CreateReviewController;
import controller.StampController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.CouponRepository;
import service.CouponService;
import service.MailService;
import service.Scheduler;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/account/CafeLoginPage.fxml"));

			Scene scene = new Scene(root, 600, 800);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// public void start(Stage primaryStage) throws Exception {
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
	// 	Parent root = loader.load();
	//
	// 	StampController controller = loader.getController();
	//
	// 	controller.initData(1L,10L); // 예시로 memberId 1을 사용
	//
	//
	// 	primaryStage.setTitle("Stamp Viewer");
	// 	primaryStage.setScene(new Scene(root));
	//
	// 	primaryStage.setTitle("Coupon Application");
	// 	primaryStage.show();
	//
	// }

	//
	// @Override
	// public void start(Stage primaryStage) throws Exception{
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KeypadView.fxml"));
	// 	Parent root = loader.load();
	// 	Scene scene = new Scene(root, 600, 800);
	// 	primaryStage.setScene(scene);
	// 	primaryStage.show();
	// }
	// public void start(Stage primaryStage) throws Exception {
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
	// 	Parent root = loader.load();
	//
	// 	CouponController controller = loader.getController();
	// 	controller.initData(37L); // 예시로 memberId 1을 사용
	//
	// 	primaryStage.setTitle("Coupon Viewer");
	// 	primaryStage.setScene(new Scene(root));
	// 	primaryStage.show();
	//
	// }


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

	// @Override
	// public void start(Stage primaryStage) throws Exception {
	// 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CreateReview.fxml"));
	// 	Parent root = loader.load();
	//
	// 	// CreateReviewController의 인스턴스 가져오기
	// 	CreateReviewController controller = loader.getController();
	//
	// 	// initData 메서드를 호출하여 memberId와 cafeId 초기화
	// 	controller.initData(9L, 2L); // 예시로 memberId 9와 cafeId 2 사용
	//
	// 	primaryStage.setTitle("Review Application");
	// 	primaryStage.setScene(new Scene(root));
	// 	primaryStage.show();
	// }

	public static void main(String[] args) throws MessagingException {
		CouponRepository couponRepository = new CouponRepository();
		MailService mailService = new MailService();

		CouponService couponService = new CouponService(couponRepository, mailService);
		Scheduler scheduler = new Scheduler();
		scheduler.execute(() -> {
			try {
				couponService.expiredCoupon();
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		});
		launch(args);
	}
}