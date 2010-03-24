package com.test.aop.autoproxy.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.test.aop.autoproxy.HelloService;

@ContextConfiguration("classpath:autoproxy/config.xml")
public class ObscenityFilterTests extends AbstractJUnit38SpringContextTests {

	@Autowired
	private HelloService helloService;

	public void setHelloMessageService(HelloService helloService) {
		this.helloService = helloService;
	}

	public void testWithObscenity() {
		try {
			helloService.getHelloMessage("Microsoft");
			fail("Should have thrown an exception");
		} catch (IllegalArgumentException ex) {
		}
	}

	public void testWithoutObscenity() {
		assertEquals("Incorrect message returned", "Hello Seattle!", helloService.getHelloMessage("Seattle"));
	}

}
