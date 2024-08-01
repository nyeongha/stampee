package formatter;

import lombok.Getter;

@Getter
public enum MailMessageFormatter {
	SEND_STAMP_MESSAGE("스탬프 %d개가 %s님에게 공유 되었습니다."),
	RECEIVE_STAMP_MESSAGE("%s님으로 부터 %d개를 공유 받았습니다."),
	FAIL_SEND_STAMP_MESSAGE("스탬프 공유에 실패했습니다."),
	SHARE_STAMP_TITLE("카페 스탬프 공유 안내"),
	EXPIRED_COUPON("카페 쿠폰 만료 예정 안내");

	private final String message;

	MailMessageFormatter(String message) {
		this.message = message;
	}
}
