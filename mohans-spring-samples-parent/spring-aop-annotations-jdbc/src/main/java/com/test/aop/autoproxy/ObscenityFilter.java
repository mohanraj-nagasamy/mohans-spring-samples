package com.test.aop.autoproxy;

import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ObscenityFilter {

	private Set<String> obscenities;

	public ObscenityFilter(Set<String> obscenities) {
		this.obscenities = obscenities;
	}

	@Before("helloService(input)")
	public void filter(String input) {
		if (obscenities.contains(input)) {
			throw new IllegalArgumentException("Obscenities such as '" + input + "' will not be tolerated!");
		}
	}

	@Pointcut("execution(public java.lang.String com.test.aop.autoproxy.HelloService+.getHelloMessage(String)) && args(helloInput)")
	public void helloService(String helloInput) {
	}
}
