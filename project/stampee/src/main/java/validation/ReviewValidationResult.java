package validation;

public enum ReviewValidationResult {
	VALID(""),
	NO_RATING("별점을 선택해주세요."),
	EMPTY_CONTENTS("내용이 비었습니다."),
	INVALID_MEMBER_OR_CAFE("유효하지않은 멤버 또는 카페 정보입니다.");

	private final String message;

	ReviewValidationResult(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
