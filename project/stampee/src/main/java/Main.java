import javax.mail.MessagingException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import repository.CouponRepository;
import service.CouponService;
import service.MailService;
import service.Scheduler;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/account/loginPageMain.fxml"));

			Scene scene = new Scene(root, 600, 800);

			primaryStage.setScene(scene);

			Image icon = new Image(getClass().getResourceAsStream("/image/github_logo.png"));
			primaryStage.getIcons().add(icon);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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