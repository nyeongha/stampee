import controller.StampController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		// FXMLLoader 생성
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/stamp.fxml"));

		// 루트 요소 로드
		Parent root = loader.load();
		StampController stampController = loader.getController();
		stampController.initData(38L, 1L);

		// 스테이지 설정
		primaryStage.setTitle("스탬프");
		primaryStage.setScene(new Scene(root, 600, 800));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}