package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

	// 비밀번호를 해시하여 반환하는 함수
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		byte[] salt = createSalt();
		byte[] saltedPassword = combineSaltAndPassword(salt, password);
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashedPassword = md.digest(saltedPassword);
		return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
	}

	// 솔트를 생성하는 함수
	public static byte[] createSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[8]; // 8바이트 솔트 생성
		sr.nextBytes(salt);
		return salt;
	}

	// 솔트와 비밀번호를 결합하는 함수
	private static byte[] combineSaltAndPassword(byte[] salt, String password) {
		byte[] passwordBytes = password.getBytes();
		byte[] saltedPassword = new byte[salt.length + passwordBytes.length];
		System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
		System.arraycopy(passwordBytes, 0, saltedPassword, salt.length, passwordBytes.length);
		return saltedPassword;
	}

	// 입력된 비밀번호를 검증하는 함수
	public static boolean verifyPassword(String inputPassword, String storedPassword) throws NoSuchAlgorithmException {
		// 저장된 비밀번호에서 솔트와 해시된 비밀번호를 분리
		String[] parts = storedPassword.split(":");

		// parts 배열의 길이를 확인하여 올바른 형식인지 검증
		if (parts.length != 2) {
			// 예외 처리 또는 로그 추가
			System.out.println("저장된 비밀번호의 형식이 올바르지 않습니다.");
			return false;
		}

		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] saltedPassword = combineSaltAndPassword(salt, inputPassword);

		// 입력된 비밀번호를 같은 방식으로 해시
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashedInputPassword = md.digest(saltedPassword);

		// 저장된 해시된 비밀번호와 입력된 비밀번호를 비교
		return Base64.getEncoder().encodeToString(hashedInputPassword).equals(parts[1]);
	}

}

