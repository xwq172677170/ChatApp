package com.cs7530.ChatApp.crypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CryptoUtil {

	/**
	 * AES Configuration Block Size 128 Key 128, 192, 256
	 */

	public static final char[] SECRET_KEY = "CS7530_SEC_K".toCharArray();

	public static final byte[] SALT = "CS7530_SALT".getBytes();

	// IV - Initialization Vector
	// IV is a pseudo-random value and has the same size as the block that is
	// encrypted. We can use the SecureRandom class to generate a random IV.
	public static final byte[] IV = { 1, 25, 125, 10, 35, 65, 95, 105, 75, 35, 5, 15, 90, 25, 115, 10 };

	public static final Integer ITERATION_COUNT = 65536;

	public static final Integer KEY_LENGTH = 256; // 128, 192, 256

	public static final String ALGORITHM_TYPE = "AES";

	public static final String ALGORITHM_NAME = "PBKDF2WithHmacSHA256";

	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(n);
		SecretKey key = keyGenerator.generateKey();
		return key;
	}
}
