package formatter;

public class PhoneNumberFormatter {
	public static String formatPhoneNumber(String phoneNumber) {
		if (phoneNumber == null || phoneNumber.length() != 11) {
			throw new IllegalArgumentException("Invalid phone number length.");
		}

		return phoneNumber.replaceFirst("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
	}
}
