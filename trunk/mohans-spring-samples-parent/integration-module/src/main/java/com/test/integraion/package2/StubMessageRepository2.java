package com.test.integraion.package2;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class StubMessageRepository2 implements MessageRepository {

	Map<String, String> messages = new HashMap<String, String>();

	@PostConstruct
	public void initialize() {
		System.out.println("StubMessageRepository2.initialize()");
		messages.put("English", "Welcome");
		messages.put("Deutsch", "Willkommen");
	}

	public String getMessage(String language) {
		System.out.println("StubMessageRepository2.getMessage()");
		return messages.get(language);
	}
}