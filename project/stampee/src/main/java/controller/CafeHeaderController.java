package controller;

import static util.Popup.*;
import static util.SceneNavigator.*;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import session.CafeSession;
import session.MemberSession;

public class CafeHeaderController {
	@FXML public Label logoutLabel;
	@FXML public Label loginLabel;

	@FXML
	public void handleLogo(MouseEvent event) throws IOException {
		getInstance().navigateTo("/fxml/index/memberMainPage.fxml", event);
	}

	@FXML
	public void handleLogin(MouseEvent mouseEvent) throws IOException {
		getInstance().navigateTo("/fxml/account/loginPageMain.fxml", mouseEvent);
	}

	@FXML
	public void handleLogout(MouseEvent mouseEvent) throws IOException {

		if(CafeSession.getInstance() !=null){
			CafeSession.clearSession();
		}else if(MemberSession.getInstance() !=null){
			MemberSession.clearSession();
		}
		showSuccessPopup("로그아웃 되었습니다.");
		getInstance().navigateTo("/fxml/account/loginPageMain.fxml", mouseEvent);
	}

	@FXML
	private void goToKeypadView() {
		try {
			getInstance().navigateTo("/fxml/KeypadView.fxml", loginLabel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
