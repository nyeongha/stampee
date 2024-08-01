package controller;

import static domain.ReviewType.*;

import java.io.IOException;
import domain.ReviewType;
import dto.response.LoggedMemberDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import session.CafeSession;
import javafx.stage.Stage;
import session.MemberSession;
import util.Popup;
import util.SceneNavigator;

public class HeaderController {
	private static final long ALL_MEMBER = 0L;

	@FXML public Label logoutLabel;
	@FXML public Label loginLabel;

	@FXML
	public void handleMyReview(MouseEvent event) {
		LoggedMemberDto member = MemberSession.getInstance().getLoggedMemberDto();
		navigateTo("/fxml/reviews.fxml", event, MEMBER, member.getMemberId());
	}

	@FXML
	public void handleReviews(MouseEvent event) {
		navigateTo("/fxml/reviews.fxml", event, ALL, ALL_MEMBER);
	}

	@FXML
	public void handleMyCoupon(MouseEvent event) throws IOException {
		SceneNavigator.getInstance().navigateTo("/fxml/CouponPage.fxml", event);
	}

	public void handleLogo(MouseEvent event) throws IOException {
		SceneNavigator.getInstance().navigateTo("/fxml/index/MemberMain.fxml", event);
	}

	private void navigateTo(String fxmlPath, MouseEvent event, ReviewType reviewType, long id) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
			Parent root = loader.load();
			ReviewController controller = loader.getController();
			controller.init(id, reviewType);

			Stage stage = (Stage)((Label)event.getSource()).getScene().getWindow();

			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void handleLogin(MouseEvent mouseEvent) throws IOException {
		SceneNavigator.getInstance().navigateTo("/fxml/account/LoginPageMain.fxml", mouseEvent);
	}

	@FXML
	public void handleLogout(MouseEvent mouseEvent) throws IOException {

		if(CafeSession.getInstance() != null){
			CafeSession.clearSession();
		}
		if(MemberSession.getInstance() != null){
			MemberSession.clearSession();
		}

		Popup.showSuccessPopup("로그아웃 되었습니다.");
		SceneNavigator.getInstance().navigateTo("/fxml/account/LoginPageMain.fxml", mouseEvent);
	}

	private void updateHeaderVisibility() {
		// 로그아웃 라벨만 숨김
		if (logoutLabel != null) {
			logoutLabel.setVisible(false);
		}
	}
}
