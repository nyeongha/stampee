// package repository;
//
// import static config.DBConnectionUtil.*;
// import static org.assertj.core.api.Assertions.*;
//
// import java.security.NoSuchAlgorithmException;
// import java.sql.Connection;
// import java.sql.SQLException;
// import java.util.Base64;
//
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
//
// import domain.Cafe;
// import util.PasswordUtil;
//
// class EncryptTest {
// 	private CafeRepository cafeRepository;
// 	private Connection conn;
//
// 	@BeforeEach
// 	void setUp() throws SQLException {
// 		cafeRepository = new CafeRepository();
// 		conn = getConnection();
// 		conn.setAutoCommit(false);
//
// 	}
//
// 	@Test
// 	void testCafeSignUpAndLogin(){
// 		//given
// 		// 새로운 Cafe 객체 생성 (로그인용)
// 		Cafe loginCafe = new Cafe(11,"Test Cafe", "Test Streat"
// 			,"123411", "s11ys@example.com", "123-456-71199");
// 		//when
// 		cafeRepository.cafeSignUp(loginCafe);
//
// 		loginCafe.setEmail(loginCafe.getEmail());
// 		loginCafe.setPassword(loginCafe.getPassword());
//
// 		boolean loginResult = cafeRepository.login(loginCafe);
// 		System.out.println(loginCafe.getPassword());
//
// 		//then
// 		assertThat(loginResult).isTrue();
// 	}
//
// 	@AfterEach
// 	void tearDown() {
// 		// 객체 초기화
// 		if (conn != null) {
// 			try {
// 				conn.rollback();  // 롤백 수행
// 			} catch (SQLException e) {
// 				throw new RuntimeException(e);
// 			}
// 			try {
// 				conn.close();
// 			} catch (SQLException e) {
// 				throw new RuntimeException(e);
// 			}
// 		}
// 	}
//
// 	@Test
// 	void testSignup() throws NoSuchAlgorithmException {
// 		// Given
// 		String password = "testpassword";
//
// 		// When
// 		String hashedPassword = PasswordUtil.hashPassword(password);
//
// 		// Then
// 		assertThat(hashedPassword).isNotNull();
// 		assertThat(hashedPassword).contains(":");
// 		String[] parts = hashedPassword.split(":");
// 		assertThat(parts.length).isEqualTo(2);
//
// 		byte[] salt = Base64.getDecoder().decode(parts[0]);
// 		assertThat(salt).hasSize(8); // 8 bytes
//
// 		byte[] hash = Base64.getDecoder().decode(parts[1]);
// 		assertThat(hash).hasSize(20); // SHA-1 produces 20-byte hash
// 	}
//
// 	@Test
// 	void testGetSalt() {
// 		// When
// 		byte[] salt = PasswordUtil.createSalt();
//
// 		// Then
// 		assertThat(salt).isNotNull();
// 		assertThat(salt).hasSize(8); // 8 bytes
// 	}
//
// 	@Test
// 	void testMainMethod() throws NoSuchAlgorithmException {
// 		// Given
// 		String pwd = "tistory";
//
// 		// When
// 		String storedPassword = PasswordUtil.hashPassword(pwd);
//
// 		// Then
// 		assertThat(storedPassword).isNotNull();
// 		assertThat(storedPassword).contains(":");
// 		String[] parts = storedPassword.split(":");
// 		assertThat(parts.length).isEqualTo(2);
// 		String salt = parts[0];
// 		String hash = parts[1];
//
// 		assertThat(salt).isNotNull();
// 		assertThat(hash).isNotNull();
// 	}
//
// 	@Test
// 	void testEncryptPasswordFormat() throws NoSuchAlgorithmException {
// 		// Given
// 		String pwd = "testpassword";
//
// 		// When
// 		String storedPassword = PasswordUtil.hashPassword(pwd);
//
// 		// Then
// 		assertThat(storedPassword).isNotNull();
// 		assertThat(storedPassword).contains(":");
// 		String[] parts = storedPassword.split(":");
// 		assertThat(parts.length).isEqualTo(2);
// 		String salt = parts[0];
// 		String hash = parts[1];
//
// 		assertThat(salt).isNotNull();
// 		assertThat(hash).isNotNull();
//
// 		byte[] saltBytes = Base64.getDecoder().decode(salt);
// 		byte[] hashBytes = Base64.getDecoder().decode(hash);
//
// 		assertThat(saltBytes).hasSize(8); // 8 bytes
// 		assertThat(hashBytes).hasSize(20); // SHA-1 produces 20-byte hash
// 	}
//
// 	@Test
// 	void testPasswordVerification() throws NoSuchAlgorithmException {
// 		// Given
// 		String password = "testpassword";
// 		String storedPassword = PasswordUtil.hashPassword(password);
//
// 		// When
// 		boolean isPasswordCorrect = PasswordUtil.verifyPassword(password, storedPassword);
//
// 		// Then
// 		assertThat(isPasswordCorrect).isTrue();
// 	}
//
// 	@Test
// 	void testPasswordVerificationFailure() throws NoSuchAlgorithmException {
// 		// Given
// 		String password = "testpassword";
// 		String wrongPassword = "wrongpassword";
// 		String storedPassword = PasswordUtil.hashPassword(password);
//
// 		// When
// 		boolean isPasswordCorrect = PasswordUtil.verifyPassword(wrongPassword, storedPassword);
//
// 		// Then
// 		assertThat(isPasswordCorrect).isFalse();
// 	}
// }
