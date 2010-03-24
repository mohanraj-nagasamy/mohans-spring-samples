package com.test.integraion.package3;

public class QuoteSubscriber {

	//@Subscriber(channel = "quotes")
	public void log(Object o) {
		System.out.println(o);
	}
}
