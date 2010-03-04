package com.test.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.test.spring.beans.TestBean;

@Component
public class TestConfig {

	@Autowired
	private ApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("TestConfig.main()");
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "config.xml" });

		Object str = context.getBean("test");
		TestBean testBean = (TestBean) context.getBean("testBean");
		System.out.println("str --> " + str);
		System.out.println("testBean --> " + testBean);
		System.out.println("testBean ID --> " + testBean.getId());
		System.out.println("testBean Name --> " + testBean.getName());
		System.out.println("testBean test anno --> " + testBean.getTestAnno());
		System.out.println("APP COntext--> " + testBean.getTestAnno().getContext());

		TestConfig testConfig = new TestConfig();
		System.out.println(testConfig.getContext());

		context.destroy();
	}

	public ApplicationContext getContext() {
		return context;
	}

}
