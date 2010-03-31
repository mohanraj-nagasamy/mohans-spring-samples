package org.martingilday;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Vector;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code   
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@Test
	public void testApp() {
		assertTrue(true);
	}

	//Exception Test
	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionWithException() {
		int i = 1 / 0;
	}

	//Ignore Test
	@Test(enabled = false)
	public void divisionWithException(int a) {
		System.out.println("Method is not ready yet");
	}

	//Timeout
	@Test(timeOut = 1000)
	public void infinity() {
		while (true)
			;
	}

	//Test groups
	@Test(groups = "method1")
	public void testingMethod1() {
		System.out.println("Method - testingMethod1()");
	}

	@Test(groups = "method2")
	public void testingMethod2() {
		System.out.println("Method - testingMethod2()");
	}

	@Test(groups = "method1")
	public void testingMethod1_1() {
		System.out.println("Method - testingMethod1_1()");
	}

	@Test(groups = "method4")
	public void testingMethod4() {
		System.out.println("Method - testingMethod4()");
	}

	//Test Dependency
	@Test
	public void method1() {
		System.out.println("This is method 1");
	}

	@Test(dependsOnMethods = { "method1" })
	public void method2() {
		System.out.println("This is method 2");
	}

	//Data provider
	@DataProvider(name = "dp")
	public Object[][] createData(Method m) {
		System.out.println("AppTest.createData()");
		System.out.println(m.getName());
		// print test method name 
		return new Object[][] { new Object[] { 122 }, { 2131 } };
	}

	@Test(dataProvider = "dp")
	//@Parameters(value = "number")
	public void parameterIntTest(int number) {
		System.out.println("Parameterized Number is : " + number);
	}

	@Test(dataProvider = "Data-Provider-Function")
	public void parameterIntTest(Class clzz, String[] number) {
		System.out.println("Parameterized Number is : " + number[0]);
		System.out.println("Parameterized Number is : " + number[1]);
	}

	//This function will provide the patameter data
	@DataProvider(name = "Data-Provider-Function")
	public Object[][] parameterIntTestProvider() {
		return new Object[][] {
				{ Vector.class, new String[] { "java.util.AbstractList", "java.util.AbstractCollection" } },
				{ String.class, new String[] { "1", "2" } }, { Integer.class, new String[] { "1", "2" } } };
	}

	//This method will provide data to any test method that declares that its Data Provider
	//is named "test1"
	@DataProvider(name = "test1")
	public Object[][] createData1() {
		return new Object[][] { { "Cedric", new Integer(36) }, { "Anne", new Integer(37) }, };
	}

	//This test method declares that its data should be supplied by the Data Provider
	//named "test1"
	@Test(dataProvider = "test1")
	public void verifyData1(String n1, Integer n2) {
		System.out.println("AppTest.verifyData1()");
		System.out.println(n1 + " " + n2);
	}
}
