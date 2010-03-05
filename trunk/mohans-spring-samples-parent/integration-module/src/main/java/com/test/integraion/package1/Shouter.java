package com.test.integraion.package1;

import org.springframework.stereotype.Component;

@Component
public class Shouter {
	public String shout(String s) {
		return s.toUpperCase().concat("!!!");
	}
}
