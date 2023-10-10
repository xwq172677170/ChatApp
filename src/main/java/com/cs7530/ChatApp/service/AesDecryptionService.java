package com.cs7530.ChatApp.service;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AesDecryptionService implements DecryptionService {

	private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";

	private static final String SALT = "ssshhhhhhhhhhh!!!!";

	Logger logger = LoggerFactory.getLogger(AesDecryptionService.class);

	@Override
	public String decrypt(String strToDecrypt) {
		long start = System.currentTimeMillis();
		logger.info("start decrption");

		logger.info("strToDecrypt {}", strToDecrypt);
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;
			logger.info("complete encrption, took : {} milliseconds ", timeElapsed);
			String out = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			logger.info("Decrypted string {}", out);
			return out;
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}
