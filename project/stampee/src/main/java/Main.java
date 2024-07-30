// import controller.CouponController;
//
// import controller.StampController;
// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
//
//
// public class Main extends Application {
// 	public void start(Stage primaryStage) throws Exception {
// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/stamp.fxml"));
// 		Parent root = loader.load();
// 		StampController controller = loader.getController();
// 		controller.initData(39L, 1L); // 예시로 memberId 1을 사용
// 		primaryStage.setTitle("Stamp Viewer");
// 		primaryStage.setScene(new Scene(root));
// 		try {
// 			InputStream is = getClass().getResourceAsStream("/font/Jua-Regular.ttf");
// 			if (is != null) {
// 				Font font = Font.loadFont(is, 12);
// 				if (font != null) {
// 					System.out.println("Font loaded: " + font.getName());
// 				} else {
// 					System.out.println("Failed to load font");
// 				}
// 			} else {
// 				System.out.println("Font file not found");
// 			}
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
//
// 		// FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
// 		// Parent root = loader.load();
// 		//
// 		// CouponController controller = loader.getController();
// 		// controller.initData(37L); // 예시로 memberId 1을 사용
// 		//
// 		// primaryStage.setTitle("Coupon Viewer");
// 		// primaryStage.setScene(new Scene(root));
// 		// primaryStage.show();
//
// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/account/LoginPage.fxml"));
// 		Parent root = loader.load();
// 		Scene scene = new Scene(root);
// 		primaryStage.setTitle("Review Block Example");
// 		primaryStage.setScene(scene);
// 		primaryStage.show();
//
// 	}
// 	public static void main(String[] args) {
// 		launch(args);
// 	}
// 	// public void start(Stage primaryStage) throws Exception {
// 	// 	try {
// 	// 		// FXML 파일 경로를 설정합니다.
// 	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CouponPage.fxml"));
// 	//
// 	// 		// FXML 파일을 로드하고 루트 레이아웃을 가져옵니다.
// 	// 		Parent root = loader.load();
// 	//
// 	// 		// Scene을 설정하고 Stage에 추가합니다.
// 	// 		Scene scene = new Scene(root);
// 	// 		primaryStage.setScene(scene);
// 	// 		primaryStage.setTitle("Coupon Application");
// 	// 		primaryStage.show();
// 	// 	} catch(Exception e) {
// 	// 		e.printStackTrace();
// 	// 	}
// 	// }
// 	//
// 	// public static void main(String[] args) {
// 	// 	launch(args);
// 	// }
// 	//
// 	// @Override
// 	// public void start(Stage primaryStage) {
// 	// 	try {
// 	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reviewListView.fxml"));
// 	// 		Parent root = loader.load();
// 	// 		Scene scene = new Scene(root);
// 	// 		primaryStage.setTitle("Review Block Example");
// 	// 		primaryStage.setScene(scene);
// 	// 		primaryStage.show();
// 	// 	} catch (Exception e) {
// 	// 		e.printStackTrace();
// 	// 	}
// 	// }
// 	//
// 	// public static void main(String[] args) {
// 	// 	launch(args);
// 	// }
//
// 	// @Override
// 	// public void start(Stage primaryStage) {
// 	// 	try {
// 	// 		FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/reusables/Header.fxml"));
// 	// 		BorderPane root = loader.load();
// 	//
// 	// 		Scene scene = new Scene(root, 600, 800);
// 	// 		primaryStage.setTitle("Header Test Application");
// 	// 		primaryStage.setScene(scene);
// 	// 		primaryStage.show();
// 	// 	} catch (IOException e) {
// 	// 		e.printStackTrace();
// 	// 	}
// 	// }
// 	//
// 	// public static void main(String[] args) {
// 	// 	launch(args);
// 	// }
// }
//
