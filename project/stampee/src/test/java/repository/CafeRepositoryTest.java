package repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import util.Encrypt;

class EncryptTest {

	@Test
	void testGetSalt() {
		// When
		String salt = Encrypt.getSalt();

		// Then
		assertThat(salt).isNotNull();
		assertThat(salt).hasSize(16); // 8 bytes in hex representation (2 characters per byte)
		assertThat(salt).matches("[0-9a-f]{16}");
	}

	@Test
	void testMainMethod() {
		// Given
		String pwd = "tistory";

		// When
		String salt = Encrypt.getSalt();
		String encryptedPasswordWithSalt = pwd + ":" + salt;

		// Then
		assertThat(encryptedPasswordWithSalt).isEqualTo(pwd + ":" + salt);
	}

	@Test
	void testEncryptPasswordFormat() {
		// Given
		String pwd = "testpassword";
		String salt = Encrypt.getSalt();

		// When
		String encryptedPasswordWithSalt = pwd + ":" + salt;

		// Then
		assertThat(encryptedPasswordWithSalt).isNotNull();
		assertThat(encryptedPasswordWithSalt).contains(":");
		String[] parts = encryptedPasswordWithSalt.split(":");
		assertThat(parts.length).isEqualTo(2);
		assertThat(parts[0]).isEqualTo(pwd);
		assertThat(parts[1]).isEqualTo(salt);
	}
}
