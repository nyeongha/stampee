package controller;

import java.util.List;

import dto.response.MyCouponDto;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import repository.CouponRepository;
import repository.MemberRepository;
import repository.StampRepository;
import service.CouponService;
import service.MailService;
import service.StampService;
import view.CouponView;

public class CouponController{

	private static CouponService couponService;
	private static StampService stampService;
	private final CouponView couponView = new CouponView();

	@FXML
	private VBox cafeListContainer;

	public CouponController() {
		CouponRepository couponRepository = new CouponRepository();
		StampRepository stampRepository = new StampRepository();
		MemberRepository memberRepository = new MemberRepository();
		MailService mailService = new MailService();
		couponService = new CouponService(couponRepository, mailService);
		stampService = new StampService(stampRepository, memberRepository, mailService);
	}

	public void initData(long memberId) {
		List<MyCouponDto> myCoupons = couponService.getMyCoupon(memberId);
		for (MyCouponDto myCoupon : myCoupons) {
			addCafeItem(myCoupon.getCafeName(), myCoupon.getAddress(), myCoupon.getCount(),
				stampService.findMyStamp(memberId, myCoupon.getCafeId()).getCount(),
				couponService.getExpiringCouponCount(memberId, myCoupon.getCafeId()));
		}
	}

	private void addCafeItem(String name, String address, int couponCount, int stampCount, int expiredCount) {
		HBox cafeItem = couponView.createCafeItemHBox();
		VBox infoBox = couponView.createInfoBox(name, address);
		VBox couponBox = couponView.createCouponBox(couponCount, expiredCount);
		HBox stampAndButtonBox = couponView.createStampAndButtonBox(stampCount);

		cafeItem.getChildren().addAll(infoBox, couponBox, stampAndButtonBox);
		cafeListContainer.getChildren().add(cafeItem);
	}
}
