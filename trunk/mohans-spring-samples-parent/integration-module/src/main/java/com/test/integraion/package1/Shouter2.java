package com.test.integraion.package1;

import org.springframework.stereotype.Component;

@Component
public class Shouter2 {
	public String shout(String s) {
		return s.concat(" ---> Shouter2 called....");
	}
}
