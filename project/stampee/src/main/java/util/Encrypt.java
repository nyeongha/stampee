package util;

import java.security.SecureRandom;

public class Encrypt {

	// public static void main(String[] args) {
	// 	String pwd = "tistory";
	// 	System.out.println("pwd : " + pwd);
	//
	// 	// Salt
	// 	String salt = getSalt();
	// 	System.out.println("salt : " + salt);
	//
	// 	// Encrypted password with salt
	// 	String encryptedPassword = pwd+":"+salt;
	// 	System.out.println("Encrypted password with salt : " + encryptedPassword);
	// }

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

	public static String getEncrytedPassword(String password){

		return password +":"+ getSalt();

	}

	// public static String getEncrypt(String password){
	// 	String result = "";
	// 	try {
	// 		MessageDigest md = MessageDigest.getInstance("SHA-256");
	// 		System.out.println("before apply password+salt : "+ password + getSalt() );
	// 		md.update((password + getSalt()).getBytes());
	//
	// 	} catch (NoSuchAlgorithmException e) {
	// 		throw new RuntimeException(e);
	// 	}
	//
	// }


}
