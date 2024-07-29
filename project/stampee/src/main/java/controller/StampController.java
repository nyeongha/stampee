package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.response.MyStampDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import repository.MemberRepository;
import repository.StampRepository;
import service.MailService;
import service.StampService;

public class StampController implements Initializable {
	private static StampService stampService;
	private static MailService mailService = new MailService();
	private static MemberRepository memberRepository = new MemberRepository();
	private static StampRepository stampRepository = new StampRepository();

	@FXML private ImageView stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9, stamp10;
	@FXML Label cafeName, cafeAddress;
	@FXML private Pane mapContainer;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			Pane mapPane = FXMLLoader.load(getClass().getResource("/MapOutput.fxml"));
			mapContainer.getChildren().add(mapPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initData(long memberId, long cafeId){
		stampService = new StampService(stampRepository, memberRepository, mailService);
		MyStampDto myStamp = stampService.findMyStamp(memberId, cafeId);

		setStampImage(myStamp.getCount());

		cafeName.setText(myStamp.getCafeName());
		cafeAddress.setText(myStamp.getCafeAddr());
	}

	private void setStampImage(int count) {
		Image filledStamp = new Image(getClass().getResourceAsStream("/github_logo.png"));
		Image emptyStamp = new Image(getClass().getResourceAsStream("/java_logo.png"));

		ImageView[] stamps = {stamp1, stamp2, stamp3, stamp4, stamp5, stamp6, stamp7, stamp8, stamp9, stamp10 };

		for (int i = 0; i < stamps.length; i++) {
			if (i < count) {
				stamps[i].setImage(filledStamp);
			} else {
				stamps[i].setImage(emptyStamp);
			}
		}
	}
}
