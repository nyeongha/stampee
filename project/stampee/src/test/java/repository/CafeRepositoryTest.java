package repository;

import static org.assertj.core.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import domain.Cafe;
import domain.Signature;
import repository.CafeRepository;
import service.CafeService;
import util.PasswordUtil;

class CafeRepositoryTest {

	private CafeRepository cafeRepository;
	private Random random;
	private CafeService cafeService;

	@BeforeEach
	void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		cafeRepository = new CafeRepository();
		random = new Random();
		cafeService = new CafeService(cafeRepository);
	}

	private String generateRandomEmail() {
		return "user" + random.nextInt(10000) + "@example.com";
	}

	private String generateRandomPhone() {
		return String.format("%03d-%03d-%04d",
			random.nextInt(1000),
			random.nextInt(1000),
			random.nextInt(10000));
	}

	@Test
	void testCafeSignUpAndLogin() throws SQLException, NoSuchAlgorithmException {
		// given
		String randomEmail = generateRandomEmail();
		String randomPhone = generateRandomPhone();

		Cafe loginCafe = new Cafe(2, "Test Cafe", "Test Street", "123411", randomEmail, randomPhone);

		// when
		long cafeId = cafeRepository.cafeSignUp(loginCafe, "Test Menu 1", "Test Menu 2");
		loginCafe.setCafeId(cafeId);  // 생성된 cafeId 설정
		boolean loginResult = cafeRepository.login(loginCafe);

		// then
		assertThat(loginResult).isTrue();
	}

	@Test
	void testCafeServiceSignUpAndLogin() {
		// given
		String randomEmail = generateRandomEmail();
		String randomPhone = generateRandomPhone();
		Cafe cafe = new Cafe(2, "ServiceTest Cafe", "Test Street", "123411", randomEmail, randomPhone);

		// when
		cafeService.cafeSignUp(cafe, "ServiceTest Menu 1", "ServiceTest Menu 2");
		boolean loginResult = cafeService.login(cafe);

		// then
		assertThat(loginResult).isTrue();
	}

	@Test
	void testCafeServiceLoginWithWrongPassword() {
		// given
		String randomEmail = generateRandomEmail();
		String randomPhone = generateRandomPhone();
		Cafe cafe = new Cafe(2, "Wrong Password Cafe", "Wrong Password Street", "correctpass", randomEmail, randomPhone);

		// when
		cafeService.cafeSignUp(cafe, "Wrong Password Menu 1", "Wrong Password Menu 2");

		cafe.setPassword("correctpass1");
		boolean loginResult = cafeService.login(cafe);

		// then
		assertThat(loginResult).isFalse();
	}
}


class PasswordUtilTest {

	@Test
	void testSignup() throws NoSuchAlgorithmException {
		// Given
		String password = "testpassword";

		// When
		String hashedPassword = PasswordUtil.hashPassword(password);

		// Then
		assertThat(hashedPassword).isNotNull();
		assertThat(hashedPassword).contains(":");
		String[] parts = hashedPassword.split(":");
		assertThat(parts.length).isEqualTo(2);

		byte[] salt = Base64.getDecoder().decode(parts[0]);
		assertThat(salt).hasSize(8); // 8 bytes

		byte[] hash = Base64.getDecoder().decode(parts[1]);
		assertThat(hash).hasSize(20); // SHA-1 produces 20-byte hash
	}

	@Test
	void testGetSalt() {
		// When
		byte[] salt = PasswordUtil.createSalt();

		// Then
		assertThat(salt).isNotNull();
		assertThat(salt).hasSize(8); // 8 bytes
	}

	@Test
	void testMainMethod() throws NoSuchAlgorithmException {
		// Given
		String pwd = "tistory";

		// When
		String storedPassword = PasswordUtil.hashPassword(pwd);

		// Then
		assertThat(storedPassword).isNotNull();
		assertThat(storedPassword).contains(":");
		String[] parts = storedPassword.split(":");
		assertThat(parts.length).isEqualTo(2);
		String salt = parts[0];
		String hash = parts[1];

		assertThat(salt).isNotNull();
		assertThat(hash).isNotNull();
	}

	@Test
	void testEncryptPasswordFormat() throws NoSuchAlgorithmException {
		// Given
		String pwd = "testpassword";

		// When
		String storedPassword = PasswordUtil.hashPassword(pwd);

		// Then
		assertThat(storedPassword).isNotNull();
		assertThat(storedPassword).contains(":");
		String[] parts = storedPassword.split(":");
		assertThat(parts.length).isEqualTo(2);
		String salt = parts[0];
		String hash = parts[1];

		assertThat(salt).isNotNull();
		assertThat(hash).isNotNull();

		byte[] saltBytes = Base64.getDecoder().decode(salt);
		byte[] hashBytes = Base64.getDecoder().decode(hash);

		assertThat(saltBytes).hasSize(8); // 8 bytes
		assertThat(hashBytes).hasSize(20); // SHA-1 produces 20-byte hash
	}

	@Test
	void testPasswordVerification() throws NoSuchAlgorithmException {
		// Given
		String password = "testpassword";
		String storedPassword = PasswordUtil.hashPassword(password);

		// When
		boolean isPasswordCorrect = PasswordUtil.verifyPassword(password, storedPassword);

		// Then
		assertThat(isPasswordCorrect).isTrue();
	}

	@Test
	void testPasswordVerificationFailure() throws NoSuchAlgorithmException {
		// Given
		String password = "testpassword";
		String wrongPassword = "wrongpassword";
		String storedPassword = PasswordUtil.hashPassword(password);

		// When
		boolean isPasswordCorrect = PasswordUtil.verifyPassword(wrongPassword, storedPassword);

		// Then
		assertThat(isPasswordCorrect).isFalse();
	}
}
