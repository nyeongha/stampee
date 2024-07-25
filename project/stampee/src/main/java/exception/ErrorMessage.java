package exception;

public enum ErrorMessage {
	NOT_FOUND_MEMBER("해당 전화번호로 가입된 회원이 존재하지 않습니다.");
	private final String ErrorMessage;

	ErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}
}
