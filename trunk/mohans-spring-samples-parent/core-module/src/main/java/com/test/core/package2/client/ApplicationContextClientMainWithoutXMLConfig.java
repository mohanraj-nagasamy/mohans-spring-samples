package com.test.core.package2.client;

import java.util.Locale;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;

public class ApplicationContextClientMainWithoutXMLConfig {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.GERMAN);
		GenericApplicationContext context = new GenericApplicationContext();

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
		scanner.scan("com"); // the parameter is 'basePackage'

		context.refresh();

		Object str = context.getBean("test");
		System.out.println("str --> " + str);

	}

}
