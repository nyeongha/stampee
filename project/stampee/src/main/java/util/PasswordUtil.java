package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

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
		byte[] salt = Base64.getDecoder().decode(parts[0]);
		byte[] saltedPassword = combineSaltAndPassword(salt, inputPassword);
		// 입력된 비밀번호를 같은 방식으로 해시
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] hashedInputPassword = md.digest(saltedPassword);
		// 저장된 해시된 비밀번호와 입력된 비밀번호를 비교
		return Base64.getEncoder().encodeToString(hashedInputPassword).equals(parts[1]);
	}

	// 간단한 콘솔 애플리케이션 로그인 로직
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner scanner = new Scanner(System.in);

		// 회원가입: 사용자 비밀번호 해시 생성 및 저장 (데이터베이스에 저장하는 과정 시뮬레이션)
		System.out.print("input password: ");
		String password = scanner.nextLine();
		String storedPassword = hashPassword(password);
		System.out.println("storedPassword: " + storedPassword);

		// 로그인: 사용자 비밀번호 입력 및 검증
		System.out.print("verify input password: ");
		String inputPassword = scanner.nextLine();
		boolean isPasswordCorrect = verifyPassword(inputPassword, storedPassword);

		if (isPasswordCorrect) {
			System.out.println("success login!");
		} else {
			System.out.println("fail login .. check password");
		}

		scanner.close();
	}
}

