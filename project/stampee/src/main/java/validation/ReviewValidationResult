package validation;

public enum ReviewValidationResult {
	VALID(""),
	NO_RATING("Please select a rating."),
	EMPTY_CONTENTS("Review contents cannot be empty."),
	INVALID_MEMBER_OR_CAFE("Invalid member or cafe information.");

	private final String message;

	ReviewValidationResult(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
