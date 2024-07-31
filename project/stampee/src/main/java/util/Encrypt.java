package util;

import java.security.SecureRandom;

public class Encrypt {
	public static String getSalt() {
		SecureRandom r = new SecureRandom();
		byte[] salt = new byte[8];
		r.nextBytes(salt);
		StringBuffer sb = new StringBuffer();
		for (byte b : salt) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public static String getEncrytedPassword(String password) {
		return password + ":" + getSalt();
	}
}
