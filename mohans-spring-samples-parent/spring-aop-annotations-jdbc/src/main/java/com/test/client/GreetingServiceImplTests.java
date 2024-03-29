package com.test.client;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.test.aop.blog.GreetingService;

@ContextConfiguration(locations = "/blog/applicationContext.xml")
public class GreetingServiceImplTests extends AbstractJUnit38SpringContextTests {

	@Autowired
	private GreetingService greetingService;

	public void setGreetingService(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	public void testEnglishWelcome() {
		Locale.setDefault(Locale.ENGLISH);
		String name = "Spring Community";
		String greeting = greetingService.greet(name);
		assertEquals("Welcome " + name, greeting);
	}

	public void testGermanWelcome() {
		Locale.setDefault(Locale.GERMAN);
		String name = "Spring Community";
		String greeting = greetingService.greet(name);
		assertEquals("Willkommen " + name, greeting);
	}
}
