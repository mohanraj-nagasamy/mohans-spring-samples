package org.martingilday;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Package123 {

	@Test
	@Parameters(value = "number")
	public void parameterIntTest(int number) {
		System.out.println("Package123.parameterIntTest()");

		System.out.println("Parameterized Number is : " + number);
	}
}
