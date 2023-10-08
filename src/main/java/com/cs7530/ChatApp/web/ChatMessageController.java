package com.cs7530.ChatApp.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs7530.ChatApp.crpto.DecryptionUtil;
import com.cs7530.ChatApp.crpto.EncrptionUtil;

@RestController
public class ChatMessageController {

	@PostMapping(value = "/send-message")
	public String sendAndEncrptMessage(@RequestParam String message) {
		return EncrptionUtil.encrypt(message);
	}

	@PostMapping(value = "/receive-message")
	public String receivedAndDecrptMessage(@RequestParam String message) {
		return DecryptionUtil.decrypt(message);
	}

}
