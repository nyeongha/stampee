package exception;

public enum ErrorMessage {
	NOT_FOUND_MEMBER("해당 전화번호로 가입된 회원이 존재하지 않습니다."),
	NOT_FOUND_CONFIG("설정 파일을 찾을 수 없습니다."),
	NOT_FOUND_PROPERTIES("해당 속성이 존재하지 않습니다."),
	FAILED_TO_INITIALIZE("구성이 제대로 초기화되지 않았습니다.");

	private final String ErrorMessage;

	ErrorMessage(String errorMessage) {
		this.ErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}
}
