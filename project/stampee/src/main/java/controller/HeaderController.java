package controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HeaderController {

	// private PageController pageController;
	@FXML
	private void handleMyReviewClick(MouseEvent event) {
		loadPage("/fxml/MyReviewPage.fxml", "MyReviewController", 37L, event); // event 추가
	}

	@FXML
	private void handleReviewsClick(MouseEvent event) {
		loadPage("/fxml/ReviewsPage.fxml", "ReviewsController", null, event); // event 추가
	}

	@FXML
	private void handleCouponClick(MouseEvent event) {
		loadPage("/fxml/CouponPage.fxml", "CouponController", 37L, event); // event 추가
	}

	@FXML
	private void handleContactsClick(MouseEvent event) {
		loadPage("/fxml/ContactsPage.fxml", "ContactsController", null, event); // event 추가
	}

	@FXML
	private void handleEditClick(MouseEvent event) {
		loadPage("/fxml/EditPage.fxml", "EditController", 37L, event); // event 추가
	}

	private void loadPage(String fxmlPath, String controllerName, Object param, MouseEvent event) {
		try {
			FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/BaseLayout.fxml"));
			Parent baseRoot = baseLoader.load();

			FXMLLoader contentLoader = new FXMLLoader(getClass().getResource(fxmlPath));
			Node content = contentLoader.load();

			// 컨트롤러에 데이터 전달
			Object controller = contentLoader.getController();
			Method initDataMethod = controller.getClass().getMethod("initData", Object.class);
			initDataMethod.invoke(controller, param);

			// BaseLayout의 contentArea에 새 내용 설정
			VBox contentArea = (VBox)baseRoot.lookup("#contentArea");
			contentArea.getChildren().setAll(content);

			// 현재 스테이지 가져오기
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

			// 새 씬 설정
			Scene scene = new Scene(baseRoot, 800, 600);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
