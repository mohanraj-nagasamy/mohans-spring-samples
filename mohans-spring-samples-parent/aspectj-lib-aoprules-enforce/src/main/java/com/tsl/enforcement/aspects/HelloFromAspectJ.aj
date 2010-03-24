package com.tsl.enforcement.aspects;

public aspect HelloFromAspectJ {

	pointcut mainMethod() : execution(public static void main(String[]));

	after() returning : mainMethod() {
		System.out.println("Hello from AspectJ");
	}
}