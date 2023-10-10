package com.cs7530.ChatApp.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs7530.ChatApp.service.DecryptionService;
import com.cs7530.ChatApp.service.EncryptionService;

@RestController
public class ChatMessageController {

	private final EncryptionService encryptionService;
	private final DecryptionService decryptionService;

	public ChatMessageController(EncryptionService encryptionService, DecryptionService decryptionService) {
		this.encryptionService = encryptionService;
		this.decryptionService = decryptionService;
	}

	@PostMapping(value = "/send-message")
	public String sendAndEncrptMessage(@RequestParam String message) {
		return encryptionService.encrypt(message);
	}

	@PostMapping(value = "/receive-message")
	public String receivedAndDecrptMessage(@RequestParam String message) {
		return decryptionService.decrypt(message);
	}

}
