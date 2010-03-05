package com.test.integraion.package2.client;

import java.util.Locale;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

import com.test.integraion.package2.GreetingService;

public class ThreeDiffAnnotationsClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.GERMAN);
		GenericApplicationContext context = new GenericApplicationContext();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.scan("com"); // the parameter is 'basePackage'

		context.refresh();

		GreetingService greetingService = (GreetingService) context.getBean("greetingServiceImpl");
		String message = greetingService.greet("Standalone Beans");

		System.out.println(message);

	}

}
