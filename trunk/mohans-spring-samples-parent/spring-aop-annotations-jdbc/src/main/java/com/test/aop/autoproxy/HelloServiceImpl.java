package com.test.aop.autoproxy;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl implements HelloService {

	public String getHelloMessage(String toAddHello) {
		return "Hello " + toAddHello + "!";
	}

}
