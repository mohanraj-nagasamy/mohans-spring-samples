package com.test.integraion.package2;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingServiceImpl implements GreetingService {

	@Autowired()
	@Qualifier("stubMessageRepository2")
	private MessageRepository messageRepository;

	public String greet(String name) {
		Locale locale = Locale.getDefault();
		if (messageRepository == null) {
			return "Sorry, no messages";
		}
		String message = messageRepository.getMessage(locale.getDisplayLanguage());
		return message + " " + name;
	}
}