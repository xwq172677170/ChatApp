package com.cs7530.ChatApp.service;

import static com.cs7530.ChatApp.crypto.CryptoUtil.ALGORITHM_NAME;
import static com.cs7530.ChatApp.crypto.CryptoUtil.ALGORITHM_TYPE;
import static com.cs7530.ChatApp.crypto.CryptoUtil.ITERATION_COUNT;
import static com.cs7530.ChatApp.crypto.CryptoUtil.IV;
import static com.cs7530.ChatApp.crypto.CryptoUtil.KEY_LENGTH;
import static com.cs7530.ChatApp.crypto.CryptoUtil.SALT;
import static com.cs7530.ChatApp.crypto.CryptoUtil.SECRET_KEY;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AesEncrptionService implements EncryptionService {

	private Logger logger = LoggerFactory.getLogger(AesEncrptionService.class);

	@Override
	public String encrypt(String strToEncrypt) {

		long start = System.currentTimeMillis();

		logger.info("start encrption");
		logger.info("strToEncrypt {}", strToEncrypt);
		try {
			IvParameterSpec ivspec = new IvParameterSpec(IV);

			SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);

			KeySpec spec = new PBEKeySpec(SECRET_KEY, SALT, ITERATION_COUNT, KEY_LENGTH);

			SecretKey secretKey = factory.generateSecret(spec);

			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM_TYPE);

			Cipher cipher = Cipher.getInstance("AES", new BouncyCastleProvider());

			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

			long finish = System.currentTimeMillis();
			long timeElapsed = finish - start;

			logger.info("complete encrption, took : {} milliseconds ", timeElapsed);

			String out = Base64.getEncoder()
					.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));

			logger.info("Encrypted string {}", out);
			return out;
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
}
