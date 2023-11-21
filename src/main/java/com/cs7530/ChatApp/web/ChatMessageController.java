package com.cs7530.ChatApp.web;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs7530.ChatApp.service.DecryptionService;
import com.cs7530.ChatApp.service.EncryptionService;

@RestController
public class ChatMessageController {
	private Logger logger = LoggerFactory.getLogger(ChatMessageController.class);

	private final EncryptionService encryptionService;
	private final DecryptionService decryptionService;

	public ChatMessageController(EncryptionService encryptionService, DecryptionService decryptionService) {
		this.encryptionService = encryptionService;
		this.decryptionService = decryptionService;
	}
	
	@PostMapping(value = "/send-message")
	public String sendAndEncrptMessage(@RequestParam String message) {
		logger.info("Encrpt message: " + message);
		String ciperText = encryptionService.encrypt(message);
		logger.info("Encrpted message: " + ciperText);
		logger.info("Encoded message: " + URLEncoder.encode(ciperText));
		return URLEncoder.encode(ciperText);
	}

	@PostMapping(value = "/receive-message")
	public String receivedAndDecrptMessage(@RequestParam String message) {
		logger.info("Decrpt message: " + message);
		logger.info("Decoded message: " + message);
		String plainText = decryptionService.decrypt(message);
		logger.info("Decrpted message: " + plainText);
		return plainText;
	}

}
